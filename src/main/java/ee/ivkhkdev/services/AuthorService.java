package ee.ivkhkdev.services;

import ee.ivkhkdev.helpers.AppHelper;
import ee.ivkhkdev.model.Author;
import ee.ivkhkdev.repositories.Repository;

import java.util.List;

public class AuthorService implements Service {
    private final List<Author> authors;

    private Repository<Author> repository;
    private AppHelper appHelperAuthor;
    public AuthorService(List<Author> authors,AppHelper<Author> appHelperAuthor, Repository<Author> repository) {
        this.authors=authors;
        this.appHelperAuthor = appHelperAuthor;
        this.repository = repository;
    }

    public boolean add() {
        Author author= (Author) appHelperAuthor.create();
        if(author == null ) return false;
        try {
            for (int i = 0; i <= authors.size(); i++){
                if(i == 0 ){
                    authors.add(author);
                    repository.save(author);
                    break;
                }else if(authors.get(i) == null) {
                    authors.add(author);
                    repository.save(author);
                    break;
                }
            }
            return true;
        }catch (Exception e){
            System.out.println("Error: "+e.toString());
            return false;
        }
    }

    @Override
    public boolean print() {
        return appHelperAuthor.printList(authors);
    }


    @Override
    public List<Author> list() {
        return authors;
    }
}
