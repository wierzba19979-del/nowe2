package pd15.service.impl;

import lombok.AllArgsConstructor;
import pd15.domain.Book;
import pd15.domain.Loan;
import pd15.domain.Member;
import pd15.dto.CreateLoanCommand;
import pd15.dto.LoanDto;
import pd15.dto.ReturnBookCommand;
import pd15.exception.BookNotFoundException;
import pd15.exception.LoanLimitExceededException;
import pd15.exception.LoanNotFoundException;
import pd15.exception.MemberNotFoundException;
import pd15.mapper.LoanMapper;
import pd15.repository.BookRepository;
import pd15.repository.LoanRepository;
import pd15.repository.MemberRepository;
import pd15.service.LoanService;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
public class LoanServiceImpl implements LoanService {
    private final LoanRepository loanRepository;
    private final BookRepository bookRepository;
    private final MemberRepository memberRepository;
    private final LoanMapper loanMapper;

    @Override
    public LoanDto borrowBook(CreateLoanCommand command) {
        Member member = memberRepository.findById(command.memberId())
                .orElseThrow(() -> new MemberNotFoundException(command.memberId()));
        Book book = bookRepository.findById(command.bookId())
                .orElseThrow(() -> new BookNotFoundException(command.bookId()));
        if (!member.canBorrowBook()) {
            throw new LoanLimitExceededException();
        }
        book.borrow();
        bookRepository.save(book);
        LocalDateTime borrowedAt = LocalDateTime.now();
        LocalDateTime dueDate = borrowedAt.plusDays(14);
        Long loanId = loanRepository.nextId();
        Loan loan = Loan.of(loanId, book, member, borrowedAt, dueDate, null);
        member.addLoan(loan);
        Loan savedLoan = loanRepository.save(loan);
        return loanMapper.toDto(savedLoan);
    }

    @Override
    public LoanDto returnBook(ReturnBookCommand command) {
        Loan loan = loanRepository.findById(command.loanId())
                .orElseThrow(() -> new LoanNotFoundException(command.loanId()));
        loan.complete();
        bookRepository.save(loan.getBook());
        Loan savedLoan = loanRepository.save(loan);
        return loanMapper.toDto(savedLoan);
    }

    @Override
    public List<LoanDto> getActiveLoans(Long memberId) {
        memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberNotFoundException(memberId));
        return loanRepository.findByMemberId(memberId).stream()
                .filter(loan -> loan.getReturnedAt() == null)
                .map(loanMapper::toDto)
                .toList();
    }

    @Override
    public List<LoanDto> getOverdueLoans() {
        return loanRepository.findAll().stream()
                .filter(Loan::isOverdue)
                .map(loanMapper::toDto)
                .toList();
    }
}
