
package bookstore.java;

import bookstore.java.cmp.*;
import bookstore.java.fn.CSV_fn;
import bookstore.java.fn.File_rw;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class StoreLogic {

    private Map<Integer, Bill> bills;
    private Map<String, Book> books;
    private Map<String, User> users;

    private User loggedInWith;
    private int currBillNo;

    // ************ Constructor/Initializer ************ //

    public StoreLogic() {

        // load user data
        ArrayList<String[]> userData = CSV_fn.parse(File_rw.readTextFromFile("app_data/user_data.txt"));
        users = new LinkedHashMap<>();

        userData.forEach((x) -> {
            users.put(x[2], new User(x));  // Phone no as key
        });

        // load books data
        ArrayList<String[]> bookData = CSV_fn.parse(File_rw.readTextFromFile("app_data/books_data.txt"));
        books = new LinkedHashMap<>();

        bookData.forEach((x) -> {
            books.put(x[0], new Book(x));  // isbn as key
        });

        // load bills data
        ArrayList<String[]> billsData = CSV_fn.parse(File_rw.readTextFromFile("app_data/bills_data.txt"));
        bills = new LinkedHashMap();
        currBillNo = 1;

        billsData.forEach((x) -> {
            Bill b = new Bill(x);
            bills.put(Integer.parseInt(x[0]), b);  // bill no as key
            if (currBillNo <= b.getBillNo())
                currBillNo = b.getBillNo() + 1;
        });

        loggedInWith = null;

    }

    // ********************** END ********************** //

    // ****************** Getter/Setter ****************** //

    /**
     * Adds a book to the booklist
     *
     * @param book Book data
     */
    public void addBook(Book book) {
        books.put(book.getISBN() + "", book);
    }

    /**
     * Gets all the books in a list
     *
     * @return booklist
     */
    public ArrayList<Book> getAllBooks() {
        ArrayList<Book> data = new ArrayList<>();
        books.values().forEach((b) -> {
            data.add(b);
        });
        return data;
    }

    /**
     * Gets a book from the book data with the ISBN number
     *
     * @param isbn ISBN number of the book
     * @return book
     */
    public Book getBook(String isbn) {
        return books.get(isbn);
    }

    /**
     * Adds a user to the userlist
     *
     * @param user Book data
     */
    public void addUser(User user) {
        users.put(user.getPhoneNo(), user);
    }

    /**
     * Gets all the users in a list
     *
     * @return user list
     */
    public ArrayList<User> getAllUsers() {
        ArrayList<User> data = new ArrayList<>();
        users.values().forEach((b) -> {
            data.add(b);
        });
        return data;
    }

    /**
     * Gets all the currently active users in a list
     *
     * @return user list
     */
    public ArrayList<User> getActiveUsers() {
        ArrayList<User> data = new ArrayList<>();
        users.values().forEach((b) -> {
            if (b.getStatus().equals("Active"))
                data.add(b);
        });
        return data;
    }

    /**
     * Get employees data from the user data
     * Besides administrator privilege user all others are considered employee
     *
     * @return Employees data
     */
    public ArrayList<User> getEmployees() {
        ArrayList<User> data = new ArrayList<>();
        getActiveUsers().forEach((b) -> {
            if (b.getAccessLevel() != Access.ADMINISTRATOR)
                data.add(b);
        });
        return data;
    }

    /**
     * Get librarians data from the user data
     *
     * @return Librarians data
     */
    public ArrayList<User> getLibrarians() {
        ArrayList<User> data = new ArrayList<>();
        getActiveUsers().forEach((b) -> {
            if (b.getAccessLevel() == Access.LIBRARIAN)
                data.add(b);
        });
        return data;
    }

    /**
     * Gets an user from the user data with the email or password
     * returns null if the user with given emailOrPhone and password doesn't exists
     *
     * @param emailOrPh Email or PhoneNo of the user
     * @param password  Password of the user
     * @return user
     */
    public User getUser(String emailOrPh, String password) {
        for (User user : getActiveUsers()) {
            if (user.getEmail().equals(emailOrPh) || user.getPhoneNo().equals(emailOrPh)) {
                if (user.getPassword().equals(password))
                    return user;
            }
        }
        return null;
    }

    /**
     * Checks if user exists in the data and is active currently
     *
     * @param phNo Phone number of the user
     * @return true if found otherwise false
     */
    public boolean isUser(String phNo) {
        return users.get(phNo) != null && users.get(phNo).getStatus().equals("Active");
    }

    /**
     * Fires given user from user data
     *
     * @param phNo Phone number of the user
     */
    public void fireUser(String phNo) {
        users.get(phNo).setStatus("Fired");
    }

    /**
     * Replaces user with new data
     *
     * @param user New user data
     */
    public void editUser(User user) {
        users.replace(user.getPhoneNo(), user);
    }

    /**
     * gets the stocks of the book left in the library
     *
     * @param isbn ISBN of the book
     * @return stocks
     */
    public int getStocksOf(String isbn) {
        return books.get(isbn).getStock();
    }

    /**
     * sets the stocks of the book in the library
     *
     * @param isbn     ISBN of the book
     * @param quantity new quantity
     */
    public void setStocksOf(String isbn, int quantity) {
        books.get(isbn).setStock(quantity);
    }

    /**
     * gets the selling price of the book
     *
     * @param isbn ISBN of the book
     * @return selling price
     */
    public int getSPOf(String isbn) {
        return books.get(isbn).getSP();
    }

    /**
     * Adds a new bill to the bills list and decreases stocks relative
     * to quantity of books being removed from the library
     * <p>
     * Fails if the given quantity is greater than the stocks of the book
     *
     * @param libPhNo  Phone number of the librarian
     * @param isbn     ISBN of the book
     * @param quantity Quantity of the book to be sold
     * @return returns the billNo if billing was successful, otherwise -1
     */
    public int addBill(String libPhNo, String isbn, int quantity) {

        int bookStocks = getStocksOf(isbn);

        if (quantity > 0 && bookStocks >= quantity) {

            String[] data = new String[7];

            data[0] = currBillNo + "";
            data[1] = libPhNo;
            data[2] = isbn;

            LocalDate ld = LocalDate.now();
            data[3] = ld.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));

            data[4] = quantity + "";
            setStocksOf(isbn, bookStocks - quantity);

            Book book = getBook(isbn);

            int sp = (book.getSP() * quantity);
            int cp = (book.getCP() * quantity);

            data[5] = sp + "";

            data[6] = (sp - cp) + "";

            Bill b = new Bill(data);
            bills.put(currBillNo, b);

            currBillNo++;

            return b.getBillNo();
        } else {
            return -1;
        }
    }

    /**
     * Gets a bill from the bill data with the billNo
     *
     * @param billNo Bill number of the bill
     * @return bill
     */
    public Bill getBill(int billNo) {
        return bills.get(billNo);
    }

    /**
     * Gets all the bills in a list checked out by the given user
     *
     * @param user
     * @return bill list
     */
    public ArrayList<Bill> getAllBillsFor(User user) {
        ArrayList<Bill> data = new ArrayList<>();
        bills.values().forEach((b) -> {
            if (b.getLibrarianPhNo().equals(user.getPhoneNo()))
                data.add(b);
        });
        return data;
    }

    /**
     * Gets all the bills in a list
     *
     * @return bill list
     */
    public ArrayList<Bill> getAllBills() {
        ArrayList<Bill> data = new ArrayList<>();
        bills.values().forEach((b) -> {
            data.add(b);
        });
        return data;
    }

    /**
     * Gets bill data in printable format
     *
     * @param billNo Bill no of the bill
     * @return bill data in printable format
     */
    public String getBillDataInPrintableFormat(int billNo) {

        Bill b = getBill(billNo);

        if (b != null) {
            Book bo = getBook(b.getISBN());

            String data = "[Book]\n" + bo.toString() + "\n\n[Bill]\n" + b.toString();
            data += "\n\nTotal Price: " + b.getPrice();
            return data;
        } else {
            return null;
        }
    }

    /**
     * Checks if store is already logged in with an account
     *
     * @return true if logged in otherwise false
     */
    public boolean isLoggedIn() {
        return this.loggedInWith != null;
    }

    /**
     * Returns data of the user who is logged in currently
     *
     * @return logged in user data
     */
    public User getLoggedInUser() {
        return loggedInWith;
    }

    /**
     * Log in as the given user
     *
     * @param user User
     */
    public void loginWith(User user) {
        this.loggedInWith = user;
    }

    /**
     * Log out from the current session
     */
    public void logOut() {
        this.loggedInWith = null;
    }

    // ********************** END ********************** //

    public void printUsers() {

        System.out.println("");
        System.out.println(" **************** Users ***************** ");
        System.out.println("");

        users.values().forEach((u) -> {
            System.out.println(u.toString());
        });

    }

    public void printBooks() {

        System.out.println("");
        System.out.println(" **************** Books ***************** ");
        System.out.println("");

        books.values().forEach((b) -> {
            System.out.println(b.toString());
        });

    }

    public void printBills() {

        System.out.println("");
        System.out.println(" **************** Bills ***************** ");
        System.out.println("");

        bills.values().forEach((b) -> {
            System.out.println(b.toString());
        });

    }

    /**
     * Saves the current bill data to external file : bills_data.txt
     */
    public void saveBillsData() {

        String bD = "";
        bD = bills.values().stream().map((b) -> b.getAllData() + "\n").reduce(bD, String::concat);

        if (bD.isEmpty())
            File_rw.writeTextToFile("", "app_data/bills_data.txt");
        else
            File_rw.writeTextToFile(bD.substring(0, bD.length() - 1), "app_data/bills_data.txt");

    }

    /**
     * Saves the current user data to external file : user_data.txt
     */
    public void saveUserData() {

        String uD = "";
        uD = users.values().stream().map((u) -> u.getAllData() + "\n").reduce(uD, String::concat);

        if (uD.isEmpty())
            File_rw.writeTextToFile("", "app_data/user_data.txt");
        else
            File_rw.writeTextToFile(uD.substring(0, uD.length() - 1), "app_data/user_data.txt");

    }

    /**
     * Saves the current book data to external file : book_data.txt
     */
    public void saveBookData() {

        String bookD = "";
        bookD = books.values().stream().map((bo) -> bo.getAllData() + "\n").reduce(bookD, String::concat);

        if (bookD.isEmpty())
            File_rw.writeTextToFile("", "app_data/books_data.txt");
        else
            File_rw.writeTextToFile(bookD.substring(0, bookD.length() - 1), "app_data/books_data.txt");

    }

    /**
     * Writes the bill to file (<BillNo>.txt)
     *
     * @param billNo   Bill number of the bill
     * @param billData Bill data for export
     */
    public void exportBill(int billNo, String billData) {
        File_rw.writeTextToFile(billData, "app_data" + billNo + ".txt");
    }

}
