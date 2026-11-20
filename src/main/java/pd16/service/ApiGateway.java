package pd16.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import pd16.dto.WeatherResponse;
import pd16.model.*;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.List;

public class ApiGateway {

    private static final String JSON_URL =
            "https://jsonplaceholder.typicode.com";

    private static final String WEATHER_URL =
            "https://api.open-meteo.com/v1/forecast";

    private static final String COUNTRY_URL =
            "https://restcountries.com/v3.1/name";

    private static final String COUNTRY_NAME = "Poland";

    private final HttpClient httpClient;
    private final ObjectMapper objectMapper;

    public ApiGateway() {

        this.httpClient = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(10))
                .followRedirects(HttpClient.Redirect.NORMAL)
                .build();

        this.objectMapper = new ObjectMapper()
                .configure(
                        DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
                        false
                );
    }

    private User getUser(Long userId) throws IOException, InterruptedException {
        String url = JSON_URL + "/users/" + userId;

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(
                request,
                HttpResponse.BodyHandlers.ofString()
        );

        if (response.statusCode() != 200) {
            throw new IOException(
                    "JSONPlaceholder error: HTTP "
                            + response.statusCode()
            );
        }

        return objectMapper.readValue(
                response.body(),
                User.class
        );
    }

    private List<Todo> getTodos(Long userId) throws IOException, InterruptedException {
        String url = JSON_URL + "/users/" + userId + "/todos";

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(
                request,
                HttpResponse.BodyHandlers.ofString()
        );

        if (response.statusCode() != 200) {
            throw new IOException("JSONPlaceholder TODO error: HTTP " + response.statusCode());
        }

        List<Todo> todos = objectMapper.readValue(
                response.body(),
                objectMapper.getTypeFactory()
                        .constructCollectionType(List.class, Todo.class)
        );

        return todos.stream()
                .filter(todo -> !todo.isCompleted())
                .toList();
    }

    private List<Post> getPosts(Long userId) throws IOException, InterruptedException {
        String url = JSON_URL + "/users/" + userId + "/posts";

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(
                request,
                HttpResponse.BodyHandlers.ofString()
        );

        if (response.statusCode() != 200) {
            throw new IOException(
                    "JSONPlaceholder POSTS error: HTTP "
                            + response.statusCode()
            );
        }

        List<Post> posts = objectMapper.readValue(
                response.body(),
                objectMapper.getTypeFactory()
                        .constructCollectionType(List.class, Post.class)
        );

        return posts.stream()
                .sorted((p1, p2) -> p2.getId().compareTo(p1.getId()))
                .limit(3)
                .toList();
    }


    private Weather getWeather(User user) throws IOException, InterruptedException {
        Geo geo = user.getAddress().geo();

        double latitude = Double.parseDouble(geo.lat());
        double longitude = Double.parseDouble(geo.lng());

        String url = WEATHER_URL
                + "?latitude=" + latitude
                + "&longitude=" + longitude
                + "&current=temperature_2m,wind_speed_10m,weather_code";

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(
                request,
                HttpResponse.BodyHandlers.ofString()
        );

        if (response.statusCode() != 200) {
            throw new IOException(
                    "Open-Meteo error: HTTP " + response.statusCode()
            );
        }

        WeatherResponse weatherResponse = objectMapper.readValue(
                response.body(),
                WeatherResponse.class
        );

        WeatherResponse.Current current = weatherResponse.current();

        return new Weather(
                current.temperature(),
                current.windSpeed(),
                current.weatherCode()
        );
    }

    private Country getCountry(String countryName) throws IOException, InterruptedException {

        String encodedName = URLEncoder.encode(
                countryName,
                StandardCharsets.UTF_8
        ).replace("+", "%20");

        String url = COUNTRY_URL + "/" + encodedName;

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(
                request,
                HttpResponse.BodyHandlers.ofString()
        );

        if (response.statusCode() != 200) {
            throw new IOException("REST Countries error: HTTP " + response.statusCode());
        }

        JsonNode root = objectMapper.readTree(response.body());

        if (!root.isArray() || root.isEmpty()) {
            throw new IOException("Country not found: " + countryName);
        }

        JsonNode countryData = root.get(0);

        String name = countryData.path("name")
                .path("common").asText();

        String capital = countryData.path("capital")
                .path(0).asText();

        String region = countryData.path("region").asText();

        String cca2 = countryData.path("cca2").asText();

        long population = countryData.path("population").asLong();

        return new Country(name, capital, region, cca2, population);
    }

    public UserReport getUserReport(Long userId)
            throws IOException, InterruptedException {

        if (userId == null || userId <= 0) {
            throw new IllegalArgumentException(
                    "User ID must be a positive number"
            );
        }

        User user;

        try {
            user = getUser(userId);
        } catch (IOException | InterruptedException e) {
            System.err.println("Failed to fetch user: " + e.getMessage());
            throw e;
        }

        List<Todo> todos;

        try {
            todos = getTodos(userId);
        } catch (IOException | InterruptedException e) {
            System.err.println("Failed to fetch TODOs: " + e.getMessage());
            todos = List.of();
        }

        List<Post> posts;

        try {
            posts = getPosts(userId);
        } catch (IOException | InterruptedException e) {
            System.err.println("Failed to fetch posts: " + e.getMessage());
            posts = List.of();
        }

        Weather weather = null;

        try {
            weather = getWeather(user);
        } catch (IOException | InterruptedException e) {
            System.err.println("Failed to fetch weather: " + e.getMessage());
        }

        Country country = null;

        try {
            country = getCountry(COUNTRY_NAME);
        } catch (IOException | InterruptedException e) {
            System.err.println("Failed to fetch country: " + e.getMessage());
        }

        return new UserReport(user, todos, posts, weather, country);
    }


}
