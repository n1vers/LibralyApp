package ee.ivkhkdev;

import ee.ivkhkdev.interfaces.Input;
import ee.ivkhkdev.model.User;
import ee.ivkhkdev.services.BookService;
import ee.ivkhkdev.services.UserService;

public class App {
    private Input input;
    public static User[] books = new User[100];
    private UserService userService = new UserService();
    private BookService bookService = new BookService();
    // Теперь в конструктор передается Input вместо Scanner
    public App(Input input) {
        this.input = input;
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
            System.out.print("Введите номер задачи: ");
            int task = Integer.parseInt(input.nextLine()); // Используем input
            switch (task) {
                case 0:
                    System.out.println("Выход из программы");
                    repeat = false;
                    break;
                case 1:
                    System.out.println("1. Добавить пользователя");
                    if(userService.add(input)){
                        System.out.println("Пользователь добавлен");
                    }else{
                        System.out.println("Пользователя добавить не удалось");
                    };
                    break;
                case 2:
                    if(userService.printList()){
                        System.out.println("-----------Конец списка---------");
                    }
                case 3:
                    System.out.println("3. Добавить книгу");
                    if(bookService.add(input)){
                        System.out.println("Книга добавлена");
                    }else {
                        System.out.println("Книгу добвить не удалось");
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