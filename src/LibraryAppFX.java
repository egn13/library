import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.List;

public class LibraryAppFX extends Application {

    private Library library;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        library = new Library();
        primaryStage.setTitle("Управление библиотекой");

        GridPane grid = createFormPane();
        Scene scene = new Scene(grid, 400, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private GridPane createFormPane() {
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10));
        grid.setVgap(8);
        grid.setHgap(10);

        // Элементы формы
        Label titleLabel = new Label("Название:");
        TextField titleInput = new TextField();

        Label authorLabel = new Label("Автор:");
        TextField authorInput = new TextField();

        Label yearLabel = new Label("Год:");
        TextField yearInput = new TextField();

        Label idLabel = new Label("ID:");
        TextField idInput = new TextField();

        Button addButton = new Button("Добавить книгу");
        Button removeButton = new Button("Удалить книгу");
        Button searchButton = new Button("Поиск книги");
        Button listButton = new Button("Список книг");

        Button sortByTitleButton = new Button("Сортировать по названию");
        Button sortByAuthorButton = new Button("Сортировать по автору");
        Button sortByYearButton = new Button("Сортировать по году");

        TextArea outputArea = new TextArea();
        outputArea.setEditable(false);

        // Добавление элементов на панель
        grid.add(titleLabel, 0, 0);
        grid.add(titleInput, 1, 0);
        grid.add(authorLabel, 0, 1);
        grid.add(authorInput, 1, 1);
        grid.add(yearLabel, 0, 2);
        grid.add(yearInput, 1, 2);
        grid.add(idLabel, 0, 3);
        grid.add(idInput, 1, 3);
        grid.add(addButton, 0, 4);
        grid.add(removeButton, 0, 5);
        grid.add(listButton, 0, 6);
        grid.add(searchButton, 0, 7);
        grid.add(sortByTitleButton, 1, 4);
        grid.add(sortByAuthorButton, 1, 5);
        grid.add(sortByYearButton, 1, 6);
        grid.add(outputArea, 0, 8, 2, 1);

        // Обработчики кнопок
        addButton.setOnAction(e -> {
            String title = titleInput.getText();
            String author = authorInput.getText();
            int year = Integer.parseInt(yearInput.getText());
            String id = idInput.getText();
            library.addBook(new Book(title, author, year, id));
            outputArea.appendText("Книга добавлена!\n");
            clearInputs(titleInput, authorInput, yearInput, idInput);
        });

        searchButton.setOnAction(e -> {
            String query = titleInput.getText();
            List<Book> results = library.searchBooks(query);
            outputArea.appendText("Результаты поиска:\n");
            results.forEach(book -> outputArea.appendText(book.toString() + "\n"));
        });

        listButton.setOnAction(e -> {
            List<Book> allBooks = library.getAllBooks();
            outputArea.appendText("Список книг:\n");
            allBooks.forEach(book -> outputArea.appendText(book.toString() + "\n"));
        });

        sortByTitleButton.setOnAction(e -> {
            List<Book> sortedBooks = library.sortBooksByTitleFX();
            outputArea.appendText("Сортировка по названию:\n");
            sortedBooks.forEach(book -> outputArea.appendText(book.toString() + "\n"));
        });

        sortByAuthorButton.setOnAction(e -> {
            List<Book> sortedBooks = library.sortBooksByAuthorFX();
            outputArea.appendText("Сортировка по автору:\n");
            sortedBooks.forEach(book -> outputArea.appendText(book.toString() + "\n"));
        });

        sortByYearButton.setOnAction(e -> {
            List<Book> sortedBooks = library.sortBooksByYearFX();
            outputArea.appendText("Сортировка по году:\n");
            sortedBooks.forEach(book -> outputArea.appendText(book.toString() + "\n"));
        });

        // Логика удаления книги
        removeButton.setOnAction(e -> {
            String id = idInput.getText();
            library.removeBook(id);
            outputArea.appendText("Книга удалена, если она существовала.\n");
            idInput.clear();
        });

        return grid;
    }

    private void clearInputs(TextField... inputs) {
        for (TextField input : inputs) {
            input.clear();
        }
    }
}
