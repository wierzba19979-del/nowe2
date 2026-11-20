package pd15.exception;

public class BookNotFoundException extends RuntimeException {
    public BookNotFoundException(Long bookId) {
        super("Book with id " + bookId + " not found");
    }

    public BookNotFoundException(String isbn) {
        super("Book with ISBN " + isbn + " not found");
    }
}
