package springboot.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import springboot.project.entity.Book;

public interface BookRepository extends JpaRepository<Book, Long> {

}