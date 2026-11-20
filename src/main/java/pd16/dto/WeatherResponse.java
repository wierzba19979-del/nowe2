package pd16.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record WeatherResponse(Current current) {

    public record Current(
            @JsonProperty("temperature_2m")
            double temperature,

            @JsonProperty("wind_speed_10m")
            double windSpeed,

            @JsonProperty("weather_code")
            int weatherCode
    ) {}
}