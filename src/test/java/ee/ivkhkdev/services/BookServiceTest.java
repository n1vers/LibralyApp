package ee.ivkhkdev.services;

import ee.ivkhkdev.interfaces.AppHelper;
import ee.ivkhkdev.helpers.BookAppHelper;
import ee.ivkhkdev.interfaces.Service;
import ee.ivkhkdev.model.Author;
import ee.ivkhkdev.model.Book;
import ee.ivkhkdev.interfaces.Repository;
import ee.ivkhkdev.repositories.Storage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static javax.swing.UIManager.get;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.internal.verification.VerificationModeFactory.times;

class BookServiceTest {

    AppHelper<Book> appHelperBook;
    Repository<Book> repository;
    Service<Book> bookService;

    @BeforeEach
    void setUp() {
        appHelperBook = Mockito.mock(BookAppHelper.class);
        repository = Mockito.mock(Storage.class);
        bookService = new BookService(appHelperBook, repository);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testAdd_SuccessfulAdd() {
        Book book = new Book("Voina i Mir", List.of(new Author("Lev", "Tolstoy")), 2000);
        when(appHelperBook.create()).thenReturn(book);
        boolean result = bookService.add();
        assertTrue(result);
        verify(repository, times(1)).save(book); // проверка, что книга сохраняется
    }

    @Test
    void testAdd_CreateReturnNull() {
        when(appHelperBook.create()).thenReturn(null);
        boolean result = bookService.add();
        List<Book> resultBook = bookService.list();
        assertFalse(result);
        assertTrue(resultBook.isEmpty());
        verify(repository, never()).save(any()); // проверка, что не сохраняется ничего
    }

    @Test
    void testAdd_AddExistingBook() {
        Book book = new Book("Voina i Mir", List.of(new Author("Lev", "Tolstoy")), 2000);
        when(appHelperBook.create()).thenReturn(book);
        when(repository.load()).thenReturn(List.of(book)); // имитируем, что книга уже есть в репозитории

        boolean result = bookService.add();
        assertFalse(result); // проверка, что добавление неуспешно

        verify(repository,times(1)).save(book); // проверка, что `save` не вызывается
    }


    @Test
    void testPrint() {
        List<Book> books = List.of(new Book("Voina i Mir", List.of(new Author("Lev", "Tolstoy")), 2000));
        when(repository.load()).thenReturn(books); // загружаем список книг из репозитория
        when(appHelperBook.printList(books)).thenReturn(true);

        boolean result = bookService.print();
        assertTrue(result);
        verify(appHelperBook, times(1)).printList(books);
    }

    @Test
    void list() {
        List<Book> books = List.of(new Book("Voina i mir", List.of(new Author("Lev", "Tolstoy")), 2000));
        when(repository.load()).thenReturn(books); // должно возвращаться из репозитория, а не из appHelperBook

        List<Book> resultBooks = bookService.list();
        assertEquals(books.size(), resultBooks.size());
        assertEquals(books.get(0).getTitle(), resultBooks.get(0).getTitle());
    }
}
