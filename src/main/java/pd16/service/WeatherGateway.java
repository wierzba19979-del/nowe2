package pd16.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import pd16.dto.WeatherResponse;
import pd16.model.Point;
import pd16.model.User;
import pd16.model.Weather;

import java.io.IOException;
import java.net.http.HttpClient;

public class WeatherGateway extends AbstractApiGateway {
    private static final String BASE_URL = "https://api.open-meteo.com/v1/forecast";

    public WeatherGateway(HttpClient httpClient, ObjectMapper objectMapper) {
        super(httpClient, objectMapper);
    }

    public Weather getWeather(User user) throws IOException, InterruptedException {
        Point point = PointExtractor.extract(user.getAddress().point());
        String url = BASE_URL
                + "?latitude=" + point.latitude()
                + "&longitude=" + point.longitude()
                + "&current=temperature_2m,wind_speed_10m,weather_code";
        return sendGet(url, "Open-Meteo", body -> {
            WeatherResponse response = objectMapper.readValue(body, WeatherResponse.class);
            WeatherResponse.Current current = response.current();
            return new Weather(current.temperature(), current.windSpeed(), current.weatherCode());
        });
    }
}
