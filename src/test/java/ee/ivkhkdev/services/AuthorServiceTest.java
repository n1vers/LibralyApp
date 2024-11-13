package ee.ivkhkdev.services;

import ee.ivkhkdev.interfaces.AppHelper;
import ee.ivkhkdev.model.Author;
import ee.ivkhkdev.interfaces.Repository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.internal.verification.VerificationModeFactory.times;

public class AuthorServiceTest {

    private AuthorService authorService;
    private Repository<Author> mockRepository;
    private AppHelper<Author> mockAppHelperAuthor;

    @BeforeEach
    void setUp() {
        // Создаем моки для зависимостей
        mockRepository = Mockito.mock(Repository.class);
        mockAppHelperAuthor = Mockito.mock(AppHelper.class);

        // Инициализируем AuthorService с моками
        authorService = new AuthorService(mockAppHelperAuthor, mockRepository);
    }

    @Test
    void testAddAuthorSuccess() {
        // Подготовка: создать автора и настроить заглушки
        Author mockAuthor = new Author(); // Предполагается, что у класса Author есть конструктор по умолчанию
        when(mockAppHelperAuthor.create()).thenReturn(mockAuthor);

        // Выполняем метод add
        boolean result = authorService.add();

        // Проверка
        assertTrue(result);
        verify(mockRepository, times(1)).save(mockAuthor); // Убедиться, что метод save был вызван один раз
    }

    @Test
    void testAddAuthorFailureWhenAuthorIsNull() {
        // Настроить заглушку, чтобы create возвращал null
        when(mockAppHelperAuthor.create()).thenReturn(null);

        // Выполняем метод add
        boolean result = authorService.add();

        // Проверка
        assertFalse(result);
        verify(mockRepository, never()).save(any()); // Убедиться, что метод save не был вызван
    }

    @Test
    void testAddAuthorExceptionHandling() {
        // Подготовка: создать автора и выбросить исключение при вызове save
        Author mockAuthor = new Author();
        when(mockAppHelperAuthor.create()).thenReturn(mockAuthor);
        doThrow(new RuntimeException("Save error")).when(mockRepository).save(mockAuthor);

        // Выполняем метод add
        boolean result = authorService.add();

        // Проверка
        assertFalse(result); // Ожидаем, что метод вернет false при возникновении исключения
    }

    @Test
    void testPrint() {
        // Подготовка: создать список авторов и настроить заглушки
        List<Author> mockAuthorList = List.of(new Author(), new Author());
        when(mockRepository.load()).thenReturn(mockAuthorList);
        when(mockAppHelperAuthor.printList(mockAuthorList)).thenReturn(true);

        // Выполняем метод print
        boolean result = authorService.print();

        // Проверка
        assertTrue(result);
        verify(mockRepository, times(1)).load(); // Убедиться, что метод load был вызван один раз
        verify(mockAppHelperAuthor, times(1)).printList(mockAuthorList); // Убедиться, что метод printList был вызван один раз
    }

    @Test
    void testList() {
        // Подготовка: создать список авторов и настроить заглушки
        List<Author> mockAuthorList = List.of(new Author(), new Author());
        when(mockRepository.load()).thenReturn(mockAuthorList);

        // Выполняем метод list
        List<Author> result = authorService.list();

        // Проверка
        assertEquals(mockAuthorList, result);
        verify(mockRepository, times(1)).load(); // Убедиться, что метод load был вызван один раз
    }
}