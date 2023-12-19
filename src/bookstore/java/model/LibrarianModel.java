
package bookstore.java.model;

import bookstore.java.GUI;
import bookstore.java.StoreLogic;
import bookstore.java.cmp.Bill;
import bookstore.java.cmp.Book;
import bookstore.java.cmp.User;

import java.util.ArrayList;

public class LibrarianModel {

    private final StoreLogic logic;
    private final GUI gui;

    // *************** Constructor/Initializer ************** //

    public LibrarianModel(StoreLogic logic, GUI gui) {
        this.logic = logic;
        this.gui = gui;
    }

    // ************************ END ************************* //

    // ************************ Fn ************************** //

    public void logout() {
        logic.logOut();
        gui.setLayoutToLogin();
    }

    public int checkOutBook(String isbn, int quantity) {
        return logic.addBill(getCurrUser().getPhoneNo(), isbn, quantity);
    }

    public void updateData() {
        logic.saveBillsData();
        logic.saveBookData();
    }

    // ************************ END ************************* //

    // ******************* Getter/Setter ******************** //

    public User getCurrUser() {
        return logic.getLoggedInUser();
    }

    public Book getBook(int index) {
        return getBooks().get(index);
    }

    public ArrayList<Book> getBooks() {
        return logic.getAllBooks();
    }

    public Bill getBill(int billNo) {
        return logic.getBill(billNo);
    }

    public String getBillDataForExport(int billNo) {
        return logic.getBillDataInPrintableFormat(billNo);
    }

    public void exportBillData(int billNo, String data) {
        logic.exportBill(billNo, data);
    }

    // *********************** END ************************* //


}
