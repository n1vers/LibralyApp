package ee.ivkhkdev.helpers;

import ee.ivkhkdev.interfaces.Input;
import ee.ivkhkdev.model.User;

import java.util.List;

public class AppHelperUser implements AppHelper<User> {
    private final Input input;

    public AppHelperUser(Input input) {
        this.input=input;
    }
   @Override
    public User create() {
        try {
            User user = new User();
            System.out.print("Имя пользователя: ");
            user.setFirstName(input.nextLine());
            System.out.print("Фамилия пользователя: ");
            user.setLastName(input.nextLine());
            System.out.print("Телефон: ");
            user.setPhone(input.nextLine());
            return user;
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public boolean printList(){
      return false;
    }
}
