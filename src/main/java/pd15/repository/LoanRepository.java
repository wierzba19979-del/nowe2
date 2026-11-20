package pd15.repository;

import pd15.domain.Loan;

import java.util.List;
import java.util.Optional;

public interface LoanRepository {
    Loan save(Loan loan);

    Optional<Loan> findById(Long id);

    List<Loan> findByMemberId(Long memberId);

    List<Loan> findAll();

    Long nextId();
}
