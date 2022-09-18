package peaksoft.api_task.dto.request;

import lombok.Getter;
import lombok.Setter;
import peaksoft.api_task.enums.Gender;

import java.time.LocalDate;
@Getter
@Setter
public class AuthorRequest {
    private String firstName;
    private String lastName;
    private String nationality;
    private Gender gender;
    private LocalDate dateOfBirth;
}
