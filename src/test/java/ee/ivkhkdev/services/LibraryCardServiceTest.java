package ee.ivkhkdev.services;

import ee.ivkhkdev.helpers.LibraryCardAppHelper;
import ee.ivkhkdev.interfaces.AppHelper;
import ee.ivkhkdev.interfaces.AppRepository;
import ee.ivkhkdev.model.LibraryCard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class LibraryCardServiceTest {

    private LibraryCardService libraryCardService;
    private AppHelper<LibraryCard> mockLibraryCardAppHelper;
    private AppRepository<LibraryCard> mockRepository;

    @BeforeEach
    void setUp() {
        // Создаем моки для зависимостей
        mockLibraryCardAppHelper = Mockito.mock(AppHelper.class);
        mockRepository = Mockito.mock(AppRepository.class);

        // Инициализируем LibraryCardService с моками
        libraryCardService = new LibraryCardService(mockLibraryCardAppHelper, mockRepository);
    }

    @Test
    void testAddLibraryCardSuccess() {
        // Подготовка: создать LibraryCard и настроить заглушки
        LibraryCard mockLibraryCard = new LibraryCard();
        when(mockLibraryCardAppHelper.create()).thenReturn(mockLibraryCard);

        // Выполняем метод add
        boolean result = libraryCardService.add();

        // Проверка
        assertTrue(result);
        verify(mockRepository, times(1)).save(mockLibraryCard); // Убедиться, что метод save был вызван один раз
    }

    @Test
    void testAddLibraryCardFailureWhenLibraryCardIsNull() {
        // Настроить заглушку, чтобы create возвращал null
        when(mockLibraryCardAppHelper.create()).thenReturn(null);

        // Выполняем метод add
        boolean result = libraryCardService.add();

        // Проверка
        assertFalse(result);
        verify(mockRepository, never()).save(any()); // Убедиться, что метод save не был вызван
    }

    @Test
    void testAddLibraryCardExceptionHandling() {
        // Подготовка: создать LibraryCard и выбросить исключение при вызове save
        LibraryCard mockLibraryCard = new LibraryCard();
        when(mockLibraryCardAppHelper.create()).thenReturn(mockLibraryCard);
        doThrow(new RuntimeException("Save error")).when(mockRepository).save(mockLibraryCard);

        // Выполняем метод add
        boolean result = libraryCardService.add();

        // Проверка
        assertFalse(result); // Ожидаем, что метод вернет false при возникновении исключения
    }

    @Test
    void testPrint() {
        // Подготовка: создать список LibraryCard и настроить заглушки
        List<LibraryCard> mockLibraryCardList = List.of(new LibraryCard());
        when(mockRepository.load()).thenReturn(mockLibraryCardList);
        when(mockLibraryCardAppHelper.printList(mockLibraryCardList)).thenReturn(true);

        // Выполняем метод print
        boolean result = libraryCardService.print();

        // Проверка
        assertTrue(result);
        verify(mockRepository, times(1)).load(); // Убедиться, что метод load был вызван один раз
        verify(mockLibraryCardAppHelper, times(1)).printList(mockLibraryCardList); // Убедиться, что метод printList был вызван один раз
    }

    @Test
    void testList() {
        // Подготовка: создать список LibraryCard и настроить заглушки
        List<LibraryCard> mockLibraryCardList = List.of(new LibraryCard());
        when(mockRepository.load()).thenReturn(mockLibraryCardList);

        // Выполняем метод list
        List<LibraryCard> result = libraryCardService.list();

        // Проверка
        assertEquals(mockLibraryCardList, result);
        verify(mockRepository, times(1)).load(); // Убедиться, что метод load был вызван один раз
    }

    @Test
    void testReturnBookSuccess() {
        // Подготовка: создать список LibraryCard и настроить заглушки
        LibraryCardAppHelper mockLibraryCardAppHelperCast = mock(LibraryCardAppHelper.class);
        List<LibraryCard> mockLibraryCardList = List.of(new LibraryCard());
        when(mockLibraryCardAppHelperCast.returnBack(mockLibraryCardList)).thenReturn(mockLibraryCardList);
        when(mockRepository.load()).thenReturn(mockLibraryCardList);

        // Вызов метода returnBook
        boolean result = new LibraryCardService(mockLibraryCardAppHelperCast, mockRepository).returnBook();

        // Проверка
        assertTrue(result);
        verify(mockRepository, times(1)).saveAll(mockLibraryCardList); // Убедиться, что метод saveAll был вызван один раз
    }

    @Test
    void testReturnBookFailure() {
        // Подготовка: создать список LibraryCard и настроить заглушки
        LibraryCardAppHelper mockLibraryCardAppHelperCast = mock(LibraryCardAppHelper.class);
        List<LibraryCard> mockLibraryCardList = List.of(new LibraryCard());
        when(mockLibraryCardAppHelperCast.returnBack(mockLibraryCardList)).thenReturn(null);
        when(mockRepository.load()).thenReturn(mockLibraryCardList);

        // Вызов метода returnBook
        boolean result = new LibraryCardService(mockLibraryCardAppHelperCast, mockRepository).returnBook();

        // Проверка
        assertFalse(result);
        verify(mockRepository, never()).saveAll(any()); // Убедиться, что метод saveAll не был вызван
    }
}