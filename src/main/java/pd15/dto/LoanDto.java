package pd15.dto;

import pd15.domain.LoanStatus;

import java.time.LocalDateTime;

public record LoanDto(
        Long id,
        Long bookId,
        String bookTitle,
        Long memberId,
        String memberName,
        LocalDateTime borrowedAt,
        LocalDateTime dueDate,
        LocalDateTime returnedAt,
        LoanStatus status) {

}

