package ee.ivkhkdev;

import java.util.Scanner;

public class App {
    private Scanner scanner;

    // Конструктор по умолчанию
    public App() {
        this.scanner = new Scanner(System.in); // Создаем объект Scanner внутри
    }

    // Конструктор с параметром для тестов
    public App(Scanner scanner) {
        this.scanner = scanner;
    }

    public void run(){
        boolean repeat = true;
        do {
            System.out.println("Список задач:");
            System.out.println("0.Выйти из программы");
            System.out.print("Введите номер задачи:");
            int task = scanner.nextInt();
            switch (task) {
                case 0:
                    System.out.println("Выход из программы");
                    repeat = false;
                    break;
                default:
                    System.out.println("Выберите номер из списка задач!");
                    break;
            }
        }while (repeat);
    }

}
