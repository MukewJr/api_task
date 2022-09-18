package peaksoft.api_task.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import peaksoft.api_task.entity.Author;

import java.util.List;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    @Override
    Page<Author> findAll(Pageable pageable);

    @Query("select a from Author a where upper(a.fullName) like concat('%', :text,'%' )"+
            "or upper(a.nationality)like concat('%',:text,'%')")
    List<Author> searchByFirstName(@Param("text") String text, Pageable pageable);
}