package peaksoft.api_task.api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import peaksoft.api_task.dto.request.BookRequest;
import peaksoft.api_task.dto.response.BookResponse;
import peaksoft.api_task.dto.response.SimpleResponse;
import peaksoft.api_task.entity.Book;
import peaksoft.api_task.service.BookService;

import java.util.List;

@RestController
@RequestMapping("/api/book")
@RequiredArgsConstructor
public class BookApi {
    private final BookService service;

    @PostMapping("/save")
    public BookResponse save(@RequestBody BookRequest bookRequest){
        return service.saveBook(bookRequest);
    }

    @GetMapping("/all")
    public List<BookResponse> getBooks(){
        return service.finAll();
    }

    @GetMapping("/get/{bookId}")
    public BookResponse getById(@PathVariable Long bookId){
        return service.getById(bookId);
    }

    @DeleteMapping("/delete/{bookId}")
    public SimpleResponse delete(@PathVariable Long bookId){
        return service.delete(bookId);
    }

    @PutMapping("/update/{bookId}")
    public BookResponse update(@PathVariable Long bookId,@RequestBody BookRequest bookRequest){
        return service.updateBookById(bookId,bookRequest);
    }
}





//    @PostMapping
//    public BookResponse save(@RequestBody BookRequest request) {
//        return service.save(request);
//    }
//
//
//    @GetMapping("{id}")
//    public BookResponse getById(@PathVariable("id") Long id) {
//        return service.getById(id);
//    }
//
//    @PutMapping("{id}")
//    public BookResponse update(@PathVariable("id")Long id, @RequestBody BookRequest request) {
//        return service.update(id,request);
//    }
//
//    @DeleteMapping("{id}")
//    public BookResponse delete(@PathVariable("id")Long id) {
//        return service.deleteById(id);
//    }
//
//    @GetMapping
//    public List<Book> findAll() {
//        return service.findAllBooks();
//    }
//
//
//    @GetMapping("/getByAuthorId/{id}")
//    public BookResponse getBookById(@PathVariable("id") Long id) {
//        return service.getBookByAuthorId(id);
//    }

