package ee.ivkhkdev.configuration;

import ee.ivkhkdev.helpers.AuthorAppHelper;
import ee.ivkhkdev.input.ConsoleInput;
import ee.ivkhkdev.interfaces.AppHelper;
import ee.ivkhkdev.interfaces.AppService;
import ee.ivkhkdev.interfaces.Input;
import ee.ivkhkdev.model.Author;
import ee.ivkhkdev.services.AuthorService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Scanner;

@Configuration
public class AppConfiguration {
    Input input;
    @Bean
    public Input input(){
        this.input= new ConsoleInput(new Scanner(System.in));
        return this.input;
    }
    @Bean
    public AppHelper<Author> authorHelper() {
        this.authorApphelper new AuthorAppHelper(input);
    }>

    @Bean
    public AppService<Author> authorService() {
        return new AuthorService(appHelperAuthor,appRepository);
    }
}
