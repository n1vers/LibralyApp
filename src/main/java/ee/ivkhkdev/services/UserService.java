package ee.ivkhkdev.services;

import ee.ivkhkdev.App;
import ee.ivkhkdev.interfaces.Input;
import ee.ivkhkdev.model.User;
import ee.ivkhkdev.helpers.AppInputHelper;
import ee.ivkhkdev.storages.Storage;

import java.util.List;

public class UserService {
    private final Storage<User> storage;
    private AppInputHelper appInputHelper ;

    public UserService(AppInputHelper appInputHelper,Storage<User> storage) {
        this.appInputHelper = appInputHelper;
        this.storage=storage;
    }

    public boolean add( List<User> users) {
        User user = appInputHelper.createUser();
        if(user == null ) return false;
        for (int i = 0; i <= users.size(); i++){
            if(i == 0){
                users.add(user);
                storage.save(users);
                break;
            }else if(users.get(i) == null){
                users.add(user);
                storage.save(users);
                break;
            }
        }
        return true;
    }

    public boolean printList(List<User> users) {
        return appInputHelper.printListUser(users);
    }
}