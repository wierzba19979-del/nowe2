package pd7.exception;

public class DuplicateEmailException extends RegistrationException {
    public DuplicateEmailException(String message) {
        super(message);
    }
}
