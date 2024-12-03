import java.io.File;
import java.util.Arrays;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.Scanner;

public class BookStoreApp {
    public static void main(String[] args) {
        Books[] booksArray = new Books[7];

        Scanner userInput = new Scanner(System.in);
        int choice;
        // Menu options with error handling for file reading
        do {
            System.out.println("\n--- Program Menu ---");
            System.out.println("\t1: Read books from database");
            System.out.println("\t2: Print Book List");
            System.out.println("\t3: Search for a book based on ID");
            System.out.println("\t4: Show sales in a given month");
            System.out.println("\t5: Sort books based on descending total sales");
            System.out.println("\t6: Quit");
            System.out.print("Enter choice: ");
            choice = userInput.nextInt();

            switch (choice) {
                case 1:
                    try {
                        // Load books from the file
                        Scanner fileScanner = new Scanner(new File("data.txt"));
                        for (int i = 0; i < 7 && fileScanner.hasNextLine(); i++) {
                            String title = fileScanner.nextLine().trim();
                            String author = fileScanner.nextLine().trim();
                            int year = Integer.parseInt(fileScanner.nextLine().trim());
                            String salesLine = fileScanner.nextLine().trim();
                            // Check if sales data is provided
                            if (!salesLine.equals("None")) {
                                String[] salesStr = salesLine.split(" ");
                                int[] sales = Arrays.stream(salesStr).mapToInt(Integer::parseInt).toArray();
                                Books book = new Books(title, author, year);
                                for (int j = 0; j < sales.length; j++) {
                                    book.setSaleNumbers(sales[j], j + 1);
                                }
                                booksArray[i] = book;
                            } else {
                                // No sales data, create a book without sales
                                booksArray[i] = new Books(title, author, year);
                            }
                        }
                        fileScanner.close();
                    } catch (Exception e) {
                        System.out.println("Error reading file: " + e.getMessage());
                    }
                    break;
                case 2:
                    // Print the book list
                    printBookList(booksArray);
                    break;
                case 3:
                    // Search for a book based on ID
                    searchBookById(booksArray, userInput);
                    break;
                case 4:
                    // Show sales in a given month
                    showSalesForMonth(booksArray, userInput);
                    break;
                case 5:
                    // Sort books based on descending total sales
                    sortBooksByTotalSales(booksArray);
                    break;
                case 6:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please choose again.");
            }
        } while (choice != 6);
    }

    // Prints the book list and creates an id number
    private static void printBookList(Books[] booksArray) {
        System.out.println("Book List:");
        for (int i = 0; i < booksArray.length; i++) {
            if (booksArray[i] != null) {
                System.out.println("ID: " + i + ", Title: " + booksArray[i].getTitle() +
                        ", Author: " + booksArray[i].getAuthor() +
                        ", Year: " + booksArray[i].getYear());
            }
        }
    }

    // search for book based on ID
    private static void searchBookById(Books[] booksArray, Scanner userInput) {
        System.out.print("Enter book ID to search: ");
        int id = userInput.nextInt();
        if (id >= 0 && id < booksArray.length && booksArray[id] != null) {
            Books book = booksArray[id];
            System.out.println("Found Book: " + book.getTitle() + ", Author: " + book.getAuthor() +
                    ", Year: " + book.getYear() + ", Total Sales: " + book.getTotal());
        } else {
            System.out.println("Book with ID " + id + " not found.");
        }
    }
    // Show sales in a given month,
    private static void showSalesForMonth(Books[] booksArray, Scanner userInput) {
        System.out.print("Enter month (1-6): ");
        int month;
        while (true) {
            try {
                month = userInput.nextInt();
                if (month < 1 || month > 6) {
                    System.out.println("Invalid month. Please enter a number between 1 and 6.");
                } else {
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                userInput.next(); // clear the invalid input
            }
        }
        for (int i = 0; i < booksArray.length; i++) {
            if (booksArray[i] != null) {
                int[] saleNumbers = booksArray[i].getSaleNumbers();
                if (saleNumbers.length < 6) {
                    int[] newSaleNumbers = new int[6];
                    System.arraycopy(saleNumbers, 0, newSaleNumbers, 0, saleNumbers.length);
                    // Initialize the remaining elements to 0
                    for (int j = saleNumbers.length; j < 6; j++) {
                        newSaleNumbers[j] = 0;
                    }
                    saleNumbers = newSaleNumbers;
                }
                System.out.println("Book: " + booksArray[i].getTitle());
                System.out.println("Sales for month " + month + ": " + saleNumbers[month - 1]);
            }
        }
    }

    private static void sortBooksByTotalSales(Books[] booksArray) {
        Books[] sortedBooks = Arrays.stream(booksArray)
                .sorted(Comparator.comparingInt(Books::getTotal).reversed())
                .toArray(Books[]::new);
        System.out.println("Books sorted by total sales in descending order.");
        printBookList(sortedBooks);
    }
}

