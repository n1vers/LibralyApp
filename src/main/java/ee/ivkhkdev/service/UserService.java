package ee.ivkhkdev.service;

import ee.ivkhkdev.App;
import ee.ivkhkdev.interfaces.Input;
import ee.ivkhkdev.model.User;
import ee.ivkhkdev.helpers.AppInputHelper;

public class UserService {
    private  AppInputHelper appInputHelper= new AppInputHelper();

    public boolean addUser(Input input) {
        User user = appInputHelper.createUser(input);
        if (user != null) return false;
        for (int i=0;i<App.users.length;i++){
            if (App.users[i]==null){
                App.users[i]=user;
                break;
            }
        }
        return true;
    }


    public void printList() {
        appInputHelper.printListUser();
    }
}
