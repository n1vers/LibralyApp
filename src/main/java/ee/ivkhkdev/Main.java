package ee.ivkhkdev;


import ee.ivkhkdev.factory.Factory;
import ee.ivkhkdev.factory.JavaConfiguration;
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
        Factory factory = Factory.getInstance(new JavaConfiguration());
        Object emptyObject  = factory.getobject("empty");
        Repository<Author> authorRepository = (Storage)factory.getobject("authorRepository");
        Repository<User> userRepository = (Storage)factory.getobject("userRepository");
        Repository<Book> bookRepository = (Storage)factory.getobject("bookRepository");
        Input input = (Input)factory.getobject("input");
        AppHelper<Author> authorAppHelper =  (AppHelper)factory.getobject("authorAppHelper");
        AppHelper<User> userAppHelper = (AppHelper)factory.getobject("userAppHelper");
        Service<Author> authorService = (Service)factory.getobject("authorService");
        AppHelper<Book> bookAppHelper = (AppHelper)factory.getobject("bookAppHelper");
        Service<User> userService = (Service)factory.getobject("userService");
        Service<Book> bookService = (Service)factory.getobject("bookService");
        AppHelper<LibraryCard> libraryCardAppHelper = (AppHelper)factory.getobject("libraryCardAppHelper");
        Repository<LibraryCard> libraryCardRepository = (Storage)factory.getobject("libraryCardRepository");
        Service<LibraryCard> libraryCardService = (Service)factory.getobject("libraryCardService");

        App app = (App) factory.getobject("app");
        app.run();
    }
}
