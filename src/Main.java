import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final Library library = new Library();
    private static final ExecutorService executorService = Executors.newCachedThreadPool();

    public static void main(String[] args) {
        while (true) {
            System.out.println("Выберите действие:");
            System.out.println("1. Добавить книгу");
            System.out.println("2. Удалить книгу");
            System.out.println("3. Искать книгу");
            System.out.println("4. Просмотреть все книги");
            System.out.println("5. Отсортировать книги");
            System.out.println("6. Выход");

            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    addBook();
                    break;
                case "2":
                    removeBook();
                    break;
                case "3":
                    searchBook();
                    break;
                case "4":
                    listBooks();
                    break;
                case "5":
                    sortBooks();
                    break;
                case "6":
                    executorService.shutdown();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Неверный выбор. Попробуйте еще раз.");
            }
        }
    }

    private static void addBook() {
        System.out.print("Введите название книги: ");
        String title = scanner.nextLine();
        System.out.print("Введите автора: ");
        String author = scanner.nextLine();
        System.out.print("Введите год издания: ");
        int year = Integer.parseInt(scanner.nextLine());
        System.out.print("Введите уникальный ID: ");
        String id = scanner.nextLine();

        Book book = new Book(title, author, year, id);
        library.addBook(book);
        System.out.println("Книга добавлена!");
    }

    private static void removeBook() {
        System.out.print("Введите уникальный ID книги для удаления: ");
        String id = scanner.nextLine();
        library.removeBook(id);
        System.out.println("Книга удалена, если она существовала.");
    }

    private static void searchBook() {
        System.out.print("Введите название или автора для поиска: ");
        String query = scanner.nextLine();

        executorService.submit(() -> {
            List<Book> results = library.searchBooks(query);
            System.out.println("Результаты поиска:");
            results.forEach(System.out::println);
        });
    }

    private static void listBooks() {
        List<Book> allBooks = library.getAllBooks();
        System.out.println("Список всех книг:");
        allBooks.forEach(System.out::println);
    }

    private static void sortBooks() {
        System.out.println("Введите критерий сортировки: 1 - По автору, 2 - По году");

        if (scanner.hasNextInt()) {
            int criterion = scanner.nextInt();
            scanner.nextLine(); // Очищаем буфер

            if (criterion == 1) {
                library.sortBooksByAuthor();
            } else if (criterion == 2) {
                library.sortBooksByYear();
            } else {
                System.out.println("Неверный критерий.");
            }
        } else {
            System.out.println("Пожалуйста, введите целое число.");
            scanner.next(); // Чистим неверный ввод
        }

        System.out.println("Книги отсортированы.");
    }
}
