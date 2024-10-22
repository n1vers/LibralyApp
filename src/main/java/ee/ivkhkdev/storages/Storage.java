package ee.ivkhkdev.storages;

import ee.ivkhkdev.App;
import ee.ivkhkdev.model.Book;
import ee.ivkhkdev.model.User;
import ee.ivkhkdev.repositories.Repository;

import java.io.*;
import java.util.List;

public class Storage<T> implements Repository<T> {

    private final String fileName;

    public Storage(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void save(T entity){
        List<T> entities=this.load();
        entities.add(entity);
        FileOutputStream fileOutputStream;
        ObjectOutputStream objectOutputStream;
        try {
            fileOutputStream = new FileOutputStream(fileName);
            objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(entity);
            objectOutputStream.flush();

        } catch (FileNotFoundException e) {
            System.out.println("Не найден файл");
        } catch (IOException e) {
            System.out.println("Ошибка ввода");
        }
    }



    @Override
    public List<T> load(){
        FileInputStream fileInputStream;
        ObjectInputStream objectInputStream;
        try {
            fileInputStream = new FileInputStream(fileName);
            objectInputStream = new ObjectInputStream(fileInputStream);
            return (List<T>) objectInputStream.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("Нет такого файла");
        } catch (IOException e) {
            System.out.println("Ошибка вывода");
        } catch (ClassNotFoundException e) {
            System.out.println("Не найден класс ");
        }
        return null;
    }
}