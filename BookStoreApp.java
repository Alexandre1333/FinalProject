import java.io.File;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class BookStoreApp {
    public static void main(String[] args) {
        Books[] booksArray = new Books[7];

        Scanner userInput = new Scanner(System.in);
        int choice;

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
                case 1: try {
                    Scanner fileScanner = new Scanner(new File("data.txt"));
                    for (int i = 0; i < 4 && fileScanner.hasNextLine(); i++) {
                        String title = fileScanner.nextLine().trim();
                        String author = fileScanner.nextLine().trim();
                        int year = Integer.parseInt(fileScanner.nextLine().trim());
                        String salesLine = fileScanner.nextLine().trim();

                        if (!salesLine.equals("None")) {
                            String[] salesStr = salesLine.split(" ");
                            int[] sales = Arrays.stream(salesStr).mapToInt(Integer::parseInt).toArray();
                            booksArray[i] = new Books(title, author, year, sales);
                        } else {
                            booksArray[i] = new Books(title, author, year);
                        }
                    }
                    for (int i = 4; i < 7 && fileScanner.hasNextLine(); i++) {
                        String title = fileScanner.nextLine().trim();
                        String author = fileScanner.nextLine().trim();
                        int year = Integer.parseInt(fileScanner.nextLine().trim());
                        String salesLine = fileScanner.nextLine().trim();

                        if (!salesLine.equals("None")) {
                            String[] salesStr = salesLine.split(" ");
                            int[] sales = Arrays.stream(salesStr).mapToInt(Integer::parseInt).toArray();
                            booksArray[i] = new Books(title, author, year, sales);
                        } else {
                            booksArray[i] = new Books(title, author, year);
                        }
                    }
                    fileScanner.close();

                } catch (Exception e) {
                    System.out.println("Error reading file: " + e.getMessage());
                }
                    break;
                case 2:
                    printBookList(booksArray);
                    break;
                case 3:
                    searchBookById(booksArray, userInput);
                    break;
                case 4:
                    showSalesForMonth(booksArray, userInput);
                    break;
                case 5:
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
        private static void printBookList (Books[]booksArray){
            System.out.println("Book List:");
            for (int i = 0; i < booksArray.length; i++) {
                if (booksArray[i] != null) {
                    System.out.println("ID: " + i + ", Title: " + booksArray[i].getTitle() +
                            ", Author: " + booksArray[i].getAuthor() +
                            ", Year: " + booksArray[i].getYear());
                }
            }
        }

        private static void searchBookById (Books[]booksArray, Scanner userInput){
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

        private static void showSalesForMonth (Books[]booksArray, Scanner userInput){
            System.out.print("Enter a month number (1-6): ");
            int month = userInput.nextInt();
            if (month < 1 || month > 6) {
                System.out.println("Invalid month number. Please enter a number between 1 and 6.");
                return;
            }
            System.out.println("Sales in month " + month + ":");
            for (Books book : booksArray) {
                if (book != null) {
                    int sales = book.getSaleNumbers(month - 1);
                    System.out.println(book.getTitle() + ": " + sales);
                }
            }
        }

        private static void sortBooksByTotalSales (Books[]booksArray){
            Arrays.sort(booksArray, Comparator.comparingInt(Books::getTotal).reversed());
            System.out.println("Books sorted by total sales in descending order.");
            printBookList(booksArray);
        }
    }

