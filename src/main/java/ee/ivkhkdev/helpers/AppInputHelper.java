package ee.ivkhkdev.helpers;

import ee.ivkhkdev.App;
import ee.ivkhkdev.interfaces.Input;
import ee.ivkhkdev.model.Author;
import ee.ivkhkdev.model.Book;
import ee.ivkhkdev.model.User;

public class AppInputHelper {
    public User createUser(Input input){
        User user = new User();
        System.out.println("Имя пользователя: ");
        user.setFirstName(input.nextLine());
        System.out.println("Фамилия пользователя: ");
        user.setLastName(input.nextLine());
        System.out.println("Телефон: ");
        user.setPhone(input.nextLine());
        return user;
    }
    public boolean printListUser(){
        try {
            System.out.println("Список пользователей:");
            for (int i = 0; i < App.books.length; i++) {
                User user = App.books[i];
                if (user != null) {
                    System.out.printf("%d. %s. %s. %s.%n",
                            i + 1,
                            user.getFirstName(),
                            user.getLastName(),
                            user.getPhone()
                    );
                }
            }
            return true;
        }catch (Exception e){
            System.out.println("Error"+e.toString());
            return false;
        }
    }
    public Book createbook(Input input) {
        Book book = new Book();
        System.out.print("Название книги:");
        book.setTitle(input.nextLine());
        System.out.print("Количество авторов:");
        int countBookAuthors = Integer.parseInt(input.nextLine());
        for (int i = 0; i < countBookAuthors; i++) {
            Author author = new Author();
            System.out.printf("автор %d:", i + 1);
            System.out.print("Имя:");
            author.setFirstName(input.nextLine());
            System.out.print("Фамилия:");
            author.setLastName(input.nextLine());
            book.getAuthor()[i] = author;
        };
        System.out.println("Год издания:");
        book.getPublicationYear(input.nextLine())

    }
}
