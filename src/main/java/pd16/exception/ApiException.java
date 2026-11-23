package pd16.exception;

public class ApiException extends RuntimeException {
    public ApiException(String apiName, int statusCode) {
        super(apiName + " error: HTTP " + statusCode);
    }
}