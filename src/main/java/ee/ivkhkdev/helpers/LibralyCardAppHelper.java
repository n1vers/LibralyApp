package ee.ivkhkdev.helpers;

import ee.ivkhkdev.interfaces.AppHelper;
import ee.ivkhkdev.interfaces.Input;
import ee.ivkhkdev.interfaces.Service;
import ee.ivkhkdev.model.Book;
import ee.ivkhkdev.model.LibraryCard;
import ee.ivkhkdev.model.User;


import java.time.LocalDate;
import java.util.List;

public class LibralyCardAppHelper implements AppHelper<LibraryCard> {
    private final Input input;
    private final Service<Book> bookService;
    private final Service<User> userService;

    public LibralyCardAppHelper(Input input, Service<Book> bookService, Service<User> userService) {
        this.input=input;
        this.bookService = bookService;
        this.userService = userService;
    }

    @Override
    public LibraryCard create() {
        if (!bookService.print()) {
            return null;
        }
        System.out.print("Выберите номер книги: ");
        int bookIndex = Integer.parseInt(input.nextLine());
        Book book = bookService.list().get(bookIndex - 1);
        if (!userService.print()) {
            return null;
        }
        System.out.print("Выберите номер клиента: ");
        int userIndex = Integer.parseInt(input.nextLine());
        User user = userService.list().get(userIndex - 1);
        LibraryCard libraryCard = new LibraryCard();
        libraryCard.setBook(book);
        libraryCard.setUser(user);
        libraryCard.setBorrowDate(LocalDate.now());
        libraryCard.setReturnDate(null);
        return libraryCard;
    }

    @Override
    public boolean printList(List<LibraryCard> cards) {
        if (cards == null || cards.isEmpty()) {
            System.out.println("Нет доступных библиотечных карт для отображения.");
            return false;
        }

        int count = 0;
        System.out.println("Список библиотечных карт:");
        for (int i = 0; i < cards.size(); i++) {
            LibraryCard card = cards.get(i);
            if (card.getReturnDate() == null) {
                System.out.printf("%d. %s. %d. читает %s %s%n",
                        i + 1,
                        card.getBook().getTitle(),
                        card.getBook().getPublicationYear(),
                        card.getUser().getFirstName(),
                        card.getUser().getLastName());
                count++;
            }
        }

        return count > 0; // Возвращаем true, если были найдены карты, иначе false
    }

    public List<LibraryCard>  returnBack (List<LibraryCard> cards) {
            if(!this.printList(cards)) {
                System.out.println("Нет книг для возврата.");
                return null;
            }
            System.out.print("Выберите номер книги, которую хотите вернуть: ");
            int index = Integer.parseInt(input.nextLine());
            LibraryCard libraryCard = cards.get(index - 1);
            cards.get(index - 1).setReturnDate(LocalDate.now());
            return cards;

    }
}
