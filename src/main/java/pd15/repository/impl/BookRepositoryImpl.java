package pd15.repository.impl;

import pd15.domain.Book;
import pd15.repository.BookRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class BookRepositoryImpl implements BookRepository {

    private final Map<Long, Book> books = new HashMap<>();

    @Override
    public Book save(Book book) {
        books.put(book.getId(), book);
        return book;
    }

    @Override
    public Optional<Book> findById(Long id) {
        return Optional.ofNullable(books.get(id));
    }

    @Override
    public Optional<Book> findByIsbn(String isbn) {
        if (isbn == null){
            return Optional.empty();
        }
        return books.values().stream().filter(book -> isbn.equals(book.getIsbn())).findFirst();
    }

    @Override
    public List<Book> findAll() {
        return books.values().stream().toList();
    }
}