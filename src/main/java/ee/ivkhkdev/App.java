package ee.ivkhkdev;


import ee.ivkhkdev.interfaces.Input;

import ee.ivkhkdev.model.Author;
import ee.ivkhkdev.model.Book;
import ee.ivkhkdev.model.LibraryCard;
import ee.ivkhkdev.model.User;
import ee.ivkhkdev.interfaces.Service;



public class App {
    private Input input;

    private Service<User> userService;
    private Service<Book> bookService;
    private Service<Author> authorService;
    private Service<LibraryCard> libralyCardService;

    public App(Input input, Service<Book> bookService, Service<User> userService, Service<Author> authorService, Service<LibraryCard> libralyCardService) {
         this.input = input;
        this.userService = userService;
        this.authorService = authorService;
        this.bookService = bookService;
        this.libralyCardService = libralyCardService;
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
            System.out.println("6. Выдать книгу");


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
                        System.out.println("5. Добавить Автора");
                        if(authorService.add()){
                            System.out.println("Пользователь добавлен");
                        }else{
                            System.out.println("Пользователя добавить не удалось");
                        };
                        break;
                case 6:
                    System.out.println("6. Выдать книгу");
                    if(libralyCardService.add()){
                        System.out.println("Книга выдана");
                    }else{
                        System.out.println("Книгу выдать не удалось");
                    };
                    break;
                case 7:
                    System.out.println("6. Вернуть книгу");
                    if(libralyCardService.returnBook()) {
                        System.out.println("Книга вернута");
                    }else{
                        System.out.println("Книгу вернуть не удалось");
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