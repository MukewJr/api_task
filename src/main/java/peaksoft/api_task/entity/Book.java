package peaksoft.api_task.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import peaksoft.api_task.enums.Genre;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "books")
@Getter
@Setter
@NoArgsConstructor
public class Book {
    @Id
    @GeneratedValue(generator = "book_gen",strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "book_gen",sequenceName = "book_seq")
    private Long id;
    private String name;
    private String description;
    private int bookYear;
    private LocalDate publicationDate;
    private Genre genre;
    private String publisher;

    @ManyToOne(cascade = {CascadeType.REFRESH,CascadeType.DETACH,CascadeType.PERSIST,CascadeType.MERGE})
    private Author author;
}
