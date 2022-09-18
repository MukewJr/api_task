package peaksoft.api_task.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import peaksoft.api_task.enums.Genre;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookResponse {
    private Long id;
    private String name;
    private String description;
    private int bookYear;
    private LocalDate publicationDate;
    private Genre genre;
    private String publisher;

}
