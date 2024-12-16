import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Comparator;
import java.util.stream.Collectors;

public class Library {
    private List<Book> books;
    private final String filePath = "library.dat";

    public Library() {
        books = new ArrayList<>();
        loadBooks();
    }

    public void addBook(Book book) {
        books.add(book);
        saveBooks();
    }

    public boolean removeBook(String id) {
        books.removeIf(book -> book.getId().equals(id));
        saveBooks();
        return false;
    }

    public List<Book> searchBooks(String query) {
        return books.stream()
                .filter(book -> book.getTitle().contains(query) || book.getAuthor().contains(query))
                .toList();
    }

    public List<Book> getAllBooks() {
        return books;
    }

    private void saveBooks() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(books);
        } catch (IOException e) {
            System.out.println("Ошибка при сохранении книги: " + e.getMessage());
        }
    }

    private void loadBooks() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            books = (List<Book>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Ошибка при загрузке книг: " + e.getMessage());
        }
    }

    public void sortBooksByAuthor() {
        books.sort((b1, b2) -> b1.getAuthor().compareTo(b2.getAuthor()));
    }

    public void sortBooksByYear() {
        books.sort((b1, b2) -> Integer.compare(b1.getYear(), b2.getYear()));
    }

    // Методы для работы FX
    public List<Book> sortBooksByTitleFX() {
        return books.stream()
                .sorted(Comparator.comparing(Book::getTitle))
                .collect(Collectors.toList());
    }

    public List<Book> sortBooksByAuthorFX() {
        return books.stream()
                .sorted(Comparator.comparing(Book::getAuthor))
                .collect(Collectors.toList());
    }

    public List<Book> sortBooksByYearFX() {
        return books.stream()
                .sorted(Comparator.comparingInt(Book::getYear))
                .collect(Collectors.toList());
    }
}
