public class books {
    private String title;
    private String author;
    private int year;
    private int[] saleNumbers;

    //here comes the Constructor lil buddy

    public books(String title, String author, int year, int[] saleNumbers) {
        this.title = title;
        this.author = author;
        this.year = year;
        this.saleNumbers = saleNumbers //figure this part out
    }
    //the other one :)

    public books(String title, String author, int year) {
        this.title = title;
        this.author = author;
        this.year = year;
        this.saleNumbers = new int[6];

    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getYear() {
        return year;
    }

    public int[] getSaleNumbers() {
        return saleNumbers;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setSaleNumbers(int[] saleNumbers) throws IllegalAccessException {
        if (saleNumbers.length == 6) {
            this.saleNumbers = saleNumbers;
        } else {
            throw new IllegalAccessException("sales array must have 6 elements.");
        }
    }

    public void setSaleNumbers(int booksSold, int month) {
        if (month >= 1 && month <= 6) {
            return this.saleNumbers[month - 1];
        } else {
            throw new IllegalArgumentException("Month must be between 1 and 6.");
        }
    }

    public int getTotalSales() {
        int total = 0;
        for (int sales : saleNumbers) {
            total += sales;
        }
        return total;
    }


    public static void main(String[] args) {
        int[] sales = {100, 200, 150, 120, 180, 220};
    books book = new books("Catching Fire", "Collins", 1949, sales );

        System.out.println("Total Sales" + book.getTotalSales());
        System.out.println("Sales in May: " + book.getSaleNumbers(5));
        book.setSaleNumbers(250, 6);
        System.out.println("updated total sales: " + book.getTotalSales());





    }

}
