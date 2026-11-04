package pd7.model;

public record  User (String name, String email, String hashedPassword) {
    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
