package pd7.service;

import pd7.exception.DuplicateEmailException;
import pd7.exception.ValidationException;
import pd7.exception.WeakPasswordException;
import pd7.model.PasswordStrength;
import pd7.model.User;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class UserRegistrationService {
    private final Map<String, User> users = new HashMap<>();
    private static final int MIN_PASSWORD_LENGTH = 8;
    private static final int STRONG_PASSWORD_LENGTH = 15;
    private static final int MIN_NAME_LENGTH = 2;
    private static final int MAX_NAME_LENGTH = 100;


    public User registerUser(String name, String email, String password) {
        validateName(name);
        validateEmail(email);

        String normalizedEmail = email.trim().toLowerCase();

        if (users.containsKey(normalizedEmail)) {
            throw new DuplicateEmailException("A user with this email already exists.");
        }
        String[] missMatched = missMatchedRules(password);
        int score;
        if (password == null) {
            score = 0;
        } else {
            score = 4 - missMatched.length;
        }
        PasswordStrength strength = validatePassword(password);
        if (strength == PasswordStrength.WEAK) {
            throw new WeakPasswordException("Password is too weak.", missMatched, score);
        }
        String hashedPassword = "hashed_" + password;
        User user = new User(name.trim(), normalizedEmail, hashedPassword);
        users.put(normalizedEmail, user);
        return user;
    }

    private String[] missMatchedRules(String password) {
        if (password == null) {
            return new String[]{"Password cannot be null."};
        }
        boolean hasUpperCase = false;
        boolean hasDigit = false;
        boolean hasSpecial = false;
        for (int i = 0; i < password.length(); i++) {
            char c = password.charAt(i);
            if (Character.isUpperCase(c)) {
                hasUpperCase = true;
            }
            if (Character.isDigit(c)) {
                hasDigit = true;
            }
            if (!Character.isLetter(c) && !Character.isWhitespace(c)) {
                hasSpecial = true;
            }
        }

        String[] missMatchedRules = new String[4];
        int n = 0;

        if (password.length() < MIN_PASSWORD_LENGTH) {
            missMatchedRules[n++] = "Password must contain at least " + MIN_PASSWORD_LENGTH + " characters.";
        }
        if (!hasUpperCase) {
            missMatchedRules[n++] = "Password must contain an uppercase letter.";
        }
        if (!hasDigit) {
            missMatchedRules[n++] = "Password must contain a digit.";
        }
        if (!hasSpecial) {
            missMatchedRules[n++] = "Password must contain a special character.";
        }
        return Arrays.copyOf(missMatchedRules, n);
    }

    public PasswordStrength validatePassword(String password) {
        if (missMatchedRules(password).length > 0) {
            return PasswordStrength.WEAK;
        }
        return password.length() >= STRONG_PASSWORD_LENGTH ? PasswordStrength.STRONG : PasswordStrength.OK;
    }


    private void validateEmail(String email) {
        if (email == null ||
                !email.trim().matches("^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$")) {
            throw new ValidationException(
                    "Invalid email format",
                    "email"
            );
        }
    }

    private void validateName(String name) {
        if (name == null || name.trim().length() < MIN_NAME_LENGTH) {
            throw new ValidationException("Name must contain at least " + MIN_NAME_LENGTH + " characters.", "name");
        }
        if (name.trim().length() > MAX_NAME_LENGTH) {
            throw new ValidationException("Name must not exceed " + MAX_NAME_LENGTH + " characters.", "name");
        }
        if (!name.trim().matches("[\\p{L} -]+")) {
            throw new ValidationException("Name can contain only letters, spaces, and hyphens.", "name");
        }
    }

    public Map<String, User> getUsers() {
        return Map.copyOf(users);
    }
}
