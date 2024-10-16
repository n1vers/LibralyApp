package ee.ivkhkdev.services;

import ee.ivkhkdev.App;
import ee.ivkhkdev.helpers.AppInputHelper;
import ee.ivkhkdev.interfaces.Input;
import ee.ivkhkdev.model.Book;

public class BookService {
    AppInputHelper appInputHelper = new AppInputHelper();
    public boolean add(Input input) {
        Book book = appInputHelper.createbook(input);
        if(book == null ) return false;
        for (int i = 0; i < App.books.length; i++){
            if(App.books[i] == null){
                App.books[i] = book;
                break;
            }
        }
        return true;
    }

}
