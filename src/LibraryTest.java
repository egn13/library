import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LibraryTest {
    private Library library;
    private Book book;

    @BeforeEach
    void setUp() {
        library = new Library();

    }

    @Test
    public void addBook() {
        // Создаем книгу
        Book book = new Book("Title", "Author", 2021, "1");

        // Добавляем книгу в библиотеку
        library.addBook(book);

        // Извлекаем все книги из библиотеки
        List<Book> books = library.getAllBooks();

        // Проверка, что размер книги в библиотеке равен 1
        assertEquals(1, books.size());

        // Проверка, что добавленная книга соответствует книге в библиотеке
        assertEquals(book, books.get(0));
    }

    @Test
    public void removeBook() {
        // Создаем книгу и добавляем её в библиотеку
        Book book = new Book("Title", "Author", 2021, "1");
        library.addBook(book);

        // Удаляем книгу по идентификатору
        library.removeBook("1");

        // Проверяем, что библиотека теперь пуста
        assertTrue(library.getAllBooks().isEmpty());
    }

    @Test
    public void searchBooks() {
        library.addBook(new Book("Title1", "Author1", 2021, "1"));

        List<Book> results = library.searchBooks("Title1");
        assertEquals(1, results.size());
        assertEquals("Title1", results.get(0).getTitle());
    }

    @AfterEach
    void tearDown() {
        library = null;
    }
}