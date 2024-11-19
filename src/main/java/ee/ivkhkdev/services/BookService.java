package ee.ivkhkdev.services;

import ee.ivkhkdev.interfaces.AppHelper;
import ee.ivkhkdev.interfaces.AppService;
import ee.ivkhkdev.model.Book;
import ee.ivkhkdev.interfaces.AppRepository;


import java.util.List;

public class BookService implements AppService {

    private AppRepository<Book> repository;
    private AppHelper<Book> bookAppHelper;

    public BookService(AppHelper<Book> bookAppHelper, AppRepository<Book> repository) {
        this.bookAppHelper = bookAppHelper;
        this.repository = repository;
    }
    public boolean add(){
        try {
            Book book = bookAppHelper.create();
            if(book == null) return false;
            repository.save(book);
            return true;
        }catch (Exception e){
            System.out.println("Error: "+e.toString());
            return false;
        }
    }

    @Override
    public boolean edit() {
        List<Book> modifiedBooks = bookAppHelper.edit(repository.load());
        if(modifiedBooks == null){
            return false;
        }
        repository.saveAll(modifiedBooks);
        return true;
    }

    @Override
    public boolean print() {
        return bookAppHelper.printList(repository.load());
    }

    @Override
    public List list() {
        return repository.load();
    }
}