package springboot.project.service;

import springboot.project.payload.BookDto;
import springboot.project.payload.BookResponse;

public interface BookService {

    BookDto addBook(BookDto bookDto);

    BookResponse getAllBooks(int pageNo, int pageSize, String sortBy, String sortDir);

    BookDto getBookById(long id);

    BookDto updateBook(long id, BookDto bookDto);

    void deleteBookById(long id);
}
