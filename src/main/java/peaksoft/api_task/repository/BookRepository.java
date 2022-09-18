package peaksoft.api_task.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import peaksoft.api_task.entity.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
//    @Query("select b from Book b where b.author.id = ?1")
//    Book getBookByAuthorId(@Param("id") Long id);
}