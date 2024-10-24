package ee.ivkhkdev.helpers;

import ee.ivkhkdev.input.Input;
import ee.ivkhkdev.model.Author;
import ee.ivkhkdev.model.Book;
import ee.ivkhkdev.services.AuthorService;
import ee.ivkhkdev.services.Service;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class AppHelperBookTest {
    Input inputMock;
    AppHelper<Book> appHelperBook;
    Service<Author> authorServiceMock;
    PrintStream defaultOut=System.out;
    ByteArrayOutputStream outMock;

    @BeforeEach
    void setUp() {
        inputMock = Mockito.mock(Input.class);
        authorServiceMock=Mockito.mock(AuthorService.class);
        appHelperBook= new AppHelperBook(inputMock, authorServiceMock);
        outMock = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outMock));
    }

    @AfterEach
    void tearDown() {
        inputMock=null;
        authorServiceMock=null;
        appHelperBook=null;
        System.setOut(defaultOut);
        outMock=null;
    }

    @Test
    void createWithAddAuthor() {
        when(inputMock.nextLine()).thenReturn("Voina i Mir","y");
        Book result= appHelperBook.create();
        Book expected=null;
        assertEquals(expected, result);
    }

    @Test
    void createWithoutAddBook() {
        Author author = new Author("Lev","Tolstoy");
        List<Author> authors= new ArrayList<>();
        authors.add(author);
        when(authorServiceMock.list()).thenReturn(authors);
        when(inputMock.nextLine()).thenReturn("Voina i Mir","n","1","1","2000");
        Book result= appHelperBook.create();
        Book expected=new Book("Voina i Mir",authors,2000);
        assertEquals(result.getTitle(),expected.getTitle());
        assertEquals(result.getAuthor().get(0).getFirstName(),expected.getAuthor().get(0).getFirstName());
        assertEquals(result.getAuthor().get(0).getLastName(),expected.getAuthor().get(0).getLastName());
        assertEquals(result.getPublicationYear(),expected.getPublicationYear());
    }
    @Test
    void printList(){
        Author author = new Author("Lev","Tolstoy");
        List<Author> authors= new ArrayList<>();
        authors.add(author);
        Book book=new Book("Voina i mir",authors,2000);
        List<Book> books= new ArrayList<>();
        books.add(book);
        appHelperBook.printList(books);
        String out=outMock.toString();
        String expected="1. Voina i mir. Lev Tolstoy. 2000";
        assertTrue(out.contains(expected));
    }
}