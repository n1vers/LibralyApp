package ee.ivkhkdev.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

public class LiblaryCard implements Serializable {
    private UUID id;
    private Book book ;
    private User user;
    private LocalDate borrowDate;
    private LocalDate returnDate;

    public LiblaryCard() {
        this.id = UUID.randomUUID();
    }

    public LiblaryCard(UUID id, Book book, User user, LocalDate borrowDate, LocalDate returnDate) {
        this.id = UUID.randomUUID();
        this.book = book;
        this.user = user;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDate getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(LocalDate borrowDate) {
        this.borrowDate = borrowDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LiblaryCard that = (LiblaryCard) o;
        return Objects.equals(id, that.id) && Objects.equals(book, that.book) && Objects.equals(user, that.user) && Objects.equals(borrowDate, that.borrowDate) && Objects.equals(returnDate, that.returnDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, book, user, borrowDate, returnDate);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("LiblaryCard{");
        sb.append("id=").append(id);
        sb.append(", book=").append(book);
        sb.append(", user=").append(user);
        sb.append(", borrowDate=").append(borrowDate);
        sb.append(", returnDate=").append(returnDate);
        sb.append('}');
        return sb.toString();
    }
}
