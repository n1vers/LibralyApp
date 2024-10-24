package ee.ivkhkdev.helpers;

import ee.ivkhkdev.input.Input;
import ee.ivkhkdev.model.User;

import java.util.List;

public class AppHelperUser implements AppHelper<User> {
    private final Input input;


    public AppHelperUser(Input input) {
        this.input = input;

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
    public boolean printList(List<User>users) {
        try {
            if(users.size() == 0) return false;
            for(int i = 0; i < users.size(); i++){
                System.out.printf("%d. %s %s. %s%n",
                        i+1,
                        users.get(i).getFirstName(),
                        users.get(i).getLastName(),
                        users.get(i).getPhone()
                );
            }
            return true;
        }catch (Exception e){
            System.out.println("Error: "+e.toString());
            return false;
        }
    }
}