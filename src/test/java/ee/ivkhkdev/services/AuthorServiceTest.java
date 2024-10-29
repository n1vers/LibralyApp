package ee.ivkhkdev.services;

import ee.ivkhkdev.helpers.AppHelper;
import ee.ivkhkdev.helpers.AppHelperAuthor;
import ee.ivkhkdev.model.Author;
import ee.ivkhkdev.repositories.Repository;
import ee.ivkhkdev.repositories.Storage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.internal.verification.VerificationModeFactory.times;

class AuthorServiceTest {
    List<Author> authors;
    AppHelper<Author> appHelperAuthorMock;
    Repository<Author> repositoryMock;
    Service<Author> authorService;
    @BeforeEach
    void setUp() {
        authors = new ArrayList<>();
        appHelperAuthorMock = Mockito.mock(AppHelperAuthor.class);
        repositoryMock = Mockito.mock(Storage.class); // мокируем зависимость
        //создаем тестируемый объект
        authorService = new AuthorService(authors,appHelperAuthorMock,repositoryMock);
    }
    @AfterEach
    void tearDown() {
    }

    @Test
    void testAdd_SuccessfulAdd() {
        Author author = new Author("Lev","Tolstoy");
        authors = new ArrayList<>();
        authors.add(author); // авторы, которые передаем в сервис
        Author mockAuthor = new Author("Ivan","Turgenev");
        // appHelperAuthorMock создаст нового автора
        when(appHelperAuthorMock.create()).thenReturn(mockAuthor);
        boolean result = authorService.add();//создаст нового автора и добавит его в authors
        assertTrue(result);
        // проверим, добавился ли в authors новый автор "Ivan"
        assertTrue(authors.get(1).getFirstName().equals("Ivan"));
        // Проверяем, что метод save() вызывался один раз
        verify(repositoryMock,times(1)).save(any(Author.class));

    }
    @Test
    void testAdd_AddExistingAuthor(){
        Author existingAuthor = new Author();
        authors.add(existingAuthor);
        Author newAuthor = new Author();
        when(appHelperAuthorMock.create()).thenReturn(newAuthor);
        boolean result = authorService.add();
        assertTrue(result);
        assertEquals(2,authors.size());
        assertEquals(newAuthor, authors.get(1));
        verify(repositoryMock,times(1)).save(newAuthor);
    }

    @Test
    void restAdd_CreateReturnsNull(){
        authors = new ArrayList<>();
        when(appHelperAuthorMock.create()).thenReturn(null);
        boolean result = authorService.add();
        assertFalse(result);
        assertTrue(authors.isEmpty());
        verify(repositoryMock,never()).save((any()));
    }
    @Test
    public void testPrint() {
        when(appHelperAuthorMock.printList(authors)).thenReturn(true);
        boolean result = authorService.print();
        assertTrue(result);
        verify(appHelperAuthorMock, times(1)).printList(authors);
    }


    @Test
    void testList() {
        List<Author> result = authorService.list();
        assertSame(authors, result);
    }
}