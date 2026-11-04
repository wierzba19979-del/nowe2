package pd7;

import pd7.exception.DuplicateEmailException;
import pd7.exception.ValidationException;
import pd7.exception.WeakPasswordException;
import pd7.model.User;
import pd7.service.UserRegistrationService;

import java.util.Map;

public class Main {
    public static void main(String[] args) {
        UserRegistrationService service = new UserRegistrationService();

        String[][] users = {
                {"Jan Kowalski", "jan@gmail.com", "nQsjsxEFxL0ygaY"},
                {"Anna Zielinska", "jan@gmail.com", "WLT8mgliMmy6iX"},
                {"Piotr Wasilewski", "piotr@wp.pl", "acs!vsvdv"},
                {"A1", "a1@gmail.com", "abcdefhkffd"},
                {"Michał Poniatowski", "cdasvdf@gmail.com", "abc"}
        };

        for (String[] user : users) {
            tryRegister(service, user[0], user[1], user[2]);
        }
        printDatabase(service);
    }

    private static void tryRegister(
            UserRegistrationService service,
            String name,
            String email,
            String password) {
        try {
            User user = service.registerUser(name, email, password);
            System.out.println("Registration successful: " + user);
        } catch (ValidationException e) {
            System.out.println("Validation error in field '"
                    + e.getField() + "': " + e.getMessage());
        } catch (DuplicateEmailException e) {
            System.out.println("Duplicate email error: " + e.getMessage());
        } catch (WeakPasswordException e) {
            System.out.println("Weak password: " + e.getMessage() + "Score: " + e.getScore());
            for (String rule : e.getMismatchedRules()) {
                System.out.println("- " + rule);
            }
        }
        System.out.println();
    }

    private static void printDatabase(UserRegistrationService service) {
        System.out.println("=== USER DATABASE ===");
        for (Map.Entry<String, User> entry
                : service.getUsers().entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }
    }
}
