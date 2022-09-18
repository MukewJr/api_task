package peaksoft.api_task.service;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import peaksoft.api_task.dto.request.AuthorRequest;
import peaksoft.api_task.dto.request.BookRequest;
import peaksoft.api_task.dto.response.AuthorResponse;
import peaksoft.api_task.dto.response.BookResponse;
import peaksoft.api_task.dto.response.SimpleResponse;
import peaksoft.api_task.entity.Author;
import peaksoft.api_task.entity.Book;
import peaksoft.api_task.dto.map.BookMapper;
import peaksoft.api_task.enums.Gender;
import peaksoft.api_task.enums.Genre;
import peaksoft.api_task.exception.NotFoundException;
import peaksoft.api_task.repository.AuthorRepository;
import peaksoft.api_task.repository.BookRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final BookMapper bookMapper;

    public BookResponse saveBook(BookRequest bookRequest){
        Book book=bookMapper.mapToEntity(bookRequest);
        Author author=authorRepository.findById(bookRequest.getAuthorId()).get();
        author.addBook(book);
        book.setAuthor(author);
        Book book1=bookRepository.save(book);
        return bookMapper.mapToResponse(book1);
    }

    public Book getBookById(Long bookId){
       return bookRepository.findById(bookId).orElseThrow(()->new NotFoundException("Book with this id "+ bookId+
                " not founded"));
    }

    public BookResponse getById(Long bookId){
        Book book=getBookById(bookId);
        return bookMapper.mapToResponse(book);
    }

    public BookResponse updateBookById(Long bookId, BookRequest bookRequest){
        Book book =getBookById(bookId);
        String currentName= book.getName();
        String newName=bookRequest.getName();
        if (newName!= null && !newName.equals(currentName)){
            book.setName(newName);
        }
        String currentDescription= book.getDescription();
        String newDescription= bookRequest.getDescription();
        if (newDescription!= null && !newDescription.equals(currentDescription)){
            book.setDescription(newDescription);
        }
        String currentGenre= String.valueOf(book.getGenre());
        String newGenre=String.valueOf(bookRequest.getGenre());
        if (newGenre!= null && !newGenre.equals(currentGenre)){
            book.setGenre(Genre.valueOf(newGenre));
        }
        String currentPublicationDate= String.valueOf(book.getPublicationDate());
        String newPublicationDate=String.valueOf(bookRequest.getPublicationDate());
        if (newPublicationDate!= null && !newPublicationDate.equals(currentPublicationDate)){
            book.setPublicationDate(LocalDate.parse(newPublicationDate));
        }
        bookMapper.updateBook(book,bookRequest);
        bookRepository.save(book);
        return bookMapper.mapToResponse(book);
    }

    public SimpleResponse delete(Long bookId){
        boolean exist=bookRepository.existsById(bookId);
        if (!exist){
            throw new NotFoundException("Book with id "+bookId+" not found");
        }
        authorRepository.deleteById(bookId);
        return new SimpleResponse("DELETED","Author with id "+bookId +" was deleted");
    }

    public List<BookResponse> finAll(){
        List<BookResponse> bookResponses=new ArrayList<>();
        for (Book book :
                bookRepository.findAll()) {
            bookResponses.add(bookMapper.mapToResponse(book));
        }
        return bookResponses;
    }
}
