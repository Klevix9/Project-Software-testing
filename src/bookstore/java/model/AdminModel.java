
package bookstore.java.model;

import bookstore.java.GUI;
import bookstore.java.StoreLogic;
import bookstore.java.cmp.Bill;
import bookstore.java.cmp.Book;
import bookstore.java.cmp.User;

import java.util.ArrayList;

public class AdminModel {

    private final StoreLogic logic;
    private final GUI gui;

    // *************** Constructor/Initializer ************** //

    public AdminModel(StoreLogic logic, GUI gui) {
        this.logic = logic;
        this.gui = gui;
    }

    // ************************ END ************************* //

    // ************************ Fn ************************** //

    public void logout() {
        logic.logOut();
        gui.setLayoutToLogin();
    }

    public boolean addBook(Book book) {
        if (logic.getBook(book.getISBN() + "") == null) {
            logic.addBook(book);
            return true;
        }
        return false;
    }

    public boolean addUser(User user) {
        if (!logic.isUser(user.getPhoneNo())) {
            logic.addUser(user);
            return true;
        }
        return false;
    }

    public boolean editUser(User user) {
        if (logic.isUser(user.getPhoneNo())) {
            logic.editUser(user);
            return true;
        }
        return false;
    }

    public boolean addStock(int index, int stock) {

        if (stock > 0) {
            Book book = getBook(index);
            String isbn = book.getISBN() + "";
            int curr_stock = book.getStock();
            logic.setStocksOf(isbn, (curr_stock + stock));
            return true;
        }

        return false;
    }

    public int checkOutBook(String isbn, int quantity) {
        return logic.addBill(getCurrUser().getPhoneNo(), isbn, quantity);
    }

    public void fireEmployee(int index) {
        String phNo = getEmployee(index).getPhoneNo();
        logic.fireUser(phNo);
    }

    public void updateData() {
        logic.saveBookData();
        logic.saveBillsData();
        logic.saveUserData();
    }

    public void exportBillData(int billNo, String data) {
        logic.exportBill(billNo, data);
    }

    // ************************ END ************************* //

    // ******************* Getter/Setter ******************** //

    public User getCurrUser() {
        return logic.getLoggedInUser();
    }

    public ArrayList<User> getEmployees() {
        return logic.getEmployees();
    }

    public User getEmployee(int index) {
        return getEmployees().get(index);
    }

    public Book getBook(int index) {
        return getBooks().get(index);
    }

    public ArrayList<Book> getBooks() {
        return logic.getAllBooks();
    }

    public ArrayList<Bill> getBills() {
        return logic.getAllBills();
    }

    public ArrayList<Bill> getBills(User user) {
        return logic.getAllBillsFor(user);
    }

    public Bill getBill(int billNo) {
        return logic.getBill(billNo);
    }

    public String getBillDataForExport(int billNo) {
        return logic.getBillDataInPrintableFormat(billNo);
    }

    // *********************** END ************************* //

}
