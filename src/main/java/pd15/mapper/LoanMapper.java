package pd15.mapper;

import pd15.domain.Loan;
import pd15.dto.LoanDto;

public class LoanMapper {
    public LoanDto toDto(Loan loan) {
        return new LoanDto(
                loan.getId(),
                loan.getBook().getId(),
                loan.getBook().getTitle(),
                loan.getMember().getId(),
                loan.getMember().getName(),
                loan.getBorrowedAt(),
                loan.getDueDate(),
                loan.getReturnedAt()
        );
    }
}
