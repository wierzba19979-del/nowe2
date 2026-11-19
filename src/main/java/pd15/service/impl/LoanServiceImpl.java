package pd15.service.impl;

import pd15.domain.Book;
import pd15.domain.Loan;
import pd15.domain.Member;
import pd15.dto.CreateLoanRequest;
import pd15.dto.LoanDto;
import pd15.dto.ReturnBookRequest;
import pd15.exception.BookNotFoundException;
import pd15.exception.LoanLimitExceededException;
import pd15.exception.LoanNotFoundException;
import pd15.exception.MemberNotFoundException;
import pd15.repository.BookRepository;
import pd15.repository.LoanRepository;
import pd15.repository.MemberRepository;
import pd15.service.LoanService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class LoanServiceImpl implements LoanService {
    private final LoanRepository loanRepository;
    private final BookRepository bookRepository;
    private final MemberRepository memberRepository;
    private final AtomicLong loanIdGenerator = new AtomicLong(1);

    public LoanServiceImpl(
            LoanRepository loanRepository,
            BookRepository bookRepository,
            MemberRepository memberRepository) {
        this.loanRepository = loanRepository;
        this.bookRepository = bookRepository;
        this.memberRepository = memberRepository;
    }

    @Override
    public LoanDto borrowBook(CreateLoanRequest request) {
        Member member = memberRepository.findById(request.getMemberId())
                .orElseThrow(() -> new MemberNotFoundException(
                        "Member with id " + request.getMemberId() + " not found")
                );
        Book book = bookRepository.findById(request.getBookId())
                .orElseThrow(() -> new BookNotFoundException(
                        "Book with id " + request.getBookId() + " not found")
                );
        if (!member.canBorrow()) {
            throw new LoanLimitExceededException("Member has reached the maximum number of active loans");
        }
        book.borrow();
        bookRepository.save(book);
        LocalDateTime borrowedAt = LocalDateTime.now();
        LocalDateTime dueDate = borrowedAt.plusDays(14);
        Long loanId = loanIdGenerator.getAndIncrement();
        Loan loan = Loan.of(loanId, book, member, borrowedAt, dueDate, null);
        member.getLoans().add(loan);
        Loan savedLoan = loanRepository.save(loan);
        return LoanDto.of(
                savedLoan.getId(),
                savedLoan.getBook().getId(),
                savedLoan.getBook().getTitle(),
                savedLoan.getMember().getId(),
                savedLoan.getMember().getName(),
                savedLoan.getBorrowedAt(),
                savedLoan.getDueDate(),
                savedLoan.getReturnedAt());
    }

    @Override
    public LoanDto returnBook(ReturnBookRequest request) {
        Loan loan = loanRepository.findById(request.getLoanId())
                .orElseThrow(() -> new LoanNotFoundException(
                        "Loan with id " + request.getLoanId() + " not found"
                ));
        loan.markAsReturned();
        Book book = loan.getBook();
        book.returnBack();
        bookRepository.save(book);
        Loan savedLoan = loanRepository.save(loan);
        return LoanDto.of(
                savedLoan.getId(),
                savedLoan.getBook().getId(),
                savedLoan.getBook().getTitle(),
                savedLoan.getMember().getId(),
                savedLoan.getMember().getName(),
                savedLoan.getBorrowedAt(),
                savedLoan.getDueDate(),
                savedLoan.getReturnedAt()
        );
    }

    @Override
    public List<LoanDto> getActiveLoans(Long memberId) {
        memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberNotFoundException(
                        "Member with id " + memberId + " not found"
                ));

        return loanRepository.findByMemberId(memberId).stream()
                .filter(loan -> loan.getReturnedAt() == null)
                .map(loan -> LoanDto.of(
                        loan.getId(),
                        loan.getBook().getId(),
                        loan.getBook().getTitle(),
                        loan.getMember().getId(),
                        loan.getMember().getName(),
                        loan.getBorrowedAt(),
                        loan.getDueDate(),
                        loan.getReturnedAt()
                ))
                .toList();
    }

    @Override
    public List<LoanDto> getOverdueLoans() {
        return loanRepository.findAll().stream()
                .filter(Loan::isOverdue)
                .map(loan -> LoanDto.of(
                        loan.getId(),
                        loan.getBook().getId(),
                        loan.getBook().getTitle(),
                        loan.getMember().getId(),
                        loan.getMember().getName(),
                        loan.getBorrowedAt(),
                        loan.getDueDate(),
                        loan.getReturnedAt()
                ))
                .toList();
    }
}
