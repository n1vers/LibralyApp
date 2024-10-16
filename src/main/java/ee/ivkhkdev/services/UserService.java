package ee.ivkhkdev.services;

import ee.ivkhkdev.App;
import ee.ivkhkdev.interfaces.Input;
import ee.ivkhkdev.model.User;
import ee.ivkhkdev.helpers.AppInputHelper;

public class UserService {
    private AppInputHelper appInputHelper = new AppInputHelper();

    public boolean add(Input input) {
        User user = appInputHelper.createUser(input);
        if(user == null ) return false;
        for (int i = 0; i < App.books.length; i++){
            if(App.books[i] == null){
                App.books[i] = user;
                break;
            }
        }
        return true;
    }

    public boolean printList() {
        return appInputHelper.printListUser();
    }
}