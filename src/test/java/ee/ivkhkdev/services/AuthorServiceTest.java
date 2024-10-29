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
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

class AuthorServiceTest {

    @BeforeEach
    void setUp() {

    }

    @AfterEach
    void tearDown() {

    }

    @Test
    void add() {
        Author author = new Author("Lev","Tolstoy");
        List<Author> authors = new ArrayList<>();
        authors.add(author); // авторы, которые передаем в сервис
        AppHelper<Author> appHelperAuthorMock = Mockito.mock(AppHelperAuthor.class);
        // appHelperAuthorMock создаст нового автора
        when(appHelperAuthorMock.create()).thenReturn(new Author("Ivan","Turgenev"));
        Repository<Author> repositoryMock = Mockito.mock(Storage.class); // мокируем зависимость
        //создаем тестируемый объект
        Service<Author> authorService = new AuthorService(authors,appHelperAuthorMock,repositoryMock);
        boolean result = authorService.add();//создаст нового автора и добавит его в authors
        assertTrue(result);
        // проверим, добавился ли в authors новый автор "Ivan"
        assertTrue(authors.get(1).getFirstName().equals("Ivan"));
        // Проверяем, что метод save() вызывался один раз
        verify(repositoryMock,times(1)).save(any(Author.class));
    }

    @Test
    void print() {
        AppHelper<Author> appHelperAuthorMock = Mockito.mock(AppHelperAuthor.class);
        
    }

    @Test
    void list() {

    }
}