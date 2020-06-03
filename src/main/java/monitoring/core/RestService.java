package monitoring.core;

import monitoring.model.BookEntity;
import monitoring.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class RestService {
    private BookRepository bookRepository;

    @Autowired
    public RestService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

//    public String getBookStats(Long id) {
//        BookEntity book = bookRepository.findById(id);
//        String result = "{ID : " + book.getId().toString() + ",Title : " + book.getTitle() + ",Author :" + book.getAuthor() + " }";
//
//        return result;
//    }

    public List<BookEntity> findAll() {
        return bookRepository.findAll();
    }
}
