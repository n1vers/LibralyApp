package ee.ivkhkdev.helpers;

import ee.ivkhkdev.input.Input;
import ee.ivkhkdev.model.Author;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class AppHelperAuthorTest {
    Input inputMock;
    PrintStream defaultOut = System.out;
    AppHelperAuthor authorAppHelper;
    ByteArrayOutputStream outMock;
    @BeforeEach
    void setUp() {
        inputMock = Mockito.mock(Input.class);
        authorAppHelper = new  AppHelperAuthor(inputMock);
        outMock = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outMock));

    }


    @AfterEach
    void tearDown() {
        inputMock = null;
        System.setOut(defaultOut);
        outMock = null;
    }

    @Test
    void create() {
        when(inputMock.nextLine()).thenReturn("Lev","Tolstoy");
        Author actual= authorAppHelper.create();
        Author expected= new Author("Lev","Tolstoy");
        assertEquals(actual.getFirstName(),expected.getFirstName());
        assertEquals(actual.getLastName(),expected.getLastName());
    }

    @Test
    void printList() {
        Author author = new Author("Lev","Tolstoy");
        List<Author> authors= new ArrayList<>();
        authors.add(author);
        boolean result = authorAppHelper.printList(authors);
        boolean expected= true;
        assertTrue(result);
        String expectedString = "1. Lev Tolstoy";
        assertTrue(outMock.toString().contains(expectedString));
    }
}