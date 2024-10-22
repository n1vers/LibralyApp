package ee.ivkhkdev.helpers;

import ee.ivkhkdev.interfaces.Input;
import ee.ivkhkdev.model.Author;
import ee.ivkhkdev.model.Book;

public class AppHelperAuthor implements AppHelper<Author> {
    private final Input input;

    public AppHelperAuthor(Input input) {
        this.input=input;
    }

    @Override
    public Author create() {
        Author author = new Author();
        try {
            System.out.print("Имя Автора: ");
            author.setFirstName(input.nextLine());
            System.out.print("Фамилия Автора: ");
            author.setFirstName(input.nextLine());
            return author;
        } catch (Exception e){
            return null;
        }
    }

    @Override
    public boolean printList() {
        return false;
    }

}

