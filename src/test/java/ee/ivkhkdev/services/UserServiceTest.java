package ee.ivkhkdev.services;

import static org.junit.jupiter.api.Assertions.*;

import ee.ivkhkdev.interfaces.AppHelper;
import ee.ivkhkdev.model.User;
import ee.ivkhkdev.interfaces.AppRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.mockito.Mockito.*;

import java.util.List;

public class UserServiceTest {

    private UserService userService;
    private AppRepository<User> mockRepository;
    private AppHelper<User> mockAppHelperUser;

    @BeforeEach
    void setUp() {
        // Создаем моки для зависимостей
        mockRepository = Mockito.mock(AppRepository.class);
        mockAppHelperUser = Mockito.mock(AppHelper.class);

        // Инициализируем UserService с моками
        userService = new UserService(mockAppHelperUser, mockRepository);
    }

    @Test
    void test_add_ShouldReturnTrue_WhenUserCreatedSuccessfully() {
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
    void test_add_ShouldReturnFalse_WhenUserCreationFails() {
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
    @Test
    void testEdit_Successfull(){
        List<User> users = List.of(new User("Ivan","Ivanov","123456"),new User("Jana","Tomme","234567"));
        when(mockRepository.load()).thenReturn(users);
        when(mockAppHelperUser.edit(users)).thenReturn(users);
        boolean result = userService.edit();
        assertTrue(result);
    }
    @Test
    void testEdit_NotSuccessfull(){
        List<User> users = List.of(new User("Ivan","Ivanov","123456"),new User("Jana","Tomme","234567"));
        when(mockRepository.load()).thenReturn(users);
        when(mockAppHelperUser.edit(users)).thenReturn(null);
        boolean result = userService.edit();
        assertFalse(result);
    }
}