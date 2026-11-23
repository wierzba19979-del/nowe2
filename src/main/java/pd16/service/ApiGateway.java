package pd16.service;

import lombok.RequiredArgsConstructor;
import pd16.model.*;

import io.vavr.control.Try;
import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
public class ApiGateway {
    private static final String COUNTRY_NAME = "Poland";
    private final JsonPlaceholderGateway jsonPlaceholderGateway;
    private final WeatherGateway weatherGateway;
    private final CountryGateway countryGateway;

    public UserReport getUserReport(Long userId) throws IOException, InterruptedException {
        if (userId == null || userId <= 0) {
            throw new IllegalArgumentException("User ID must be a positive number");
        }
        User user = jsonPlaceholderGateway.getUser(userId);
        List<Todo> todos = Try.of(() -> jsonPlaceholderGateway.getTodos(userId))
                .onFailure(e -> System.err.println("Failed to fetch TODOs: " + e.getMessage()))
                .getOrElse(List.of());
        List<Post> posts = Try.of(() -> jsonPlaceholderGateway.getPosts(userId))
                .onFailure(e -> System.err.println("Failed to fetch POSTS: " + e.getMessage()))
                .getOrElse(List.of());
        Weather weather = Try.of(() -> weatherGateway.getWeather(user))
                .onFailure(e -> System.err.println("Failed to fetch WEATHER: " + e.getMessage()))
                .getOrNull();
        Country country = Try.of(() -> countryGateway.getCountry(COUNTRY_NAME))
                .onFailure(e -> System.err.println("Failed to fetch COUNTRY: " + e.getMessage()))
                .getOrNull();
        return new UserReport(user, todos, posts, weather, country);
    }
}
