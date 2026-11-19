package pd15;

import pd15.domain.Book;
import pd15.domain.Member;
import pd15.dto.CreateLoanCommand;
import pd15.dto.LoanDto;
import pd15.dto.ReturnBookCommand;
import pd15.exception.BookNotAvailableException;
import pd15.exception.LoanLimitExceededException;
import pd15.mapper.BookMapper;
import pd15.mapper.LoanMapper;
import pd15.mapper.MemberMapper;
import pd15.repository.*;
import pd15.repository.impl.*;
import pd15.service.BookService;
import pd15.service.LoanService;
import pd15.service.MemberService;
import pd15.service.impl.BookServiceImpl;
import pd15.service.impl.LoanServiceImpl;
import pd15.service.impl.MemberServiceImpl;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        BookRepository bookRepository = new BookRepositoryImpl();
        MemberRepository memberRepository = new MemberRepositoryImpl();
        LoanRepository loanRepository = new LoanRepositoryImpl();
        BookMapper bookMapper = new BookMapper();
        MemberMapper memberMapper = new MemberMapper();
        LoanMapper loanMapper = new LoanMapper();
        BookService bookService = new BookServiceImpl(bookRepository, bookMapper);
        MemberService memberService = new MemberServiceImpl(memberRepository, memberMapper);
        LoanService loanService = new LoanServiceImpl(
                loanRepository,
                bookRepository,
                memberRepository,
                loanMapper
        );
        Book book1 = Book.of(
                1L,
                "9788307033423",
                "Solaris",
                "Stanisław Lem",
                1961,
                3
        );
        Book book2 = Book.of(
                2L,
                "9788377791970",
                "Quo Vadis",
                "Henryk Sienkiewicz",
                1896,
                2
        );
        Book book3 = Book.of(
                3L,
                "9788373271890",
                "Krzyżacy",
                "Henryk Sienkiewicz",
                1900,
                2
        );
        bookService.addBook(Book.of(
                4L,
                "9780000000001",
                "Testowa książka",
                "Autor Testowy",
                2020,
                2
        ));
        bookService.addBook(book1);
        bookService.addBook(book2);
        bookService.addBook(book3);
        Member member1 = Member.of(
                1L,
                "Jan Kowalski",
                "jan@example.com",
                new ArrayList<>()
        );
        Member member2 = Member.of(
                2L,
                "Anna Nowak",
                "anna@example.com",
                new ArrayList<>()
        );
        Member member3 = Member.of(
                3L,
                "Piotr Nowak",
                "piotr@example.com",
                new ArrayList<>()
        );
        memberService.registerMember(member1);
        memberService.registerMember(member2);
        memberService.registerMember(member3);

        System.out.println("\n=== Sprawdzenie wyszukiwania ===");
        System.out.println(bookService.findByIsbn("9788307033423"));
        System.out.println(memberService.findById(1L));

        LoanDto loan1 = loanService.borrowBook(new CreateLoanCommand(1L, 1L));

        System.out.println("\n=== WYPOŻYCZENIE ===");
        System.out.println(loan1);
        System.out.println("\n=== DOSTĘPNOŚĆ KSIĄŻKI ===");
        System.out.println(bookService.findByIsbn("9788307033423"));

        LoanDto returnedLoan = loanService.returnBook(new ReturnBookCommand(1L));

        System.out.println("\n=== ZWROT KSIĄŻKI ===");
        System.out.println(returnedLoan);
        System.out.println("\n=== DOSTĘPNOŚĆ PO ZWROCIE ===");
        System.out.println(bookService.findByIsbn("9788307033423"));

        System.out.println("\n=== TEST PODWÓJNEGO ZWROTU ===");
        try {
            loanService.returnBook(new ReturnBookCommand(1L));
        } catch (IllegalStateException e) {
            System.out.println("Wyjątek: " + e.getMessage());
        }

        System.out.println("\n=== TEST LIMITU WYPOŻYCZEŃ ===");
        loanService.borrowBook(new CreateLoanCommand(1L, 1L));
        loanService.borrowBook(new CreateLoanCommand(1L, 2L));
        loanService.borrowBook(new CreateLoanCommand(1L, 3L));
        System.out.println("Aktywne wypożyczenia: "
                + loanService.getActiveLoans(1L).size());
        try {
            loanService.borrowBook(new CreateLoanCommand(1L, 1L));
        } catch (LoanLimitExceededException e) {
            System.out.println("Limit zadziałał: " + e.getMessage());
        }

        System.out.println(bookService.findByIsbn("9788377791970"));

        System.out.println("=== TEST BRAKU DOSTĘPNOŚCI ===");
        loanService.borrowBook(new CreateLoanCommand(3L, 4L));
        loanService.borrowBook(new CreateLoanCommand(3L, 4L));
        try {
            loanService.borrowBook(new CreateLoanCommand(2L, 4L));
        } catch (BookNotAvailableException e) {
            System.out.println("Wypożyczenie zablokowane: " + e.getMessage());
        }
    }
}
