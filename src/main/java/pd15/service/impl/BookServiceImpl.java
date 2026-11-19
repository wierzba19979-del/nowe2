package pd15.service.impl;

import lombok.AllArgsConstructor;
import pd15.domain.Book;
import pd15.dto.BookDto;
import pd15.exception.BookNotFoundException;
import pd15.mapper.BookMapper;
import pd15.repository.BookRepository;
import pd15.service.BookService;

import java.util.List;
import java.util.Locale;

@AllArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    @Override
    public BookDto addBook(Book book) {
        Book saveBook = bookRepository.save(book);
        return bookMapper.toDto(saveBook);
    }

    @Override
    public BookDto findByIsbn(String isbn) {
        return bookRepository.findByIsbn(isbn)
                .map(bookMapper::toDto)
                .orElseThrow(() -> new BookNotFoundException(isbn));
    }

    @Override
    public List<BookDto> findAvailable() {
        return bookRepository.findAll().stream()
                .filter(book -> book.getAvailableCopies() > 0)
                .map(bookMapper::toDto)
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
                .map(bookMapper::toDto)
                .toList();

    }
}
