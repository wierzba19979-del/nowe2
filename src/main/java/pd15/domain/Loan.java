package pd15.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor(staticName = "of")
@Getter
public class Loan {
    private Long id;
    private Book book;
    private Member member;
    private LocalDateTime borrowedAt;
    private LocalDateTime dueDate;
    private LocalDateTime returnedAt;
    private LoanStatus status;

    public boolean isOverdue() {
        return status == LoanStatus.ACTIVE
                && dueDate.isBefore(LocalDateTime.now());
    }

    public void complete() {
        if (status == LoanStatus.RETURNED) {
            throw new IllegalStateException("This loan was already returned");
        }
        returnedAt = LocalDateTime.now();
        status = LoanStatus.RETURNED;
        book.returnBack();
    }
}
