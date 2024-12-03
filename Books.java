public class Books {
    private String title;
    private String author;
    private int year;
    private int[] saleNumbers;


    public Books(String title, String author, int year) {
        this.title = title;
        this.author = author;
        this.year = year;
        this.saleNumbers = new int[6];
    }


    public Books(String title, String author, int year, int[] saleNumbers) {
        this.title = title;
        this.author = author;
        this.year = year;
        this.saleNumbers = saleNumbers.clone();
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


    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setYear(int year) {
        this.year = year;
    }


    public void setSaleNumbers(int sales, int month) {
        if (saleNumbers.length < month) {
            int[] newSaleNumbers = new int[month];
            System.arraycopy(saleNumbers, 0, newSaleNumbers, 0, saleNumbers.length);
            saleNumbers = newSaleNumbers;
        }
        saleNumbers[month - 1] = sales;
    }

    public int getSaleNumbers(int month) {
        if (month >= 1 && month <= saleNumbers.length) {
            return saleNumbers[month - 1];
        }
        return 0;
    }

    public int getTotal() {
        int total = 0;
        for (int sales : saleNumbers) {
            total += sales;
        }
        return total;
    }
}
