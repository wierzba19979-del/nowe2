package pd15.dto;

public record CreateLoanCommand(
        Long memberId,
        Long bookId) {
}
