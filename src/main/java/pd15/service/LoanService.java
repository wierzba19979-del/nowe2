package pd15.service;

import pd15.dto.CreateLoanRequest;
import pd15.dto.LoanDto;
import pd15.dto.ReturnBookRequest;

import java.util.List;


public interface LoanService {
    LoanDto borrowBook(CreateLoanRequest request);

    LoanDto returnBook(ReturnBookRequest request);

    List<LoanDto> getActiveLoans(Long memberId);

    List<LoanDto> getOverdueLoans();
}
