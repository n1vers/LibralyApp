package ee.ivkhkdev.factory;

import ee.ivkhkdev.App;
import ee.ivkhkdev.helpers.AuthorAppHelper;
import ee.ivkhkdev.helpers.BookAppHelper;
import ee.ivkhkdev.helpers.LibraryCardAppHelper;
import ee.ivkhkdev.helpers.UserAppHelper;
import ee.ivkhkdev.input.ConsoleInput;
import ee.ivkhkdev.interfaces.Input;
import ee.ivkhkdev.interfaces.AppRepository;
import ee.ivkhkdev.interfaces.AppService;
import ee.ivkhkdev.repositories.Storage;
import ee.ivkhkdev.services.AuthorService;
import ee.ivkhkdev.services.BookService;
import ee.ivkhkdev.services.LibraryCardService;
import ee.ivkhkdev.services.UserService;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class JavaConfiguration implements Configuration {
    private Map<String,Object> map = new HashMap<>();

    public JavaConfiguration() {
        init();
    }
    private void init(){
        this.map.put("authorRepository",new Storage<>("authors"));
        this.map.put("userRepository",new Storage<>("users"));
        this.map.put("bookRepository",new Storage<>("books"));
        this.map.put("input",new ConsoleInput(new Scanner(System.in)));
        this.map.put("authorAppHelper",new AuthorAppHelper((Input)map.get("input")));
        this.map.put("userAppHelper",new UserAppHelper((Input)map.get("input")));
        this.map.put("authorService",new AuthorService((AuthorAppHelper)map.get("authorAppHelper"),(AppRepository)map.get("authorRepository")));
        this.map.put("bookAppHelper",new BookAppHelper((Input)map.get("input"),(AppService)map.get("authorService")));
        this.map.put("userService",new UserService((UserAppHelper)map.get("userAppHelper"),(AppRepository)map.get("userRepository")));
        this.map.put("bookService",new BookService((BookAppHelper)map.get("bookAppHelper"),(AppRepository)map.get("bookRepository")));
        this.map.put("libraryCardAppHelper", new LibraryCardAppHelper((Input)map.get("input"),(AppService)map.get("bookService"),(AppService)map.get("authorService")));
        this.map.put("libraryCardRepository",new Storage<>("libraryCards"));
        this.map.put("libraryCardService",new LibraryCardService((LibraryCardAppHelper)map.get("libraryCardAppHelper"),(AppRepository)map.get("libraryCardRepository")));
        this.map.put("app",new App((Input)map.get("input"),(AppService)map.get("bookService"),(AppService)map.get("userService"),(AppService)map.get("authorService"),(AppService)map.get("libraryCardService")));
    }
    @Override
    public Map<String, Object> getMap() {
        try {

        }catch (Exception e){
            throw new RuntimeException("Ошибка в кофигураторе");
        }
        return this.map;
    }
}
