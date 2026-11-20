package pd16;

import pd16.model.*;
import pd16.service.ApiGateway;

public class Main {

    public static void main(String[] args) throws Exception {
        ApiGateway gateway = new ApiGateway();
        UserReport report = gateway.getUserReport(1L);

        System.out.println("===== USER REPORT =====");
        System.out.println("\nUSER:");
        System.out.println("Name: " + report.user().getName());
        System.out.println("Email: " + report.user().getEmail());
        System.out.println("City: " + report.user().getAddress().city());

        System.out.println("\nINCOMPLETE TODOS:");
        report.todos().forEach(todo -> System.out.println("- " + todo.getTitle()));

        System.out.println("\nLAST 3 POSTS:");
        report.posts().forEach(post -> System.out.println("- " + post.getTitle()));

        System.out.println("\nWEATHER:");
        if (report.weather() != null) {
            System.out.println("Temperature: " + report.weather().temperature() + " °C");
            System.out.println("Wind speed: " + report.weather().windSpeed() + " km/h");
            System.out.println("Weather code: " + report.weather().weatherCode());
        } else {
            System.out.println("Weather unavailable");
        }

        System.out.println("\nCOUNTRY:");
        if (report.country() != null) {
            System.out.println("Name: " + report.country().name());
            System.out.println("Capital: " + report.country().capital());
            System.out.println("Population: " + report.country().population());
        } else {
            System.out.println("Country unavailable");
        }
    }
}