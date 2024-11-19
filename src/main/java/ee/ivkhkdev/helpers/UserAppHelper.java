package ee.ivkhkdev.helpers;

import ee.ivkhkdev.interfaces.AppHelper;
import ee.ivkhkdev.interfaces.Input;
import ee.ivkhkdev.model.User;

import java.util.List;

public class UserAppHelper implements AppHelper<User> {
    private final Input input;

    public UserAppHelper(Input input) {
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

    @Override
    public List<User> edit(List<User> users) {
        try {
            System.out.println("---- Редактирование книги -----");
            this.printList(users);
            System.out.print("Выберите пользователя: ");
            int numberUser = Integer.parseInt(input.nextLine());
            System.out.println("Имя: " + users.get(numberUser-1).getFirstName());
            System.out.print("Изменить (y/n): ");
            String choice = input.nextLine();
            if(choice.equals("y")){
                System.out.print("Новое имя: ");
                users.get(numberUser-1).setFirstName(input.nextLine());
            }
            System.out.println("Фамилия: " + users.get(numberUser-1).getLastName());
            System.out.print("Изменить (y/n): ");
            choice = input.nextLine();
            if(choice.equals("y")){
                System.out.print("Новая фамилия: ");
                users.get(numberUser-1).setLastName(input.nextLine());
            }
            System.out.println("Телефон: " + users.get(numberUser-1).getLastName());
            System.out.print("Изменить (y/n): ");
            choice = input.nextLine();
            if(choice.equals("y")){
                System.out.print("Новый телефон: ");
                users.get(numberUser-1).setPhone(input.nextLine());
            }
            return users;
        }catch (Exception e){
            System.out.println("Error: "+e.getMessage());
            return null;
        }

    }
}