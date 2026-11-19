package pd15.domain;

import pd15.exception.BookNotAvailableException;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(staticName = "of")
@Getter
public class Book {
    private Long id;
    private String isbn;
    private String title;
    private String author;
    private int year;
    private int availableCopies;

    public void borrow() {
        if (availableCopies == 0) {
            throw new BookNotAvailableException("Book is not available: " + title);
        }
        availableCopies--;
    }

    public void returnBack() {
        availableCopies++;
    }
}
