package peaksoft.api_task.dto.map;

import org.springframework.stereotype.Component;
import peaksoft.api_task.dto.request.AuthorRequest;
import peaksoft.api_task.dto.response.AuthorResponse;
import peaksoft.api_task.entity.Author;

import java.time.LocalDate;
import java.time.Period;

@Component
public class AuthorMapper {
    public Author mapToEntity(AuthorRequest authorRequest){
        Author author=new Author();
        author.setFullName(authorRequest.getFirstName()+ " " + authorRequest.getLastName());
        author.setNationality(authorRequest.getNationality());
        author.setDateOfBirth(authorRequest.getDateOfBirth());
        author.setGender(authorRequest.getGender());
        return author;
    }

    public AuthorResponse mapToResponse(Author author){
        AuthorResponse authorResponse=new AuthorResponse();
        authorResponse.setId(author.getId());
        authorResponse.setFullName(author.getFullName());
        String[] array=author.getFullName().split(" ", 2);
        authorResponse.setFirstName(array[0]);
        authorResponse.setLastName(array[1]);
        authorResponse.setNationality(author.getNationality());
        authorResponse.setGender(author.getGender());
        authorResponse.setAge(Period.between(author.getDateOfBirth(), LocalDate.now()).getYears());
        return authorResponse;
    }

    public Author updateAuthor(Author author,AuthorRequest authorRequest){
        author.setFullName(authorRequest.getFirstName()+ " "+ authorRequest.getLastName());
        author.setNationality(author.getNationality());
        author.setDateOfBirth(authorRequest.getDateOfBirth());
        author.setGender(authorRequest.getGender());
        return author;
    }
}
