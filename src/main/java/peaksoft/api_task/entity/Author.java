package peaksoft.api_task.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import peaksoft.api_task.enums.Gender;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "authors")
@Getter
@Setter
@NoArgsConstructor
public class Author {
    @Id
    @GeneratedValue(generator = "author_gen",strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "author_gen",sequenceName = "author_seq")
    private Long id;
    private String fullName;
    private String nationality;
    private Gender gender;
    private LocalDate dateOfBirth;

   @OneToMany(cascade = CascadeType.ALL,mappedBy = "author")
   @JsonIgnore
    private List<Book> books;

    public Author(String fullName, String nationality, Gender gender, LocalDate dateOfBirth) {
        this.fullName = fullName;
        this.nationality = nationality;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;

    }

    public void addBook(Book book) {
        if(books == null) {
            books = new ArrayList<>();
        }
        this.books.add(book);
    }
}
