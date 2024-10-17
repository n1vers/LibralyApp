package ee.ivkhkdev.model;

import java.io.Serializable;
import java.util.*;

public class Book implements Serializable {
    private UUID id;
    private String title;
    private List<Author> author = new ArrayList<>();
    private int publicationYear;

    public Book() {
        this.id = UUID.randomUUID();
    }

    public Book(String title, List<Author> author, int publicationYear) {
        this.id = UUID.randomUUID();
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Author> getAuthor() {
        return author;
    }

    public void setAuthor(List<Author> author) {
        this.author = author;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return publicationYear == book.publicationYear && Objects.equals(id, book.id) && Objects.equals(title, book.title) && Objects.equals(author, book.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, author, publicationYear);
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author=" + author +
                ", publicationYear=" + publicationYear +
                '}';
    }
}