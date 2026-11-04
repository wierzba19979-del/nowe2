package pd7.exception;

import lombok.Getter;

@Getter
public class ValidationException extends RegistrationException {
    private final String field;

    public ValidationException(String message, String field) {
        super(message);
        this.field = field;
    }

}
