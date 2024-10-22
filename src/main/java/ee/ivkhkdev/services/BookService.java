package ee.ivkhkdev.services;

import ee.ivkhkdev.helpers.AppHelper;
import ee.ivkhkdev.model.Book;
import ee.ivkhkdev.repositories.Repository;


import java.util.List;

public class BookService implements Service {
    private final List<Book> books;

    private Repository<Book> repository;
    private AppHelper appHelperBook;
    public BookService(List<Book>books,AppHelper appHelperBook, Repository<Book> repository) {
        this.books=books;
        this.appHelperBook = appHelperBook;
        this.repository = repository;
    }

    public boolean add() {
        Book book = (Book) appHelperBook.create();
        if(book == null ) return false;
        for (int i = 0; i <= books.size(); i++){
            if(i == 0 ){
                books.add(book);
                repository.save(book);
                break;
            }else if(books.get(i) == null) {
                books.add(book);
                repository.save(book);
                break;
            }
        }
        return true;
    }

    @Override
    public boolean printList() {

        return false;
    }
}