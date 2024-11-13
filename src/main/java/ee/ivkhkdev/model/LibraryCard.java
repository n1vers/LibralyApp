package ee.ivkhkdev.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

public class LibraryCard implements Serializable {
    private UUID id;
    private Book book;
    private User user;
    private LocalDate borrowdBookDate;
    private LocalDate returnBookDate;

    public LibraryCard() {
        this.id = UUID.randomUUID();
    }

    public LibraryCard(Book book, User user, LocalDate borrowdBookDate, LocalDate returnBookDate) {
        this.id = UUID.randomUUID();
        this.book = book;
        this.user = user;
        this.borrowdBookDate = borrowdBookDate;
        this.returnBookDate = returnBookDate;
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

    public LocalDate getBorrowedDate() {
        return borrowdBookDate;
    }

    public void setBorrowDate(LocalDate borrowdBookDate) {
        this.borrowdBookDate = borrowdBookDate;
    }

    public LocalDate getReturnBookDate() {
        return returnBookDate;
    }

    public void setReturnDate(LocalDate returnBookDate) {
        this.returnBookDate = returnBookDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LibraryCard that = (LibraryCard) o;
        return Objects.equals(id, that.id) && Objects.equals(book, that.book) && Objects.equals(user, that.user) && Objects.equals(borrowdBookDate, that.borrowdBookDate) && Objects.equals(returnBookDate, that.returnBookDate);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(id);
        result = 31 * result + Objects.hashCode(book);
        result = 31 * result + Objects.hashCode(user);
        result = 31 * result + Objects.hashCode(borrowdBookDate);
        result = 31 * result + Objects.hashCode(returnBookDate);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("LibraryCard{");
        sb.append("id=").append(id);
        sb.append(", book=").append(book);
        sb.append(", user=").append(user);
        sb.append(", borrowdBookDate=").append(borrowdBookDate);
        sb.append(", returnBookDate=").append(returnBookDate);
        sb.append('}');
        return sb.toString();
    }
}