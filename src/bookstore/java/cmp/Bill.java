
package bookstore.java.cmp;

public class Bill {

    private int billNo;
    private String userPhNo;  // phone no of the user who billed this
    private String isbn;
    private String billDate;
    private int quantity;
    private int price;
    private int profit;

    // ************ Constructor/Initializer ************ //

    public Bill(String[] data) {

        this.billNo = Integer.parseInt(data[0]);
        this.userPhNo = data[1];
        this.isbn = data[2];
        this.billDate = data[3];
        this.quantity = Integer.parseInt(data[4]);
        this.price = Integer.parseInt(data[5]);
        this.profit = Integer.parseInt(data[6]);

    }

    // *********************** END *********************** //

    @Override
    public String toString() {
        return "ISBN: " + isbn + ", Date: " + billDate + ", Quantity: " + quantity + ", Price: " + price;
    }

    /**
     * Returns all data for saving it in the CSV file
     *
     * @return data
     */
    public String getAllData() {
        String data = billNo + "," + userPhNo + "," + isbn + "," + billDate + "," + quantity + "," + price + "," + profit;
        return data;
    }

    // ****************** Getter/Setter ****************** //

    public int getBillNo() {
        return billNo;
    }

    public void setBillNo(int billNo) {
        this.billNo = billNo;
    }

    public String getLibrarianPhNo() {
        return userPhNo;
    }

    public void setName(String phNo) {
        this.userPhNo = phNo;
    }

    public String getISBN() {
        return isbn;
    }

    public void setISBN(String isbn) {
        this.isbn = isbn;
    }

    public String getBillDate() {
        return billDate;
    }

    public void setBillDate(String date) {
        this.billDate = date;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getProfit() {
        return profit;
    }

    public void setProfit(int profit) {
        this.profit = profit;
    }

    // *********************** END *********************** //

}
