package ee.ivkhkdev;

import ee.ivkhkdev.helpers.*;
import ee.ivkhkdev.input.Input;
import ee.ivkhkdev.input.ConsoleInput;
import ee.ivkhkdev.model.Author;
import ee.ivkhkdev.model.Book;
import ee.ivkhkdev.model.User;
import ee.ivkhkdev.repositories.Repository;
import ee.ivkhkdev.services.AuthorService;
import ee.ivkhkdev.services.BookService;
import ee.ivkhkdev.services.Service;
import ee.ivkhkdev.services.UserService;
import ee.ivkhkdev.repositories.Storage;


import java.util.List;
import java.util.Scanner;

public class App {
    private Input input;
    public List<User> users;
    public List<Book> books;
    public List<Author> authors;

    private AppHelper appHelperAuthor;
    private AppHelper appHelperBook;
    private AppHelper appHelperUser;
    private Repository<Author> authorRepository;
    private Repository<User> userRepository;
    private Repository<Book> bookRepository;
    private Service<User> userService;
    private Service<Book> bookService;
    private Service<Author> authorService;


    public App() {
        userRepository = new Storage<>("users");
        bookRepository = new Storage<>("books");
        authorRepository = new Storage<>("authors");
        this.users = this.userRepository.load();
        this.authors = this.authorRepository.load();
        this.books = this.bookRepository.load();
        this.input = new ConsoleInput(new Scanner(System.in));
        appHelperUser = new AppHelperUser(input);
        appHelperAuthor = new AppHelperAuthor(input);
        userService = new UserService(users,appHelperUser,userRepository);
        authorService = new AuthorService(authors,appHelperAuthor,authorRepository);
        appHelperBook = new AppHelperBook(input,authorService);
        bookService = new BookService(books,appHelperBook,bookRepository);
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
            System.out.println("5. Добавить Автора");
            System.out.print("Введите номер задачи: ");
            int task = Integer.parseInt(input.nextLine()); // Используем input
            switch (task) {
                case 0:
                    System.out.println("Выход из программы");
                    repeat = false;
                    break;
                case 1:
                    System.out.println("1. Добавить пользователя");
                    if(userService.add()){
                        System.out.println("Пользователь добавлен");
                    }else{
                        System.out.println("Пользователя добавить не удалось");
                    };
                    break;
                case 2:
                    if(userService.print()){
                        System.out.println("-----------Конец списка---------");
                    }
                case 3:
                    System.out.println("3. Добавить книгу");
                    if(bookService.add()){
                        System.out.println("Книга добавлена");
                    }else {
                        System.out.println("Книгу добвить не удалось");
                    }
                    break;
                    case 4:
                        if(bookService.print()){
                            System.out.println("-----------Конец списка---------");
                        }
                        break;
                    case 5:
                        System.out.println("1. Добавить пользователя");
                        if(authorService.add()){
                            System.out.println("Пользователь добавлен");
                        }else{
                            System.out.println("Пользователя добавить не удалось");
                        };
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