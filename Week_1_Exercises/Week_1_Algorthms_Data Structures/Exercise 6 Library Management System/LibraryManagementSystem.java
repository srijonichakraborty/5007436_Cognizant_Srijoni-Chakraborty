import java.util.Arrays;
import java.util.Scanner;

class Book implements Comparable<Book> {
    int bookId;
    String title;
    String author;

    public Book(int bookId, String title, String author) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
    }

    @Override
    public int compareTo(Book other) {
        return this.title.compareTo(other.title);
    }

    @Override
    public String toString() {
        return "Book ID: " + bookId + ", Title: " + title + ", Author: " + author;
    }
}

public class LibraryManagementSystem {
    private Book[] books;
    private int bookCount;

    public LibraryManagementSystem(int capacity) {
        books = new Book[capacity];
        bookCount = 0;
    }

    public void addBook(Book book) {
        if (bookCount < books.length) {
            books[bookCount++] = book;
            Arrays.sort(books, 0, bookCount);  
        } else {
            System.out.println("Library is full.");
        }
    }

    public Book linearSearch(String title) {
        for (int i = 0; i < bookCount; i++) {
            if (books[i].title.equalsIgnoreCase(title)) {
                return books[i];
            }
        }
        return null;
    }

    public Book binarySearch(String title) {
        int left = 0, right = bookCount - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int compareResult = books[mid].title.compareToIgnoreCase(title);
            if (compareResult == 0) {
                return books[mid];
            }
            if (compareResult < 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return null;
    }

    public void displayBooks() {
        for (int i = 0; i < bookCount; i++) {
            System.out.println(books[i]);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter no. of books the library can hold: ");
        int capacity = sc.nextInt();
        sc.nextLine();

        LibraryManagementSystem library = new LibraryManagementSystem(capacity);

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Add book");
            System.out.println("2. Linear search by title");
            System.out.println("3. Binary search by title");
            System.out.println("4. Display books");
            System.out.println("5. Exit");
            System.out.print("Choose option: ");
            int choice = sc.nextInt();
            sc.nextLine(); 
            switch (choice) {
                case 1:
                    System.out.print("Enter book ID: ");
                    int bookId = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter book title: ");
                    String title = sc.nextLine();
                    System.out.print("Enter author: ");
                    String author = sc.nextLine();
                    library.addBook(new Book(bookId, title, author));
                    break;
                case 2:
                    System.out.print("Enter title to search: ");
                    String searchTitle = sc.nextLine();
                    Book foundBookLinear = library.linearSearch(searchTitle);
                    if (foundBookLinear != null) {
                        System.out.println("Book found: " + foundBookLinear);
                    } else {
                        System.out.println("Book not found.");
                    }
                    break;
                case 3:
                    System.out.print("Enter title to search: ");
                    String searchTitleBinary = sc.nextLine();
                    Book foundBookBinary = library.binarySearch(searchTitleBinary);
                    if (foundBookBinary != null) {
                        System.out.println("Book found: " + foundBookBinary);
                    } else {
                        System.out.println("Book not found.");
                    }
                    break;
                case 4:
                    System.out.println("Library books:");
                    library.displayBooks();
                    break;
                case 5:
                    System.out.println("Exited");
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid, try again.");
            }
        }
    }
}
