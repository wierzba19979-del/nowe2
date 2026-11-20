package pd15.repository.impl;

import pd15.domain.Loan;
import pd15.repository.LoanRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

public class LoanRepositoryImpl implements LoanRepository {
    private final Map<Long, Loan> loans = new HashMap<>();
    private final AtomicLong loanIdGenerator = new AtomicLong(1);

    @Override
    public Loan save(Loan loan) {
        loans.put(loan.getId(), loan);
        return loan;
    }

    @Override
    public Optional<Loan> findById(Long id) {
        return Optional.ofNullable(loans.get(id));
    }

    @Override
    public List<Loan> findByMemberId(Long memberId) {
        return loans.values().stream().filter(loan -> memberId.equals(loan.getMember().getId())).toList();
    }

    @Override
    public List<Loan> findAll() {
        return loans.values().stream().toList();
    }

    @Override
    public Long nextId() {
        return loanIdGenerator.getAndIncrement();
    }
}
