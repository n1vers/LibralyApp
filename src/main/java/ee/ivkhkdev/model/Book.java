package ee.ivkhkdev.model;

import java.util.Arrays;
import java.util.Objects;
import java.util.UUID;

public class Book {
    private UUID id;
    private String title;
    private Author[] author = new Author[10];
    private int publicationYear;

    public Book() {
        this.id = UUID.randomUUID();
    }

    public Book( String title, Author[] author, int publicationYear) {
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

    public Author[] getAuthor() {
        return author;
    }

    public void setAuthor(Author[] author) {
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
        return publicationYear == book.publicationYear && Objects.equals(id, book.id) && Objects.equals(title, book.title) && Objects.deepEquals(author, book.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, Arrays.hashCode(author), publicationYear);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Book{");
        sb.append("id=").append(id);
        sb.append(", title='").append(title).append('\'');
        sb.append(", author=").append(Arrays.toString(author));
        sb.append(", publicationYear=").append(publicationYear);
        sb.append('}');
        return sb.toString();
    }
}