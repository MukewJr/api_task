package peaksoft.api_task.dto.request;

import lombok.Getter;
import lombok.Setter;
import peaksoft.api_task.enums.Genre;

import java.time.LocalDate;

@Getter
@Setter
public class BookRequest {
    private String name;
    private String description;
    private LocalDate publicationDate;
    private Genre genre;
    private Long authorId;
    private String publisher;
}
