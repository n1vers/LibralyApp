package ee.ivkhkdev.configuration;

import ee.ivkhkdev.helpers.AuthorAppHelper;
import ee.ivkhkdev.helpers.BookAppHelper;
import ee.ivkhkdev.helpers.LibraryCardAppHelper;
import ee.ivkhkdev.helpers.UserAppHelper;
import ee.ivkhkdev.input.ConsoleInput;
import ee.ivkhkdev.interfaces.AppHelper;
import ee.ivkhkdev.interfaces.AppRepository;
import ee.ivkhkdev.interfaces.AppService;
import ee.ivkhkdev.interfaces.Input;
import ee.ivkhkdev.model.Author;
import ee.ivkhkdev.model.Book;
import ee.ivkhkdev.model.LibraryCard;
import ee.ivkhkdev.model.User;
import ee.ivkhkdev.repositories.Storage;
import ee.ivkhkdev.services.AuthorService;
import ee.ivkhkdev.services.BookService;
import ee.ivkhkdev.services.LibraryCardService;
import ee.ivkhkdev.services.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import java.util.Scanner;

@Configuration
public class AppConfiguration {

    @Bean
    public Input input() {
        return new ConsoleInput(new Scanner(System.in));
    }
    @Bean
    public AppRepository<Author> authorRepository() {
        return new Storage<>("authors");
    }
    @Bean
    public AppRepository<User> userRepository() {
        return new Storage<>("users");
    }
    @Bean
    public AppRepository<Book> bookRepository() {
        return new Storage<>("books");
    }
    @Bean
    public AppRepository<LibraryCard> libraryCardRepository() {
        return new Storage<>("libraryCards");
    }
    @Bean
    public AppHelper<Author> appHelperAuthor(Input input){
        return new AuthorAppHelper(input);
    }
    @Bean
    public AppHelper<User> appHelperUser(Input input){
        return new UserAppHelper(input);
    }

    @Bean
    public AppService<Author> authorService(AppHelper<Author> appHelperAuthor, AppRepository<Author> authorRepository){
        return new AuthorService(appHelperAuthor, authorRepository);
    }
    @Bean
    public AppHelper<Book> appHelperBook(Input input, AppService<Author> authorService){
        return new BookAppHelper(input,authorService);
    }
    @Bean
    public AppService<Book> bookService(AppHelper<Book> appHelperBook,AppRepository<Book> bookRepository){
        return new BookService(appHelperBook, bookRepository);
    }
    @Bean
    public AppService<User> userService(AppHelper<User> appHelperUser,AppRepository<User> userRepository){
        return new UserService(appHelperUser, userRepository);
    }
    @Bean
    public AppHelper<LibraryCard> libraryCardAppHelper(Input input, AppService<Book> bookAppService,AppService<User> userAppService){
        return new LibraryCardAppHelper(input,bookAppService,userAppService);
    }
    @Bean
    public AppService<LibraryCard> libraryCardService(AppHelper<LibraryCard> libraryCardAppHelper,AppRepository<LibraryCard> libraryCardAppRepository){
        return new LibraryCardService(libraryCardAppHelper,libraryCardAppRepository);
    }
}