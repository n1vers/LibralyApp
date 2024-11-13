package ee.ivkhkdev.helpers;

import ee.ivkhkdev.interfaces.AppHelper;
import ee.ivkhkdev.interfaces.Input;
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

class AuthorAppHelperTest {
    Input inputMock;
    AppHelper<Author> authorAppHelper;
    PrintStream defaultOut = System.out;
    ByteArrayOutputStream outMock;
    @BeforeEach
    void setUp() {
        inputMock = Mockito.mock(Input.class);
        authorAppHelper = new AuthorAppHelper(inputMock);
        outMock = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outMock));
    }

    @AfterEach
    void tearDown() {
        inputMock = null;
        System.setOut(defaultOut);
        outMock=null;
    }

    @Test
    void create_ShouldReturnAuthorWithValidInput() {
        when(inputMock.nextLine()).thenReturn("Lev","Tolstoy");
        Author actual = authorAppHelper.create();
        Author expected = new Author("Lev","Tolstoy");
        assertEquals(actual.getFirstName(), expected.getFirstName());
        assertEquals(actual.getLastName(), expected.getLastName());
    }
    @Test
    void create_ShouldReturnNullWhenExceptionOccurs() {
        // Arrange
        when(inputMock.nextLine()).thenThrow(new RuntimeException("Input error"));
        // Act
        Author author = authorAppHelper.create();
        // Assert
        assertNull(author);
    }

    @Test
    void printList_ShouldPrintAuthorsWhenListIsNotEmpty() {
        Author author = new Author("Lev","Tolstoy");
        List<Author> authors = new ArrayList<>();
        authors.add(author);
        boolean result = authorAppHelper.printList(authors);
        boolean expected = true;
        assertTrue(result);
        String expectedString = "1. Lev Tolstoy";
        assertTrue(outMock.toString().contains(expectedString));
    }
    @Test
    void printList_ShouldReturnFalseWhenListIsEmpty() {
        // Arrange
        List<Author> authors = List.of();
        // Act
        boolean result = authorAppHelper.printList(authors);
        // Assert
        assertFalse(result);
    }
    @Test
    void printList_ShouldReturnFalseWhenExceptionOccurs() {
        // Arrange
        List<Author> authors = null; // Simulate an exception scenario
        // Act
        boolean result = authorAppHelper.printList(authors);
        // Assert
        assertFalse(result);
    }
}