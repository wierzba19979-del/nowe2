package pd15.service;

import pd15.dto.CreateLoanCommand;
import pd15.dto.LoanDto;
import pd15.dto.ReturnBookCommand;

import java.util.List;


public interface LoanService {
    LoanDto borrowBook(CreateLoanCommand command);

    LoanDto returnBook(ReturnBookCommand command);

    List<LoanDto> getActiveLoans(Long memberId);

    List<LoanDto> getOverdueLoans();
}
