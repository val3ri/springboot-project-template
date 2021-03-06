package springboot.project.repository;

import springboot.project.model.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<BookEntity, String> {

    BookEntity findById(Long id);
    List<BookEntity> findAll();

}