package springboot.project.service;

import springboot.project.payload.BookDto;

import java.util.List;

public interface BookService {

    BookDto addBook(BookDto bookDto);

    List<BookDto> getAllBooks();

    BookDto getBookById(long id);

    BookDto updateBook(long id, BookDto bookDto);

    void deleteBookById(long id);
}
