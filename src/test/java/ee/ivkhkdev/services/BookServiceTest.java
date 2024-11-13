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


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

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
        Book mockBook = new Book("Voina i mir", List.of(new Author("Lev","Tolstoy")),2000);
        when(appHelperBook.create()).thenReturn(mockBook);
        boolean result = bookService.add();
        assertTrue(result);
    }
    @Test
    public void testAdd_CreateReturnsNull() {
        when(appHelperBook.create()).thenReturn(null);
        boolean result = bookService.add();
        List<Book> resultListBook = bookService.list();
        assertFalse(result);
        assertTrue(resultListBook.isEmpty());
        verify(repository, never()).save(any());
    }
    @Test
    public void testAdd_AddExistingBook() {
        Book bookMock = new Book("Voina i mir", List.of(new Author("Lev","Tolstoy")),2000);
        when(appHelperBook.create()).thenReturn(bookMock);
        when(repository.load()).thenReturn(List.of(new Book("Voina i mir", List.of(new Author("Lev","Tolstoy")),2000)));
        Book existingBook = new Book("Voina i mir", List.of(new Author("Lev","Tolstoy")),2000);
        boolean result = bookService.add();
        Book resultBook = bookService.list().get(0);
        assertTrue(result);
        assertEquals(existingBook.getTitle(), resultBook.getTitle());
    }
    @Test
    public void testPrint() {
        // Подготовка: создать список книг и настроить заглушки
        List<Book> mockBookList = List.of(new Book(), new Book());
        when(repository.load()).thenReturn(mockBookList);
        when(appHelperBook.printList(mockBookList)).thenReturn(true);
        // Выполняем метод print
        boolean result = bookService.print();
        // Проверка
        assertTrue(result);
        verify(repository, times(1)).load(); // Убедиться, что метод load был вызван один раз
        verify(appHelperBook, times(1)).printList(mockBookList); // Убедиться, что метод printList был вызван один раз
    }

    @Test
    public void testList() {
        // Подготовка: создать список книг и настроить заглушки
        List<Book> mockBookList = List.of(new Book(), new Book());
        when(repository.load()).thenReturn(mockBookList);
        // Выполняем метод list
        List<Book> result = bookService.list();
        // Проверка
        assertEquals(mockBookList, result);
        verify(repository, times(1)).load(); // Убедиться, что метод load был вызван один раз
    }
    @Test
    void testEdit_Successfull(){
        List<Book> books = List.of(new Book(),new Book());
        when(repository.load()).thenReturn(books);
        when(appHelperBook.edit(books)).thenReturn(books);
        boolean result = bookService.edit();
        assertTrue(result);
    }
    @Test
    void testEdit_NotSuccessfull(){
        List<Book> books = List.of(new Book(),new Book());
        when(repository.load()).thenReturn(books);
        when(appHelperBook.edit(books)).thenReturn(null);
        boolean result = bookService.edit();
        assertFalse(result);
    }
}