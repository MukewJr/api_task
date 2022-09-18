package peaksoft.api_task.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import peaksoft.api_task.dto.request.AuthorRequest;
import peaksoft.api_task.dto.response.AuthorResponse;
import peaksoft.api_task.dto.response.PaginationResponse;
import peaksoft.api_task.dto.response.SimpleResponse;
import peaksoft.api_task.entity.Author;
import peaksoft.api_task.enums.Gender;
import peaksoft.api_task.exception.NotFoundException;
import peaksoft.api_task.dto.map.AuthorMapper;
import peaksoft.api_task.repository.AuthorRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorService {
    private final AuthorRepository authorRepository;
    private final AuthorMapper authorMapper;

    public AuthorResponse save(AuthorRequest authorRequest){
        Author author=authorMapper.mapToEntity(authorRequest);
        Author author1=authorRepository.save(author);
        return authorMapper.mapToResponse(author1);
    }

    public List<AuthorResponse> findAll(){
        List<AuthorResponse> authorResponses=new ArrayList<>();
        for (Author author:
             authorRepository.findAll()) {
            authorResponses.add(authorMapper.mapToResponse(author));
        }
        return authorResponses;
    }

    public AuthorResponse getById(Long authorId){
        Author author=getAuthorById(authorId);
        return authorMapper.mapToResponse(author);
    }

    private Author getAuthorById(Long authorId){
        return authorRepository.findById(authorId).
                orElseThrow(()->new NotFoundException("Author with id "+authorId+ " not founded"));
    }

    public AuthorResponse updateAuthorById(Long authorId,AuthorRequest authorRequest){
        Author author=getAuthorById(authorId);
        String currentFullName= author.getFullName();
        String newFullName=authorRequest.getFirstName()+ " " +authorRequest.getLastName();
        if (newFullName!= null && !newFullName.equals(currentFullName)){
            author.setFullName(newFullName);
        }
        String currentNationality= author.getNationality();
        String newNationality=authorRequest.getNationality();
        if (newNationality!= null && !newNationality.equals(currentNationality)){
            author.setNationality(newNationality);
        }
        String currentGender= String.valueOf(author.getGender());
        String newGender=String.valueOf(authorRequest.getGender());
        if (newGender!= null && !newGender.equals(currentGender)){
            author.setGender(Gender.valueOf(newGender));
        }
        String currentDateOfBirth= String.valueOf(author.getDateOfBirth());
        String newDateOfBirth=String.valueOf(authorRequest.getDateOfBirth());
        if (newDateOfBirth!= null && !newDateOfBirth.equals(currentDateOfBirth)){
            author.setDateOfBirth(LocalDate.parse(newDateOfBirth));
        }
        authorMapper.updateAuthor(author,authorRequest);
        authorRepository.save(author);
        return authorMapper.mapToResponse(author);
    }


    public SimpleResponse delete(Long authorId){
        boolean exist=authorRepository.existsById(authorId);
        if (!exist){
            throw new NotFoundException("Author with id "+authorId+" not found");
        }
        authorRepository.deleteById(authorId);
        return new SimpleResponse("DELETED","Author with id "+authorId +" was deleted");
    }

    public PaginationResponse getAuthorPagination(String text, int page, int size) {
        PaginationResponse paginationResponse=new PaginationResponse();
        Pageable pageable= PageRequest.of(page-1,size);
        List<AuthorResponse> authorResponses=new ArrayList<>();
        Page<Author> authors=authorRepository.findAll(pageable);
        for (Author author :
        authors) {
            authorResponses.add(authorMapper.mapToResponse(author));
        }

        paginationResponse.setAuthorResponseList(authorResponses);
        paginationResponse.setCurrentPage(pageable.getPageNumber()+1);
        paginationResponse.setTotalPages(authors.getTotalPages());
        return paginationResponse;
    }

    private List<Author> search(String name,Pageable pageable){
        String text=name==null? "" :name;
        return authorRepository.searchByFirstName(text.toUpperCase(),pageable);
    }

}
