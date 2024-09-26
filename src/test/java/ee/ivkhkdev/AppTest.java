package ee.ivkhkdev;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class AppTest {

    @Test
    public void testRunExit() {
        // Подготавливаем ввод, имитируя ввод '0' для выхода
        String input = "0";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        // Запускаем метод run() и проверяем, что он не выбрасывает исключение
        App app = new App(new Scanner(System.in));
        assertDoesNotThrow(app::run);
    }

    @Test
    public void testRunInvalidTask() {
        // Подготавливаем ввод: сначала 1 (некорректный ввод), затем 0 (выход)
        String input = "1\n0";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        // Запускаем метод run() и проверяем, что он не выбрасывает исключение
        App app = new App(new Scanner(System.in));
        assertDoesNotThrow(app::run);
    }
}
