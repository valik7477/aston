import java.util.*;

public class Student { //Измел в second на компе
    List<Book> books;
    private final String name;

    public Student(String name, List<Book> books) {
        this.books = books;
        this.name = name;
    }

    public List<Book> getBooks() {
        return books;
    }

    @Override
    public String toString() {
        return "Student - " + name +
                " books=" + books;
    }
    public static void main(String[] args) {
        // Создаем книги
        Book book1 = new Book("Title1", 256, 1960);
        Book book2 = new Book("Title2", 223, 1990);
        Book book3 = new Book("Title3", 13, 2005);
        Book book4 = new Book("Title4", 190, 1900);
        Book book5 = new Book("Title5", 120, 2023);

        Book book6 = new Book("Title6", 123, 2020);
        Book book7 = new Book("Title7", 15, 1999);
        Book book8 = new Book("Title8", 2, 2025);
        Book book9 = new Book("Title9", 620, 1901);
        Book book10 = new Book("Title10", 123, 2023);

        // Список книг первого студента
        List<Book> student1Books = new ArrayList<>(List.of(book1, book2, book3, book4, book5));
        Student student1 = new Student("Vasya",List.copyOf(student1Books)); // Иммутабельный список

        // Список книг второго студента
        List<Book> student2Books = new ArrayList<>(List.of(book6, book7, book8, book9, book10));
        Student student2 = new Student("Petya",List.copyOf(student2Books)); // Иммутабельный список

        List<Student> students = List.of(student1, student2); // Иммутабельный список студентов

        // Обрабатываем поток и находим нужную книгу
        students.stream()
                .peek(System.out::println) // Информация о студентах
                .flatMap(student -> student.getBooks().stream()) // Получить все книги
                .sorted(Comparator.comparingInt(Book::getPage)) // Сортировать по количеству страниц
                .distinct() // Оставить только уникальные книги
                .filter(book -> book.getYear() > 2000) // Отфильтровать книги после 2000 года
                .limit(3) // Ограничить 3 элементами
                .findFirst() // Найти первую книгу (короткое замыкание)
                .ifPresentOrElse( // Обработка найденной книги или отсутствие
                        book -> System.out.println("Найденная книга была выпущена в " + book.getYear() + " году."),
                        () -> System.out.println("Книга, соответствующая условиям, отсутствует.")
                );
    }
}

class Book {
    private final String title;
    private final int page;
    private final int year;
    private q;

    public Book(String title, int page, int year) {
        this.title = title;
        this.page = page;
        this.year = year;
    }

    public String getTitle() {
        return title;
    }

    public int getPage() {
        return page;
    }

    public int getYear() {
        return year;
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", page=" + page +
                ", year=" + year +
                '}';
    }
}

