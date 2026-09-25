package zl6;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        OperationResult<User> userResult = new OperationResult<>(true, new User("Jan"), "Użytkownik");
        OperationResult<Double> doubleResult = new OperationResult<>(true, 1.0, "Liczba");
        OperationResult<List<String>> listResult = new OperationResult<>(true, List.of("Koło zębate", "Wał", "Łożysko"), "Lista");

        System.out.println(userResult.getValue());
        System.out.println(doubleResult.getValue());
        System.out.println(listResult.getValue());

        OperationResult<Integer> count = OperationResult.success(5);
        System.out.println(count.getValue() + " - " + count.getMessage());

//        OperationResult<Product> productResult = userResult;
    }
}
