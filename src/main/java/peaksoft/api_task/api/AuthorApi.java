package peaksoft.api_task.api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import peaksoft.api_task.dto.request.AuthorRequest;
import peaksoft.api_task.dto.response.AuthorResponse;
import peaksoft.api_task.dto.response.PaginationResponse;
import peaksoft.api_task.dto.response.SimpleResponse;
import peaksoft.api_task.entity.Author;
import peaksoft.api_task.service.AuthorService;

import java.util.List;
@RestController
@RequestMapping("/api/author")
@RequiredArgsConstructor
public class AuthorApi {
    private final AuthorService service;

    @PostMapping("/save")
    public AuthorResponse save(@RequestBody AuthorRequest request) {
        return service.save(request);
    }

    @GetMapping("/get/{authorId}")
    public AuthorResponse getById(@PathVariable Long authorId) {
        return service.getById(authorId);
    }

    @PutMapping("/update/{authorId}")
    public AuthorResponse update(@PathVariable Long authorId, @RequestBody AuthorRequest request) {
        return service.updateAuthorById(authorId,request);
    }

    @DeleteMapping("/delete/{authorId}")
    public SimpleResponse delete(@PathVariable Long authorId) {
        return service.delete(authorId);
    }

    @GetMapping("/all")
    public List<AuthorResponse> findAll() {
        return service.findAll();
    }

    @GetMapping("/page")
    public PaginationResponse getPaginationOfAuthor(@RequestParam(name = "text",required = false)String text,
                                                    @RequestParam int page,
                                                    @RequestParam int size){
        return service.getAuthorPagination(text,page,size);
    }
}
