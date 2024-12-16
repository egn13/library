import java.io.Serializable;
import java.util.Objects;

public class Book implements Serializable {
    private String title;
    private String author;
    private int year;
    private String id;

    public Book(String title, String author, int year, String id) {
        this.title = title;
        this.author = author;
        this.year = year;
        this.id = id;
    }

    // Геттеры
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public int getYear() { return year; }
    public String getId() { return id; }

    @Override
    public String toString() {
        return String.format("ID: %s, Title: %s, Author: %s, Year: %d", id, title, author, year);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Book book = (Book) obj;
        return year == book.year && Objects.equals(title, book.title) &&
                Objects.equals(author, book.author) && Objects.equals(id, book.id);
    }
}
