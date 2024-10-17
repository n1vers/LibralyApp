package ee.ivkhkdev;
import ee.ivkhkdev.helpers.AppInputHelper;
import ee.ivkhkdev.interfaces.Input;
import ee.ivkhkdev.model.Author;
import ee.ivkhkdev.model.Book;
import ee.ivkhkdev.model.User;
import ee.ivkhkdev.services.BookService;
import ee.ivkhkdev.storages.Storage;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class AppTest {

    private Input inputMock; // Мок объекта Input
    private ByteArrayOutputStream outContentMock; // Для перехвата вывода консоли
    private final PrintStream originalOut = System.out; // Оригинальный System.out

    @BeforeEach
    public void setUp() {
        // Мокируем Input
        inputMock = Mockito.mock(Input.class);
        outContentMock = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContentMock));

    }

    @AfterEach
    public void tearDown() {
        // Восстанавливаем оригинальный System.out после каждого теста
        System.setOut(originalOut);
    }

    @Test
    public void testExitProgram() {
        // Настраиваем поведение nextInt для завершения программы
        when(inputMock.nextLine()).thenReturn("0");
        // Перехватываем вывод в консоль
        // Создаем объект App с мокированным input
        App app = new App(inputMock);
        // Запускаем метод run
        app.run();
        assertTrue(outContentMock.toString().contains("До свидания!"));
    }

    @Test
    public void testInvalidTaskNumber() {
        // Настраиваем поведение nextInt для неверного ввода и последующего завершения программы
        when(inputMock.nextLine()).thenReturn("5", "0"); // Сначала неверный ввод, затем завершение
        // Создаем объект App с мокированным input
        App app = new App(inputMock);
        // Запускаем метод run
        app.run();
        assertTrue(outContentMock.toString().contains("Выберите номер из списка задач!") && outContentMock.toString().contains("До свидания!"));
    }
    @Test
    public void testAddUser() {

        when(inputMock.nextLine()).thenReturn("1", "Ivan","Ivanov", "56565656","0");
        // Создаем объект App с мокированным input
        App app = new App(inputMock);
        // Запускаем метод run
        app.run();
        User expected = new User("Ivan", "Ivanov", "56565656");
        // Проверяем, что фактический вывод совпадает с ожидаемым
        assertTrue(users.get(0).getFirstName().equals("Ivan"));
    }
    @Test
    void testPrintListUsers() {
        when(inputMock.nextLine()).thenReturn("2","0");

        App app = new App(inputMock);
        app.run();
        String contentString = outContentMock.toString();

       // System.setOut(originalOut);
       // System.out.println(contentString);
        String expected="-----------Конец списка---------";
        assertTrue(contentString.contains(expected));
    }
    @Test
    public void testAddBook() {
        AppInputHelper appInputHelperMock = Mockito.mock(AppInputHelper.class);
        Book bookMock = Mockito.mock(Book.class);
        List<Book> booksMock =Mockito.mock(ArrayList.class);
        when(appInputHelperMock.createbook(inputMock)).thenReturn(bookMock);
        Storage<Book> storageMock = Mockito.mock(Storage.class);
        BookService bookServiceMock = new BookService(appInputHelperMock,storageMock);
        boolean result = bookServiceMock.add(inputMock);
        verify(storageMock,times(1)).save(booksMock);
        assertTrue(result);

    }
}