
package bookstore.java.ctrl;

import bookstore.java.cmp.Bill;
import bookstore.java.cmp.Book;
import bookstore.java.cmp.Message;
import bookstore.java.cmp.User;
import bookstore.java.dialog.AddBookDialog;
import bookstore.java.model.ManagerModel;
import bookstore.java.view.ManagerView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

public class ManagerCtrl {

    private final ManagerModel model;
    private final ManagerView view;

    // *************** Constructor/Initializer ************** //

    public ManagerCtrl(ManagerModel model, ManagerView view) {
        this.model = model;
        this.view = view;

        // set controller to components
        view.setLogoutBtnAction(e -> logoutBtnAction());
        view.setAddBookBtnAction(e -> addBookBtnAction());
        view.setIncrStkBtnAction(e -> incrStkBtnAction());
        view.setFullInfoBtnAction(e -> fullInfoBtnAction());
        view.setChkLibPerfBtnAction(e -> chkLibPerfBtnAction());
        view.setChkStrPerfBtnAction(e -> setChkStrPerfBtnAction());
    }

    // ************************ END ************************* //

    // ************************ Fn ************************** //

    public void logoutBtnAction() {
        model.logout();
    }

    public void addBookBtnAction() {

        new AddBookDialog().showAndGetResult().ifPresent(e -> {

            String[] data = new String[9];

            for (String s : e) {
                if (s != null && s.isEmpty()) {
                    Message.showError("A field is empty,\nPlease fill all fields");
                    return;
                }
            }

            try {
                data[0] = Long.parseLong(e.get(0)) + "";
            } catch (NumberFormatException f) {
                Message.showError("Please enter numeric value in isbn field");
                return;
            }

            try {
                data[5] = Integer.parseInt(e.get(5)) + "";
            } catch (NumberFormatException f) {
                Message.showError("Please enter numeric value in cp field");
                return;
            }

            try {
                data[6] = Integer.parseInt(e.get(6)) + "";
            } catch (NumberFormatException f) {
                Message.showError("Please enter numeric value in sp field");
                return;
            }

            try {
                data[8] = Integer.parseInt(e.get(8)) + "";
            } catch (NumberFormatException f) {
                Message.showError("Please enter numeric value in stock field");
                return;
            }

            try {
                Date date = new SimpleDateFormat("dd-MM-yyyy").parse(e.get(4));
                data[4] = e.get(4);
            } catch (ParseException f) {
                Message.showError("Invalid date format\nDate format should be DD-MM-YYYY\nEx: 01/01/1983");
                return;
            }

            data[1] = e.get(1);
            data[2] = e.get(2);
            data[3] = e.get(3);
            data[7] = e.get(7);

            Book book = new Book(data);

            if (model.addBook(book)) {
                model.updateData();
                view.updateBooksData();
                Message.showInfo("Book added", "Information");
            } else {
                Message.showError("Book with the given isbn already exist\nenter another one");
            }

        });

    }

    public void incrStkBtnAction() {

        int res = view.getBookSelectedIndex();

        Optional<String> resText = Message.showInput("Quantity", "Enter quantity");

        resText.ifPresent(e -> {

            int q = 0;
            try {
                q = Integer.parseInt(e);
            } catch (NumberFormatException f) {
                Message.showError("Please enter a numeric value");
                return;
            }

            if (model.addStock(res, q)) {
                model.updateData();
                Message.showInfo("Stock added", "Information");
            } else {
                Message.showError("Please enter a valid quantity");
            }
        });

    }

    public void fullInfoBtnAction() {

        int res = view.getBookSelectedIndex();
        Book book = model.getBook(res);

        if (book != null) {

            StringBuilder sb = new StringBuilder();

            sb.append("Book name: ").append(book.getName()).append("\n");
            sb.append("ISBN: ").append(book.getISBN()).append("\n");
            sb.append("Category: ").append(book.getCategory()).append("\n");
            sb.append("Author: ").append(book.getAuthor()).append("\n");
            sb.append("Publisher: ").append(book.getPublisher()).append("\n");
            sb.append("Publishing Date: ").append(book.getPDate()).append("\n");
            sb.append("Selling Price: ").append(book.getSP()).append("\n");
            sb.append("Cost Price: ").append(book.getCP()).append("\n");
            sb.append("Stock: ").append(book.getStock()).append("\n");

            Message.showInfo(sb.toString(), "Book Data");
        }
    }

    public void chkLibPerfBtnAction() {

        int res = view.getLibSelectedIndex();
        User user = model.getUser(res);
        ArrayList<Bill> bills = model.getBills(user);

        int totalBills = bills.size();
        int totalBookSold = 0;
        int earnings = 0;

        for (Bill bill : bills) {
            totalBookSold += bill.getQuantity();
            earnings += bill.getProfit();
        }
        ;

        StringBuilder s = new StringBuilder();
        s.append("Name: ").append(user.getName()).append("\n");
        s.append("Total Bills: ").append(totalBills).append("\n");
        s.append("Total Books Sold: ").append(totalBookSold).append("\n");
        s.append("Total Earnings: ").append(earnings).append("\n");

        Message.showInfo(s.toString(), "Work Performance");

    }

    public void setChkStrPerfBtnAction() {

        ArrayList<Bill> bills = model.getBills();
        ArrayList<Book> books = model.getBooks();

        int totalBills = bills.size();
        int currTotalBooks = 0;
        int investment = 0;
        int booksSold = 0;
        int storeProfit = 0;

        for (Book b : books) {
            currTotalBooks += b.getStock();
            investment += b.getStock() * b.getCP();
        }

        for (Bill bi : bills) {
            booksSold += bi.getQuantity();
            storeProfit += bi.getProfit();
            investment += bi.getPrice() - bi.getProfit();
        }

        StringBuilder s = new StringBuilder();
        s.append("Books in stock: ").append(currTotalBooks).append("\n");
        s.append("Books Sold: ").append(booksSold).append("\n");
        s.append("Store Investment: ").append(investment).append("\n");
        s.append("Total Bills: ").append(totalBills).append("\n");
        s.append("Profit on Sale: ").append(storeProfit).append("\n");

        Message.showInfo(s.toString(), "Store Performance");
    }

    // ************************ END ************************* //

}
