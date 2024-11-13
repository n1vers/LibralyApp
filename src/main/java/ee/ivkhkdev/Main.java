package ee.ivkhkdev;


import ee.ivkhkdev.helpers.LibraryCardAppHelper;
import ee.ivkhkdev.interfaces.AppHelper;
import ee.ivkhkdev.helpers.AuthorAppHelper;
import ee.ivkhkdev.helpers.BookAppHelper;
import ee.ivkhkdev.helpers.UserAppHelper;
import ee.ivkhkdev.input.ConsoleInput;
import ee.ivkhkdev.interfaces.Input;
import ee.ivkhkdev.model.Author;
import ee.ivkhkdev.model.Book;
import ee.ivkhkdev.model.LibraryCard;
import ee.ivkhkdev.model.User;
import ee.ivkhkdev.interfaces.Repository;
import ee.ivkhkdev.repositories.Storage;
import ee.ivkhkdev.services.AuthorService;
import ee.ivkhkdev.services.BookService;
import ee.ivkhkdev.interfaces.Service;
import ee.ivkhkdev.services.LibraryCardService;
import ee.ivkhkdev.services.UserService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Repository<Author> authorRepository = new Storage<>("authors");
        Repository<User> userRepository = new Storage<>("users");
        Repository<Book> bookRepository = new Storage<>("books");
        Input input = new ConsoleInput(new Scanner(System.in));
        AppHelper<Author> authorAppHelper = new AuthorAppHelper(input);
        AppHelper<User> userAppHelper = new UserAppHelper(input);
        Service<Author> authorService = new AuthorService(authorAppHelper,authorRepository);;
        AppHelper<Book> bookAppHelper = new BookAppHelper(input,authorService);
        Service<User> userService = new UserService(userAppHelper,userRepository);
        Service<Book> bookService = new BookService(bookAppHelper,bookRepository);
        AppHelper<LibraryCard> libraryCardAppHelper = new LibraryCardAppHelper(input,bookService,userService);
        Repository<LibraryCard> libraryCardRepository = new Storage<>("libraryCards");
        Service<LibraryCard> libraryCardService = new LibraryCardService(libraryCardAppHelper, libraryCardRepository);

        App app = new App(input, bookService,userService,authorService,libraryCardService);
        app.run();
    }
}
