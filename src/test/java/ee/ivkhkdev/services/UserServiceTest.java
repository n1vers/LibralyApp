package ee.ivkhkdev.services;

import ee.ivkhkdev.interfaces.AppHelper;
import ee.ivkhkdev.model.User;
import ee.ivkhkdev.interfaces.Repository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    private AppHelper<User> appHelperUser;
    private Repository<User> repository;
    private UserService userService;

    @BeforeEach
    void setUp() {
        appHelperUser = Mockito.mock(AppHelper.class);
        repository = Mockito.mock(Repository.class);
        userService = new UserService(appHelperUser, repository);
    }

    @Test
    void testAdd_SuccessfulAdd() {
        User user = new User("John", "Doe","3234242434");
        when(appHelperUser.create()).thenReturn(user);

        boolean result = userService.add();

        assertTrue(result); // Проверяем, что добавление прошло успешно
        verify(repository, times(1)).save(user); // Проверяем, что метод save() вызван один раз
    }

    @Test
    void testAdd_CreateReturnNull() {
        when(appHelperUser.create()).thenReturn(null);

        boolean result = userService.add();

        assertFalse(result); // Проверяем, что добавление не выполнено
        verify(repository, never()).save(any()); // Проверяем, что метод save() не вызван
    }

    @Test
    void testAdd_AddExistingUser() {
        User user = new  User("John", "Doe","3234242434");
        when(appHelperUser.create()).thenReturn(user);
        when(repository.load()).thenReturn(List.of(user)); // Имитируем, что пользователь уже существует

        boolean result = userService.add();

        assertTrue(result); // Предполагаем, что добавление прошло успешно
        verify(repository, times(1)).save(user);
    }

    @Test
    void testPrint() {
        List<User> users = List.of(new  User("John", "Doe","3234242434"));
        when(repository.load()).thenReturn(users);
        when(appHelperUser.printList(users)).thenReturn(true);

        boolean result = userService.print();

        assertTrue(result); // Проверяем, что печать прошла успешно
        verify(appHelperUser, times(1)).printList(users); // Проверяем, что printList вызван один раз
    }

    @Test
    void testList() {
        List<User> users = List.of(new  User("John", "Doe","3234242434"));
        when(repository.load()).thenReturn(users);

        List<User> resultUsers = userService.list();

        assertEquals(users.size(), resultUsers.size());
        assertEquals(users.get(0).getFirstName(), resultUsers.get(0).getFirstName());
        assertEquals(users.get(0).getLastName(), resultUsers.get(0).getLastName());
        assertEquals(users.get(0).getPhone(), resultUsers.get(0).getPhone());
    }
}
