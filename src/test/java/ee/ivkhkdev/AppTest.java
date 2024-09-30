package ee.ivkhkdev;

import ee.ivkhkdev.interfaces.Input;
import ee.ivkhkdev.model.Customer;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;


import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;


public class AppTest{
    private Input inputMock; // Мок объекта Input
    private ByteArrayOutputStream outContent; // Для перехвата вывода консоли
    private final PrintStream originalOut = System.out; // Оригинальный System.out

    @BeforeEach
    public void setUp() {
        // Мокируем Input
        inputMock = Mockito.mock(Input.class);

        // Перехватываем вывод в консоль
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void tearDown() {

        System.setOut(originalOut);
    }

    @Test
    public void testExitProgram() {
        // Настраиваем поведение nextInt для завершения программы
        when(inputMock.nextInt()).thenReturn(0);

        // Создаем объект App с мокированным input
        App app = new App(inputMock);

        // Запускаем метод run
        app.run();


        // Проверяем, что фактический вывод совпадает с ожидаемым
       assertTrue(outContent.toString().contains("Выход из программы"));
    }

    @Test
    public void testInvalidTaskNumber() {
        // Настраиваем поведение nextInt для неверного ввода и последующего завершения программы
        when(inputMock.nextInt()).thenReturn(5, 0); // Сначала неверный ввод, затем завершение

        // Создаем объект App с мокированным input
        App app = new App(inputMock);

        // Запускаем метод run
        app.run();

        // Проверяем, что фактический вывод совпадает с ожидаемым
        assertTrue(outContent.toString().contains("Выберите номер из списка задач!") && outContent.toString().contains("До свидания!"));
    }

    @Test
    public void TestAddCustomer(){
        when(inputMock.nextInt()).thenReturn(1,0);

        App app = new App(inputMock);

        Customer expected=new Customer("Ivan","Ivanov","54345534");

        app.run();

        assertTrue(App.customers[0].getFirstname().equals("Ivan")&& outContent.toString().contains("До свидания!"));
    }
}

