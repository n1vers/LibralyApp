package ee.ivkhkdev.storages;

import ee.ivkhkdev.App;
import ee.ivkhkdev.model.Book;
import ee.ivkhkdev.model.User;

import java.io.*;
import java.util.List;

public class Storage<T> {

    private final String fileName;

    public Storage(String fileName) {
        this.fileName=fileName;
    }

    public void save(List<T> entities){
        FileOutputStream fileOutputStream;
        ObjectOutputStream objectOutputStream;
        try {
            fileOutputStream=new FileOutputStream(fileName);
            objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(entities);
            objectOutputStream.flush();
        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден");
        } catch (IOException e) {
            System.out.println("Ошибка ввода");
        }
    }
    public List<T> load(){
        FileInputStream fileInputStream;
        ObjectInputStream objectInputStream;
        try {
            fileInputStream = new FileInputStream(fileName);
            objectInputStream = new ObjectInputStream(fileInputStream);
            return  (List<T>) objectInputStream.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("нет такого файла");
        } catch (IOException e) {
            System.out.println("Ошибка вывода");
        } catch (ClassNotFoundException e) {
            System.out.println("Не найден класс");
        }
        return null;
    }
}
