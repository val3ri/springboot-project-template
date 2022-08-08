package springboot.project.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import springboot.project.entity.Book;
import springboot.project.exception.ResourceNotFoundException;
import springboot.project.payload.BookDto;
import springboot.project.payload.BookResponse;
import springboot.project.repository.BookRepository;
import springboot.project.service.BookService;


@Service
public class BookServiceImpl implements BookService {

    private ModelMapper modelMapper;
    private BookRepository bookRepository;

    // constructor based dependency injection
    public BookServiceImpl(BookRepository bookRepository, ModelMapper modelMapper) {
        this.bookRepository = bookRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public BookDto addBook(BookDto bookDto) {
        // convert dto to entity
        Book bookToAdd = mapToEntity(bookDto);

        Book savedBook = bookRepository.save(bookToAdd);

        return mapToDto(savedBook);

    }

    @Override
    public BookResponse getAllBooks(int pageNo, int pageSize, String sortBy, String sortDir) {
        // create Pageable instance
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(Sort.Direction.fromString(sortDir), sortBy));
        Page<Book> books = bookRepository.findAll(pageable);

        // get content for page object
        BookResponse bookResponse = new BookResponse();

        bookResponse.setContent(books.getContent().stream().map(book -> mapToDto(book)).toList());
        bookResponse.setPageNo(books.getNumber());
        bookResponse.setPageSize(books.getSize());
        bookResponse.setTotalElements(books.getTotalElements());
        bookResponse.setTotalPages(books.getTotalPages());
        bookResponse.setLast(books.isLast());

        return bookResponse;

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
        found.setPublicationDate(bookDto.getPublicationDate());
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
        return modelMapper.map(book, BookDto.class);
    }

    // convert dto to entity
    private Book mapToEntity(BookDto bookDto) {
        return modelMapper.map(bookDto, Book.class);
    }

}
