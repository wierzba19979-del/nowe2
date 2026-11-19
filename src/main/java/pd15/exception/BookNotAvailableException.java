package pd15.exception;

public class BookNotAvailableException extends RuntimeException {

    public BookNotAvailableException(String bookTitle) {
        super("Book is not available: " + bookTitle);
    }
}
