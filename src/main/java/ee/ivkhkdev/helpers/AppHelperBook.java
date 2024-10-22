package ee.ivkhkdev.helpers;

import ee.ivkhkdev.interfaces.Input;
import ee.ivkhkdev.model.Author;
import ee.ivkhkdev.model.Book;
import ee.ivkhkdev.services.AuthorService;

import java.sql.SQLOutput;

public class AppHelperBook implements AppHelper<Book> {

    private final Input input;
    private final AuthorService authorSevice;


    public AppHelperBook(Input input, AuthorService authorService) {
        this.input=input;
        this.authorSevice=authorService;
    }

    @Override
    public Book create() {
        Book book = new Book();
        try {
            System.out.print("Название книги: ");
            book.setTitle(input.nextLine());
            authorSevice.printList();
            System.out.print("Добавить автора в список(y/n): ");
            String addAuthorChoose = input.nextLine();
            if(addAuthorChoose.equals("y")) {
                System.out.println("выход из добавление книги");
                return null;
            }
            System.out.println("Количество авторов в книге:");
            int countBookAuthors = Integer.parseInt(input.nextLine());
            for (int i = 0; i < countBookAuthors; i++){
                System.out.printf("Выберите номер автора (%d автор из %d%n):", i+1;CountBookAuthors;);
                int numberOfAuthors = Integer.parseInt(input.nextLine());
                book.getAuthor().add(authorService.printList(authorService.getAuthors().))

            }
            System.out.print("Год издания: ");
            book.setPublicationYear(Integer.parseInt(input.nextLine()));
            return book;

        }catch (Exception e){
            return null;
        }
    }

    @Override
    public boolean printList() {
        return false;
    }

}
