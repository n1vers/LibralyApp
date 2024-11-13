package ee.ivkhkdev.helpers;

import ee.ivkhkdev.interfaces.AppHelper;
import ee.ivkhkdev.interfaces.Input;
import ee.ivkhkdev.model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class UserAppHelperTest {
    Input inputMock;
    AppHelper<User> appHelperUser;
    PrintStream defaultOut = System.out;
    ByteArrayOutputStream outMock;
    @BeforeEach
    void setUp() {
        inputMock = Mockito.mock(Input.class);
        appHelperUser = new UserAppHelper(inputMock);
        outMock = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outMock));
    }

    @AfterEach
    void tearDown() {
        inputMock = null;
        System.setOut(defaultOut);
        outMock=null;
    }

    @Test
    void create_ShouldReturnUserWithValidInput() {
        when(inputMock.nextLine()).thenReturn("Ivan","Ivanov","123456");
        User user = appHelperUser.create();
        User expected = new User("Ivan","Ivanov","123456");
        assertNotNull(user);
        assertEquals(user.getFirstName(), expected.getFirstName());
        assertEquals(user.getLastName(), expected.getLastName());
        assertEquals(user.getPhone(), expected.getPhone());
    }
    @Test
    void create_ShouldReturnNullWhenExceptionOccurs() {
        // Arrange
        when(inputMock.nextLine()).thenThrow(new RuntimeException("Input error"));
        // Act
        User user = appHelperUser.create();
        // Assert
        assertNull(user);
    }
    @Test
    void printList_ShouldReturnTrueWhenUsersExist() {
        User user = new User("Ivan","Ivanov","56565656");
        List<User> users = new ArrayList<>();
        users.add(user);
        boolean result = appHelperUser.printList(users);
        boolean expected = true;
        assertTrue(result);
        String expectedString = "1. Ivan Ivanov. 56565656";
        assertTrue(outMock.toString().contains(expectedString));
    }
    @Test
    void printList_ShouldReturnFalseWhenUsersListIsEmpty() {
        // Arrange
        List<User> users = new ArrayList<>();
        // Act
        boolean result = appHelperUser.printList(users);
        // Assert
        assertFalse(result);
    }
    @Test
    void edit_ShouldUpdateUserDetailsWhenValidInput(){
        List<User> users = List.of(new User("Ivan","Ivanov","12345"));
        when(inputMock.nextLine()).thenReturn("1","y", "Ivan1", "y","Ivanov1","y","1234567");
        List<User> result = appHelperUser.edit(users);
        assertNotNull(result);
        assertEquals(result.get(0).getFirstName(), "Ivan1");
        assertEquals(result.get(0).getLastName(), "Ivanov1");
        assertEquals(result.get(0).getPhone(), "1234567");
    }
    @Test
    void edit_ShouldReturnNullWhenExceptionOccurs() {
        // Arrange
        List<User> users = new ArrayList<>();
        when(inputMock.nextLine()).thenThrow(new RuntimeException("Input error"));
        // Act
        List<User> result = appHelperUser.edit(users);
        // Assert
        assertNull(result);
    }
}