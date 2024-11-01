package ee.ivkhkdev.services;

import static org.junit.jupiter.api.Assertions.*;

import ee.ivkhkdev.interfaces.AppHelper;
import ee.ivkhkdev.interfaces.Repository;
import ee.ivkhkdev.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.mockito.Mockito.*;

import java.util.List;

public class UserServiceTest {

    private UserService userService;
    private Repository<User> mockRepository;
    private ee.ivkhkdev.interfaces.AppHelper<User> mockAppHelperUser;

    @BeforeEach
    void setUp() {
        // Создаем моки для зависимостей
        mockRepository = Mockito.mock(Repository.class);
        mockAppHelperUser = Mockito.mock(AppHelper.class);

        // Инициализируем UserService с моками
        userService = new UserService(mockAppHelperUser, mockRepository);
    }

    @Test
    void testAddUserSuccess() {
        // Подготовка: создать пользователя и настроить заглушки
        User mockUser = new User(); // Предполагается, что у класса User есть конструктор по умолчанию
        when(mockAppHelperUser.create()).thenReturn(mockUser);

        // Выполняем метод add
        boolean result = userService.add();

        // Проверка
        assertTrue(result);
        verify(mockRepository, times(1)).save(mockUser); // Убедиться, что метод save был вызван один раз
    }

    @Test
    void testAddUserFailureWhenUserIsNull() {
        // Настроить заглушку, чтобы create возвращал null
        when(mockAppHelperUser.create()).thenReturn(null);

        // Выполняем метод add
        boolean result = userService.add();

        // Проверка
        assertFalse(result);
        verify(mockRepository, never()).save(any()); // Убедиться, что метод save не был вызван
    }

    @Test
    void testAddUserExceptionHandling() {
        // Подготовка: создать пользователя и выбросить исключение при вызове save
        User mockUser = new User();
        when(mockAppHelperUser.create()).thenReturn(mockUser);
        doThrow(new RuntimeException("Save error")).when(mockRepository).save(mockUser);

        // Выполняем метод add
        boolean result = userService.add();

        // Проверка
        assertFalse(result); // Ожидаем, что метод вернет false при возникновении исключения
    }

    @Test
    void testPrint() {
        // Подготовка: создать список пользователей и настроить заглушки
        List<User> mockUserList = List.of(new User(), new User());
        when(mockRepository.load()).thenReturn(mockUserList);
        when(mockAppHelperUser.printList(mockUserList)).thenReturn(true);

        // Выполняем метод print
        boolean result = userService.print();

        // Проверка
        assertTrue(result);
        verify(mockRepository, times(1)).load(); // Убедиться, что метод load был вызван один раз
        verify(mockAppHelperUser, times(1)).printList(mockUserList); // Убедиться, что метод printList был вызван один раз
    }

    @Test
    void testList() {
        // Подготовка: создать список пользователей и настроить заглушки
        List<User> mockUserList = List.of(new User(), new User());
        when(mockRepository.load()).thenReturn(mockUserList);

        // Выполняем метод list
        List<User> result = userService.list();

        // Проверка
        assertEquals(mockUserList, result);
        verify(mockRepository, times(1)).load(); // Убедиться, что метод load был вызван один раз
    }
}