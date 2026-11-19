package pd15.mapper;

import pd15.domain.Book;
import pd15.dto.BookDto;

public class BookMapper {

    public BookDto toDto(Book book) {
        return BookDto.of(
                book.getId(),
                book.getIsbn(),
                book.getTitle(),
                book.getAuthor(),
                book.getYear(),
                book.getAvailableCopies()
        );
    }
}