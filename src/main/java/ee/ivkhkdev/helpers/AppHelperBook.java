package ee.ivkhkdev.helpers;

import ee.ivkhkdev.input.Input;
import ee.ivkhkdev.model.Author;
import ee.ivkhkdev.model.Book;
import ee.ivkhkdev.services.AuthorService;
import ee.ivkhkdev.services.Service;

import java.util.List;

public class AppHelperBook implements AppHelper<Book> {

    private final Input input;
    private final AuthorService authorSevice;


    public AppHelperBook(Input input, Service<Author> authorService) {
        this.input=input;
        this.authorSevice= (AuthorService) authorService;
    }

    @Override
    public Book create() {
        Book book = new Book();
        try {
            System.out.print("Название книги: ");
            book.setTitle(input.nextLine());
            authorSevice.print();
            System.out.print("Добавить автора в список(y/n): ");
            String addAuthorChoose = input.nextLine();
            if(addAuthorChoose.equals("y")) {
                System.out.println("выход из добавление книги");
                return null;
            }
            System.out.println("Количество авторов в книге:");
            int countBookAuthors = Integer.parseInt(input.nextLine());
            for (int i = 0; i < countBookAuthors; i++){
                System.out.printf("Выберите номер автора из списка (%d автор из %d%n): ", i+1,countBookAuthors);
                int numberAuthor = Integer.parseInt(input.nextLine());
                book.getAuthor().add(authorSevice.list().get(numberAuthor-1));

            }
            System.out.print("Год издания: ");
            book.setPublicationYear(Integer.parseInt(input.nextLine()));
            return book;

        }catch (Exception e){
            return null;
        }
    }

    @Override
    public boolean printList(List<Book> books) {
        try {
            if(books.size() == 0) return false;
            for(int i = 0; i < books.size(); i++){
                StringBuilder sbAuthors = new StringBuilder();
                for (int j = 0; j < books.get(i).getAuthor().size(); j++) {
                    sbAuthors.append(books.get(i).getAuthor().get(j).getFirstName());
                    sbAuthors.append(" ");
                    sbAuthors.append(books.get(i).getAuthor().get(j).getLastName());
                    sbAuthors.append(". ");
                }
                System.out.printf("%d. %s. %s%d%n", i+1,books.get(i).getTitle(),sbAuthors.toString(),books.get(i).getPublicationYear());
            }
            return true;
        }catch (Exception e){
            System.out.println("Error: "+e.toString());
            return false;
        }
    }

}
