package peaksoft.api_task.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import peaksoft.api_task.enums.Gender;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthorResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private String fullName;
    private String nationality;
    private Gender gender;
    private int age;
}
