package zl6;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        OperationResult<User> userResult = OperationResult.success(new User("Jan"));
        OperationResult<Double> doubleResult = OperationResult.success(1.0);
        OperationResult<List<String>> listResult =OperationResult.success(List.of("Koło zębate", "Wał", "Łożysko"));
        OperationResult<User> notFound = OperationResult.ofFailed("Nie znaleziono użytkownika");

        System.out.println(userResult.getValue() + " " + userResult.getMessage());
        System.out.println(doubleResult.getValue() + " " + doubleResult.getMessage());
        System.out.println(listResult.getValue() + " " + listResult.getMessage());
        System.out.println(notFound.getValue());

//        OperationResult<Product> productResult = userResult;
    }
}
