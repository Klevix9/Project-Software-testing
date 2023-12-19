
package bookstore.java.cmp;

public class Book {

    private long isbn;
    private String name;
    private String category;
    private String publisher;
    private String pDate; // publishing date
    private int cp;  // cost price
    private int sp;  // selling price
    private String author;
    private int stock;

    // ************ Constructor/Initializer ************ //

    public Book(String[] data) {
        this.isbn = Long.parseLong(data[0]);
        this.name = data[1];
        this.category = data[2];
        this.publisher = data[3];
        this.pDate = data[4];
        this.cp = Integer.parseInt(data[5]);
        this.sp = Integer.parseInt(data[6]);
        this.author = data[7];
        this.stock = Integer.parseInt(data[8]);
    }

    // *********************** END *********************** //

    @Override
    public String toString() {
        return "ISBN: " + isbn + ", Book Name: " + name + ", Author: " + author + ", Stocks: " + stock;
    }

    /**
     * Returns all data for saving it in the CSV file
     *
     * @return data
     */
    public String getAllData() {
        String data = isbn + "," + name + "," + category + "," + publisher + "," + pDate + ",";
        data += cp + "," + sp + "," + author + "," + stock;
        return data;
    }

    // ****************** Getter/Setter ****************** //

    public Long getISBN() {
        return isbn;
    }

    public void setISBN(Long isbn) {
        this.isbn = isbn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getPDate() {
        return pDate;
    }

    public void setPDate(String pDate) {
        this.pDate = pDate;
    }

    public int getCP() {
        return cp;
    }

    public void setCP(int cp) {
        this.cp = cp;
    }

    public int getSP() {
        return sp;
    }

    public void setSP(int sp) {
        this.sp = sp;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    // *********************** END *********************** //

}
