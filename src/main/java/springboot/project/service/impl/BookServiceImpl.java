package springboot.project.service.impl;

import org.springframework.stereotype.Service;
import springboot.project.entity.Book;
import springboot.project.exception.ResourceNotFoundException;
import springboot.project.payload.BookDto;
import springboot.project.repository.BookRepository;
import springboot.project.service.BookService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    private BookRepository bookRepository;

    // constructor based dependency injection
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public BookDto addBook(BookDto bookDto) {
        // convert dto to entity
        Book bookToAdd = mapToEntity(bookDto);

        Book savedBook = bookRepository.save(bookToAdd);

        return mapToDto(savedBook);

    }

    @Override
    public List<BookDto> getAllBooks() {
        List<Book> books = bookRepository.findAll();
        return books.stream().map(book -> mapToDto(book)).toList();

    }

    @Override
    public BookDto getBookById(long id) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Book", "id", id));
        return mapToDto(book);
    }

    @Override
    public BookDto updateBook(long id, BookDto bookDto) {
        Book found = bookRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Book", "id", id));

        found.setTitle(bookDto.getTitle());
        found.setAuthor(bookDto.getAuthor());
        Book savedBook = bookRepository.save(found);

        return mapToDto(savedBook);
    }

    @Override
    public void deleteBookById(long id) {
        Book found = bookRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Book", "id", id));
        bookRepository.delete(found);
    }

    // convert entity to dto
    private BookDto mapToDto(Book book) {
        return new BookDto(book.getId(), book.getTitle(), book.getAuthor());
    }

    // convert dto to entity
    private Book mapToEntity(BookDto bookDto) {
        Book book = new Book();
        book.setTitle(bookDto.getTitle());
        book.setAuthor(bookDto.getAuthor());
        return book;
    }

}
