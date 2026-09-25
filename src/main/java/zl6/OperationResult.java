package zl6;

public class OperationResult <T>{
    private final boolean success;
    private final T value;
    private final String message;

    public OperationResult(boolean success, T value, String message) {
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

    public static <T> OperationResult<T> success (T value){
        return new OperationResult<>(true, value, "Operacja zakończona");
    }
}
