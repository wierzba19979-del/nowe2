package pd15.service.impl;

import pd15.domain.Book;
import pd15.dto.BookDto;
import pd15.exception.BookNotFoundException;
import pd15.repository.BookRepository;
import pd15.service.BookService;

import java.util.List;
import java.util.Locale;

public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public BookDto addBook(Book book) {
        Book saveBook = bookRepository.save(book);
        return BookDto.of(
                saveBook.getId(),
                saveBook.getIsbn(),
                saveBook.getTitle(),
                saveBook.getAuthor(),
                saveBook.getYear(),
                saveBook.getAvailableCopies());
    }

    @Override
    public BookDto findByIsbn(String isbn) {
        return bookRepository.findByIsbn(isbn)
                .map(book -> BookDto.of(
                        book.getId(),
                        book.getIsbn(),
                        book.getTitle(),
                        book.getAuthor(),
                        book.getYear(),
                        book.getAvailableCopies()))
                .orElseThrow(() -> new BookNotFoundException("Book with ISBN " + isbn + " not found"));
    }

    @Override
    public List<BookDto> findAvailable() {
        return bookRepository.findAll().stream()
                .filter(book -> book.getAvailableCopies() > 0)
                .map(book -> BookDto.of(
                        book.getId(),
                        book.getIsbn(),
                        book.getTitle(),
                        book.getAuthor(),
                        book.getYear(),
                        book.getAvailableCopies()))
                .toList();
    }

    @Override
    public List<BookDto> search(String query) {
        if (query == null || query.isBlank()) {
            return List.of();
        }
        String lowerCaseQuery = query.toLowerCase(Locale.ROOT);
        return bookRepository.findAll().stream().filter(book ->
                        (book.getTitle() != null && book.getTitle().toLowerCase().contains(lowerCaseQuery)) ||
                        (book.getAuthor() != null && book.getAuthor().toLowerCase().contains(lowerCaseQuery)) ||
                        (book.getIsbn() != null && book.getIsbn().toLowerCase().contains(lowerCaseQuery)))
                .map(book -> BookDto.of(
                        book.getId(),
                        book.getIsbn(),
                        book.getTitle(),
                        book.getAuthor(),
                        book.getYear(),
                        book.getAvailableCopies()))
                .toList();

    }
}
