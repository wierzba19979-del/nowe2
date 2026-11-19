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

    public boolean isOverdue() {
        return returnedAt == null && dueDate.isBefore(LocalDateTime.now());
    }

    public void markAsReturned(){
        if (this.returnedAt != null){
            throw new IllegalStateException("This loan was already returned");
        }
        this.returnedAt = LocalDateTime.now();
    }
}
