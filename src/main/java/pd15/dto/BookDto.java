package pd15.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor(staticName = "of")
@Getter
@ToString
public class BookDto {
    private Long id;
    private String isbn;
    private String title;
    private String author;
    private int year;
    private int availableCopies;
}
