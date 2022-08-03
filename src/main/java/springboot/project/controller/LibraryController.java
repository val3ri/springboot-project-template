package springboot.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springboot.project.payload.BookDto;
import springboot.project.service.BookService;

import java.util.List;

@RestController
@RequestMapping("/api/library")
public class LibraryController {

    // using interface -> because of the loose coupling
    private BookService bookService;

    @Autowired
    public LibraryController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping(value = "/books")
    public List<BookDto> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping(value = "/book/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BookDto> getBookById(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(bookService.getBookById(id));
    }

    @PostMapping(value = "/book", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BookDto> addBook(@RequestBody BookDto dto) {
        return new ResponseEntity<>(bookService.addBook(dto), HttpStatus.CREATED);
    }

    @PutMapping(value = "/book/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BookDto> updateBook(@PathVariable(name = "id") long id, @RequestBody BookDto bookDto) {
        return new ResponseEntity<>(bookService.updateBook(id, bookDto), HttpStatus.OK);
    }

    @DeleteMapping(value = "/book/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable(name = "id") Long id) {
        bookService.deleteBookById(id);
        return ResponseEntity.ok("Book entity deleted successfully");
    }
}