package pd7.exception;

import lombok.Getter;

public class WeakPasswordException extends RegistrationException {
    private final String[] mismatchedRules;
    @Getter
    private final int score;

    public WeakPasswordException(String message, String[] mismatchedRules, int score) {
        super(message);
        this.mismatchedRules = mismatchedRules.clone();
        this.score = score;
    }

    public String[] getMismatchedRules() {
        return mismatchedRules.clone();
    }

}
