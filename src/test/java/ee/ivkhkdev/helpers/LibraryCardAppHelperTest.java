package ee.ivkhkdev.helpers;

import ee.ivkhkdev.interfaces.Input;
import ee.ivkhkdev.interfaces.Service;
import ee.ivkhkdev.model.Author;
import ee.ivkhkdev.model.Book;
import ee.ivkhkdev.model.LibraryCard;
import ee.ivkhkdev.model.User;
import ee.ivkhkdev.services.BookService;
import ee.ivkhkdev.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class LibraryCardAppHelperTest {

    private LibraryCardAppHelper libraryCardAppHelper;
    private Input mockInput;
    private Service<Book> mockBookService;
    private Service<User> mockUserService;

    @BeforeEach
    void setUp() {
        // Создаем моки для зависимостей
        mockInput = Mockito.mock(Input.class);
        mockBookService = Mockito.mock(BookService.class);
        mockUserService = Mockito.mock(UserService.class);

        // Инициализируем LibraryCardAppHelper с моками
        libraryCardAppHelper = new LibraryCardAppHelper(mockInput, mockBookService, mockUserService);
    }

    @Test
    void testCreateSuccess() {
        // Настройка моков для успешного создания LibraryCard
        Book mockBook = new Book();
        User mockUser = new User();
        when(mockBookService.print()).thenReturn(true);
        when(mockBookService.list()).thenReturn(List.of(mockBook));
        when(mockInput.nextLine()).thenReturn("1"); // Выбор книги
        when(mockUserService.print()).thenReturn(true);
        when(mockUserService.list()).thenReturn(List.of(mockUser));
        when(mockInput.nextLine()).thenReturn("1"); // Выбор пользователя

        // Выполнение метода create
        LibraryCard result = libraryCardAppHelper.create();

        // Проверка результата
        assertNotNull(result);
        assertEquals(mockBook, result.getBook());
        assertEquals(mockUser, result.getUser());
        assertEquals(LocalDate.now(), result.getBorrowedDate());
    }

    @Test
    void testCreateBookServicePrintFails() {
        // Настройка моков, чтобы bookService.print() вернул false
        when(mockBookService.print()).thenReturn(false);

        // Выполнение метода create
        LibraryCard result = libraryCardAppHelper.create();

        // Проверка результата
        assertNull(result);
    }

    @Test
    void testCreateUserServicePrintFails() {
        // Настройка моков для успешного выбора книги, но пользовательский сервис не удается
        Book mockBook = new Book();
        when(mockBookService.print()).thenReturn(true);
        when(mockBookService.list()).thenReturn(List.of(mockBook));
        when(mockInput.nextLine()).thenReturn("1"); // Выбор книги
        when(mockUserService.print()).thenReturn(false);

        // Выполнение метода create
        LibraryCard result = libraryCardAppHelper.create();

        // Проверка результата
        assertNull(result);
    }


    @Test
    void testPrintListWithNoReturnDates() {
        // Подготовка: создать список LibraryCard с незавершенными книгами
        Book book = new Book("Test Book",List.of(new Author("firstnameAuthor","lastnameAuthor")), 2020);
        User user = new User("John", "Doe","123456");
        LibraryCard libraryCard = new LibraryCard(book, user, LocalDate.now(), null); // Не возвращено
        List<LibraryCard> libraryCards = List.of(libraryCard);

        // Выполняем метод printList
        boolean result = libraryCardAppHelper.printList(libraryCards);

        // Проверка
        assertTrue(result);
    }

    @Test
    void testPrintListWithAllReturnDates() {
        // Подготовка: создать список LibraryCard с завершенными книгами
        Book book = new Book("Test Book",List.of(new Author("firstnameAuthor","lastnameAuthor")), 2020);
        User user = new User("John", "Doe","123456");
        LibraryCard libraryCard = new LibraryCard(book, user, LocalDate.now(), LocalDate.now()); // Книга возвращена
        List<LibraryCard> libraryCards = List.of(libraryCard);

        // Выполняем метод printList
        boolean result = libraryCardAppHelper.printList(libraryCards);

        // Проверка
        assertFalse(result);
    }

    @Test
    void testReturnBackWithValidSelection() {
        // Подготовка: создать список LibraryCard с незавершенной книгой
        Book book = new Book("Test Book",List.of(new Author("firstnameAuthor","lastnameAuthor")), 2020);
        User user = new User("John", "Doe","123456");
        LibraryCard libraryCard = new LibraryCard(book, user, LocalDate.now(), null);
        List<LibraryCard> libraryCards = List.of(libraryCard);

        // Имитация ввода номера книги
        when(mockInput.nextLine()).thenReturn("1");

        // Выполняем метод returnBack
        List<LibraryCard> result = libraryCardAppHelper.returnBack(libraryCards);

        // Проверка
        assertNotNull(result);
        assertNotNull(result.get(0).getReturnBookDate()); // Дата возврата должна быть установлена
    }

    @Test
    void testReturnBackWithNoBooksToReturn() {
        // Подготовка: создать список LibraryCard с завершенной книгой
        Book book = new Book("Test Book",List.of(new Author("firstnameAuthor","lastnameAuthor")), 2020);
        User user = new User("John", "Doe","123456");
        LibraryCard libraryCard = new LibraryCard(book, user, LocalDate.now(), LocalDate.now()); // Книга возвращена
        List<LibraryCard> libraryCards = List.of(libraryCard);

        // Выполняем метод returnBack
        List<LibraryCard> result = libraryCardAppHelper.returnBack(libraryCards);

        // Проверка
        assertNull(result); // Ожидаем null, так как нет книг для возврата
    }
}