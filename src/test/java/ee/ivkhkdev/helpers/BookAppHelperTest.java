package ee.ivkhkdev.helpers;

import ee.ivkhkdev.interfaces.AppHelper;
import ee.ivkhkdev.interfaces.Input;
import ee.ivkhkdev.model.Author;
import ee.ivkhkdev.model.Book;
import ee.ivkhkdev.repositories.Storage;
import ee.ivkhkdev.services.AuthorService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BookAppHelperTest {
    Input inputMock;
    AuthorAppHelper authorAppHelper;
    AppHelper<Book> bookAppHelper;
    AuthorService authorService;
    PrintStream defaultOut = System.out;
    ByteArrayOutputStream outMock;
    @BeforeEach
    void setUp() {
        inputMock = Mockito.mock(Input.class);
        authorAppHelper = mock(AuthorAppHelper.class);
        Storage<Author> storage = Mockito.mock(Storage.class);
        authorService = new AuthorService(authorAppHelper,storage);
        bookAppHelper = new BookAppHelper(inputMock, authorService);
        outMock = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outMock));
    }

    @AfterEach
    void tearDown() {
        inputMock = null;
        authorService = null;
        bookAppHelper = null;
        System.setOut(defaultOut);
        outMock = null;
    }

    @Test
    void create_ShouldReturnNullWhenAddAuthorChoiceIsY() {
        when(inputMock.nextLine()).thenReturn("Voina i mir", "y");
        Book result = bookAppHelper.create();
        Book expected = null;
        assertEquals(result,expected);

    }
    @Test
    void create_ShouldReturnBookWithValidInput() {
        Author author = new Author("Lev","Tolstoy");
        List<Author> authors = new ArrayList<>();
        authors.add(author);
        when(authorService.list()).thenReturn(authors);
        when(inputMock.nextLine()).thenReturn("Voina i mir", "n","1","1","2000");
        Book result = bookAppHelper.create();
        Book expected = new Book("Voina i mir",authors,2000);
        assertEquals(result.getTitle(),expected.getTitle());
        assertEquals(result.getAuthor().get(0).getFirstName(),expected.getAuthor().get(0).getFirstName());
        assertEquals(result.getAuthor().get(0).getLastName(),expected.getAuthor().get(0).getLastName());
        assertEquals(result.getPublicationYear(),expected.getPublicationYear());
    }

    @Test
    void printList_ShouldReturnTrueWhenBooksExist() {
        Author author = new Author("Lev","Tolstoy");
        List<Author> authors = new ArrayList<>();
        authors.add(author);
        Book book = new Book("Voina i mir",authors,2000);
        List<Book>books = new ArrayList<>();
        books.add(book);
        bookAppHelper.printList(books);
        String out = outMock.toString();
        String expected = "1. Voina i mir. Lev Tolstoy. 2000";
        assertTrue(out.contains(expected));
    }
    @Test
    void printList_ShouldReturnFalseWhenBooksListIsEmpty() {
        // Arrange
        List<Book> books = new ArrayList<>();
        // Act
        boolean result = bookAppHelper.printList(books);
        // Assert
        assertFalse(result);
    }
    @Test
    void edit_ShouldUpdateBookDetailsWhenValidInput(){
        List<Author> authors = List.of(new Author("Lev","Tolstoy"), new Author("Ivan","Turgenev"));
        List<Book> books = List.of(new Book("Voina i mir",List.of(authors.get(0)),2000));
        when(inputMock.nextLine()).thenReturn(
                "1", //numberBook
                "y", //Изменить: y
                "Otsi i deti",// new title
                "y",//изменить авторов: y
                "1",//количество авторов в книге: 1
                "2",//numberAuthor: 2
                "y",//изменить год издания: y
                "2001"//
        );
       // when(authorService.getAppHelperAuthor().printList(authors)).thenReturn(true);
        when(authorAppHelper.printList(authors)).thenReturn(true);
        when(authorService.list()).thenReturn(authors);
        List<Book> result = bookAppHelper.edit(books);

        assertEquals(result.get(0).getTitle(), "Otsi i deti");
        assertEquals(1, result.get(0).getAuthor().size());
        assertEquals("Ivan", result.get(0).getAuthor().get(0).getFirstName());
        assertEquals(2001, result.get(0).getPublicationYear());
    }
    @Test
    void edit_ShouldReturnNullWhenExceptionOccurs(){
        // Arrange
        List<Book> books = new ArrayList<>();
        when(inputMock.nextLine()).thenThrow(new RuntimeException("Input error"));
        // Act
        List<Book> result = bookAppHelper.edit(books);
        // Assert
        assertNull(result);
    }
}