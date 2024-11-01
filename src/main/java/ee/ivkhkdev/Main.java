package ee.ivkhkdev;


import ee.ivkhkdev.interfaces.AppHelper;
import ee.ivkhkdev.helpers.AuthorAppHelper;
import ee.ivkhkdev.helpers.BookAppHelper;
import ee.ivkhkdev.helpers.UserAppHelper;
import ee.ivkhkdev.input.ConsoleInput;
import ee.ivkhkdev.interfaces.Input;
import ee.ivkhkdev.model.Author;
import ee.ivkhkdev.model.Book;
import ee.ivkhkdev.model.User;
import ee.ivkhkdev.interfaces.Repository;
import ee.ivkhkdev.repositories.Storage;
import ee.ivkhkdev.services.AuthorService;
import ee.ivkhkdev.services.BookService;
import ee.ivkhkdev.interfaces.Service;
import ee.ivkhkdev.services.UserService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Input input = new ConsoleInput(new Scanner(System.in));
        Repository<Author> authorRepository =  new Storage<>("authors");
        Repository<User> userRepository =  new Storage<>("users");
        Repository<Book> bookRepository =  new Storage<>("books");

        AppHelper<Author> appHelperAuthor = new AuthorAppHelper(input);
        AppHelper<User> appHelperUser = new UserAppHelper(input);
        Service<Author> authorService = new AuthorService(appHelperAuthor,authorRepository);
        AppHelper<Book> appHelperBook = new BookAppHelper(input,authorService);
        Service<User> userService = new UserService(appHelperUser,userRepository);
        Service<Book> bookService = new BookService(appHelperBook,bookRepository);
        App app = new App(input,bookService,userService,authorService);
        app.run();
    }
}
