package pd16.model;

public record Country(
        String name,
        String capital,
        String region,
        String cca2,
        long population
) {
}