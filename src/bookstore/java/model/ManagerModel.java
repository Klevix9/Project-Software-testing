
package bookstore.java.model;

import bookstore.java.GUI;
import bookstore.java.StoreLogic;
import bookstore.java.cmp.Bill;
import bookstore.java.cmp.Book;
import bookstore.java.cmp.User;

import java.util.ArrayList;

public class ManagerModel {

    private final StoreLogic logic;
    private final GUI gui;

    // *************** Constructor/Initializer ************** //

    public ManagerModel(StoreLogic logic, GUI gui) {
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

    public void updateData() {
        logic.saveBookData();
    }

    // ************************ END ************************* //

    // ******************* Getter/Setter ******************** //

    public User getCurrUser() {
        return logic.getLoggedInUser();
    }

    public ArrayList<User> getLibrarians() {
        return logic.getLibrarians();
    }

    public User getUser(int index) {
        return getLibrarians().get(index);
    }

    public ArrayList<Bill> getBills(User user) {
        return logic.getAllBillsFor(user);
    }

    public ArrayList<Bill> getBills() {
        return logic.getAllBills();
    }

    public Book getBook(int index) {
        return getBooks().get(index);
    }

    public ArrayList<Book> getBooks() {
        return logic.getAllBooks();
    }

    // *********************** END ************************* //

}
