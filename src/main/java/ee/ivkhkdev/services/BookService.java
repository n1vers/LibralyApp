package ee.ivkhkdev.services;

import ee.ivkhkdev.App;
import ee.ivkhkdev.helpers.AppInputHelper;
import ee.ivkhkdev.interfaces.Input;
import ee.ivkhkdev.model.Book;
import ee.ivkhkdev.storages.Storage;

import java.util.List;

public class BookService {

    private final Storage<Book> storage;
    private AppInputHelper appInputHelper ;

    public BookService(AppInputHelper appInputHelper, Storage<Book> storage) {
        this.appInputHelper = appInputHelper;
        this.storage=storage;
    }

    public boolean add( List<Book> books) {
        Book book = appInputHelper.createbook();
        if(book == null ) return false;
        for (int i = 0; i <= books.size(); i++){
            if(i == 0){
                books.add(book);
                storage.save(books);
                break;
            }else if(books.get(i) == null){
                books.add(book);
                storage.save(books);
                break;
            }
        }
        return true;
    }
}