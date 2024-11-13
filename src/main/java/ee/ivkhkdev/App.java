package ee.ivkhkdev;

import ee.ivkhkdev.interfaces.AppHelper;
import ee.ivkhkdev.interfaces.Input;
import ee.ivkhkdev.model.Author;
import ee.ivkhkdev.model.Book;
import ee.ivkhkdev.model.LibraryCard;
import ee.ivkhkdev.model.User;
import ee.ivkhkdev.interfaces.Service;
import ee.ivkhkdev.services.LibraryCardService;

public class App {
    private final Service<LibraryCard> libraryCardService;
    private Input input;
    private Service<User> userService;
    private Service<Book> bookService;
    private Service<Author> authorService;

    public App(Input input, Service<Book> bookService, Service<User> userService, Service<Author> authorService, Service<LibraryCard> libraryCardService) {
        this.input = input;
        this.bookService = bookService;
        this.userService = userService;
        this.authorService = authorService;
        this.libraryCardService = libraryCardService;
    }

    public void run() {
        boolean repeat = true;
        System.out.println("======= JPTV23Library =========");
        do {
            System.out.println("Список задач:");
            System.out.println("0. Выйти из программы");
            System.out.println("1. Добавить пользователя");
            System.out.println("2. Список пользователей");
            System.out.println("3. Добавить книгу");
            System.out.println("4. Список книг");
            System.out.println("5. Добавить автора");
            System.out.println("6. Выдать книгу");
            System.out.println("7. Вернуть книгу");
            System.out.println("8. Редактировать книгу");
            System.out.println("9. Редактировать пользователя");
            System.out.print("Введите номер задачи: ");
            int task = Integer.parseInt(input.nextLine()); // Используем input
            switch (task) {
                case 0:
                    System.out.println("Выход из программы");
                    repeat = false;
                    break;
                case 1:
                    System.out.println("Добавить пользователя");
                    if(userService.add()){
                        System.out.println("Пользователь добавлен");
                    }else{
                        System.out.println("Пользователя добавить не удалось");
                    };
                    break;
                case 2:
                    if(userService.print()){
                        System.out.println("----------- Конец списка -----------");
                    }
                    break;
                case 3:
                    System.out.println("Добавить книгу");
                    if(bookService.add()){
                        System.out.println("Книга добавлена");
                    }else {
                        System.out.println("Книгу добавить не удалось");
                    }
                    break;
                case 4:
                    if(bookService.print()){
                        System.out.println("----------- Конец списка -----------");
                    }
                    break;
                case 5:
                    System.out.println("Добавить автора");
                    if(authorService.add()){
                        System.out.println("Автор добавлен");
                    }else{
                        System.out.println("Книгу добавить не удалось");
                    };
                    break;
                case 6:
                    System.out.println("Выдать книгу");
                    if(libraryCardService.add()){
                        System.out.println("Книга выдана");
                    }else{
                        System.out.println("Книгу выдать не удалось");
                    };
                    break;
                case 7:
                    System.out.println("Вернуть книгу");
                    if(((LibraryCardService)libraryCardService).returnBook()){
                        System.out.println("Книга возврощена");
                    }else{
                        System.out.println("Книгу вернуть не удалось");
                    };
                    break;
                case 8:
                    System.out.println("Редактирование книги");
                    if(bookService.edit()){
                        System.out.println("Книга изменена");
                    }else {
                        System.out.println("Книгу изменить не удалось");
                    }
                    break;
                case 9:
                    System.out.println("Редактирование пользователя");
                    if(userService.edit()){
                        System.out.println("Пользователь изменен");
                    }else {
                        System.out.println("Пользователя изменить не удалось");
                    }
                    break;
                default:
                    System.out.println("Выберите номер из списка задач!");
                    break;
            }
            System.out.println("==============================");
        } while (repeat);
        System.out.println("До свидания! :)");
    }
}