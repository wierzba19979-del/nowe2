package pd16.model;

public record Weather(
        double temperature,
        double windSpeed,
        int weatherCode
) {
}