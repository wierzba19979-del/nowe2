package pd15.exception;

public class LoanLimitExceededException extends RuntimeException {
    public LoanLimitExceededException() {
        super("Member has reached the maximum number of active loans");
    }
}