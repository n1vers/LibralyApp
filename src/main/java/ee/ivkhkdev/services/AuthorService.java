package ee.ivkhkdev.services;

import ee.ivkhkdev.interfaces.AppHelper;
import ee.ivkhkdev.interfaces.Service;
import ee.ivkhkdev.model.Author;
import ee.ivkhkdev.interfaces.Repository;

import java.util.List;

public class AuthorService implements Service {

    private Repository<Author> repository;
    private AppHelper<Author> appHelperAuthor;

    public AuthorService( AppHelper<Author> appHelperAuthor, Repository<Author> repository) {

        this.appHelperAuthor = appHelperAuthor;
        this.repository = repository;
    }

    public boolean add(){
        Author author = appHelperAuthor.create();
        if(author == null){
            return false;
        }
        try {
            repository.save(author);
            return true;
        }catch (Exception e){
            System.out.println("Error: "+e.toString());
            return false;
        }
    }

    @Override
    public boolean edit() {
        return false;
    }

    @Override
    public boolean print() {
        return appHelperAuthor.printList(repository.load());
    }

    @Override
    public List<Author> list() {
        return repository.load();
    }
}