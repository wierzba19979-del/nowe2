package pd15.exception;

public class MemberNotFoundException extends RuntimeException {
    public MemberNotFoundException(Long memberId) {
        super("Member with id " + memberId + " not found");
    }
}
