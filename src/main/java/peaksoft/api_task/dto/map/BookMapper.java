package peaksoft.api_task.dto.map;

import org.springframework.stereotype.Component;
import peaksoft.api_task.dto.request.BookRequest;
import peaksoft.api_task.dto.response.AuthorResponse;
import peaksoft.api_task.dto.response.BookResponse;
import peaksoft.api_task.entity.Author;
import peaksoft.api_task.entity.Book;

import java.time.LocalDate;
import java.time.Period;

@Component
public class BookMapper {
    public Book mapToEntity(BookRequest bookRequest){
        Book book =new Book();
        book.setName(bookRequest.getName());
        book.setBookYear(book.getBookYear());
        book.setGenre(bookRequest.getGenre());
        book.setDescription(bookRequest.getDescription());
        book.setPublisher(bookRequest.getPublisher());
        book.setPublicationDate(bookRequest.getPublicationDate());
        return book;
    }

    public BookResponse mapToResponse(Book book){
        BookResponse bookResponse=new BookResponse();
        bookResponse.setId(book.getId());
        bookResponse.setName(book.getName());
        bookResponse.setPublisher(book.getPublisher());
        bookResponse.setDescription(book.getDescription());
        bookResponse.setGenre(book.getGenre());
        bookResponse.setPublicationDate(book.getPublicationDate());
        bookResponse.setBookYear(Period.between(book.getPublicationDate(),LocalDate.now()).getYears());
        return bookResponse;
    }

    public Book updateBook(Book book,BookRequest bookRequest){
        book.setName(bookRequest.getName());
        book.setPublisher(book.getPublisher());
        book.setGenre(bookRequest.getGenre());
        book.setDescription(book.getDescription());
        book.setPublicationDate(bookRequest.getPublicationDate());
        //book.setAuthor(bookRequest.getAuthorId());
        return book;
    }
}
