package zl6;

public class User {
    private String userName;

    public User(String userName) {
        this.userName = userName;
    }


    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                '}';
    }
}
