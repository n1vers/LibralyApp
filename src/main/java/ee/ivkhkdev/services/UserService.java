package ee.ivkhkdev.services;


import ee.ivkhkdev.interfaces.AppHelper;
import ee.ivkhkdev.interfaces.Service;
import ee.ivkhkdev.model.User;
import ee.ivkhkdev.interfaces.Repository;


import java.util.List;

public class UserService implements Service {

    private final Repository<User> repository;
    private AppHelper appHelperUser;

    public UserService( AppHelper<User> appHelperUser, Repository<User> repository) {
        this.appHelperUser = appHelperUser;
        this.repository = repository;
    }

    public boolean add() {
        User user = (User) appHelperUser.create();
        try {
        repository.save(user);
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

        return appHelperUser.printList(repository.load());
    }

    @Override
    public List list() {
        return repository.load();
    }


}