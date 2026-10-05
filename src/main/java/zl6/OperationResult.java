package zl6;

public class OperationResult<T> {
    private final boolean success;
    private final T value;
    private final String message;

    private OperationResult(boolean success, T value, String message) {
        this.success = success;
        this.value = value;
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public T getValue() {
        return value;
    }

    public String getMessage() {
        return message;
    }

    public static <T> OperationResult<T> success(T value) {
        if (value == null) {
            throw new IllegalArgumentException("Wprowadzona wartość nie może być null");
        }
        return new OperationResult<>(true, value, "Operacja zakończona");

    }

    public static <T> OperationResult<T> ofFailed(String message){
        if (message == null || message.isBlank()){
            throw new IllegalArgumentException("Komunikat błędu nie może być pusty");
        }
        return new OperationResult<>(false, null, message);
    }
}
