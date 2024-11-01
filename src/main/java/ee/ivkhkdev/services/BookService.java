package ee.ivkhkdev.services;

import ee.ivkhkdev.interfaces.AppHelper;
import ee.ivkhkdev.interfaces.Service;
import ee.ivkhkdev.model.Book;
import ee.ivkhkdev.interfaces.Repository;


import java.util.List;

public class BookService implements Service {

    private Repository<Book> repository;
    private AppHelper appHelperBook;

    public BookService(AppHelper<Book> appHelperBook, Repository<Book> repository) {
        this.appHelperBook = appHelperBook;
        this.repository = repository;
    }

    public boolean add() {
        Book book = (Book) appHelperBook.create();
        if (book == null) {
            return false;
        }
        try {
            repository.save(book);
            return true;
        } catch (Exception e) {
            System.out.println("Error: " + e.toString());
            return false;
        }
    }
    @Override
    public boolean print() {

        return appHelperBook.printList(repository.load());
    }

    @Override
    public List list() {
        return repository.load();
    }
}