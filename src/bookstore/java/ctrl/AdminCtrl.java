
package bookstore.java.ctrl;

import bookstore.java.cmp.Access;
import bookstore.java.cmp.Bill;
import bookstore.java.cmp.Book;
import bookstore.java.cmp.Message;
import bookstore.java.cmp.User;
import bookstore.java.dialog.AddBookDialog;
import bookstore.java.dialog.AddUserDialog;
import bookstore.java.model.AdminModel;
import bookstore.java.view.AdminView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Optional;

import javafx.scene.control.ButtonType;

public class AdminCtrl {

    private final AdminModel model;
    private final AdminView view;

    // *************** Constructor/Initializer ************** //

    public AdminCtrl(AdminModel model, AdminView view) {
        this.model = model;
        this.view = view;

        // set controller to components
        view.setEmpListBoxAction(e -> empListBtnAction());
        view.setAddBookBtnAction(e -> addBookBtnAction());
        view.setIncrStkBtnAction(e -> incrStkBtnAction());
        view.setFullInfoBtnAction(e -> fullInfoBtnAction());
        view.setCheckoutBtnAction(e -> checkoutBtnAction());
        view.setAddEmpBtnAction(e -> addEmpBtnAction());
        view.setRemEmpBtnAction(e -> remEmpBtnAction());
        view.setEditEmpBtnAction(e -> editEmpBtnAction());
        view.setChkLibPerfBtnAction(e -> chkLibPerfBtnAction());
        view.setChkStrPerfBtnAction(e -> setChkStrPerfBtnAction());
        view.setLogoutBtnAction(e -> logoutBtnAction());
    }

    // ************************ END ************************* //

    // ************************ Fn ************************** //

    public void empListBtnAction() {

        int res = view.getEmpSelectedIndex();

        if (res >= 0) {
            User user = model.getEmployee(res);

            if (user != null && user.getAccessLevel() == Access.LIBRARIAN) {
                view.disableChkLibPerfBtn(false);
            } else {
                view.disableChkLibPerfBtn(true);
            }
        }

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

    public void checkoutBtnAction() {

        int res = view.getBookSelectedIndex();
        Book book = model.getBook(res);

        Optional<String> resText = Message.showInput("Quantity", "Enter quantity");

        resText.ifPresent(q -> {

            int quantity = 0;

            try {

                quantity = Integer.parseInt(q);

                if (quantity < 1) {
                    Message.showError("Please enter a valid quantity");
                    return;
                }

                int billNo = model.checkOutBook(book.getISBN() + "", quantity);

                if (billNo >= 0) {

                    // billing successful
                    Bill bill = model.getBill(billNo);

                    StringBuilder s = new StringBuilder();
                    s.append("Bill No: ").append(bill.getBillNo()).append("\n");
                    s.append("Book ISBN: ").append(bill.getISBN()).append("\n");
                    s.append("Book Quantity: ").append(bill.getQuantity()).append("\n");
                    s.append("Book Price: ").append(bill.getPrice()).append("\n\n");
                    s.append("Do you want to export this bill?").append("\n");

                    Optional<ButtonType> resConfirm = Message.showConfirm(s.toString(), "Bill Data");

                    resConfirm.ifPresent((ButtonType c) -> {
                        if (c.getText().equals("OK")) {
                            String billData = model.getBillDataForExport(billNo);
                            model.exportBillData(billNo, billData);
                        }
                    });

                    model.updateData();  // writes out changes in the file

                } else {
                    Message.showError("Billing was unsuccessful, Quantity exceeds stock");
                }

            } catch (NumberFormatException e) {
                Message.showError("Invalid input, Please enter a number");
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

    public void addEmpBtnAction() {

        new AddUserDialog().showAndGetResult().ifPresent(e -> {

            String[] data = new String[8];

            for (String s : e) {
                if (s != null && s.isEmpty()) {
                    Message.showError("A field is empty,\nPlease fill all fields");
                    return;
                }
            }

            try {
                data[2] = Long.parseLong(e.get(2)) + "";
            } catch (NumberFormatException f) {
                Message.showError("Please enter numeric value in phone no. field");
                return;
            }

            try {
                data[5] = Integer.parseInt(e.get(5)) + "";
            } catch (NumberFormatException f) {
                Message.showError("Please enter numeric value in salary field");
                return;
            }

            try {
                Date date = new SimpleDateFormat("dd-MM-yyyy").parse(e.get(1));
                data[1] = e.get(1);
            } catch (ParseException f) {
                Message.showError("Invalid date format\nDate format should be DD-MM-YYYY\nEx: 01/01/1983");
                return;
            }

            String access = e.get(6).toLowerCase();

            if (access.equals("admin") || access.equals("manager") || access.equals("librarian")) {
                data[6] = access;
            } else {
                Message.showError("Please enter a valid value in the access field");
                return;
            }

            data[0] = e.get(0);
            data[3] = e.get(3);
            data[4] = e.get(4);
            data[7] = "Active";

            User user = new User(data);

            if (model.addUser(user)) {
                model.updateData();
                view.updateEmployeesData();
                Message.showInfo("User added", "Information");
            } else {
                Message.showError("User with the given phNo already exist\nenter another one");
            }

        });

    }

    public void remEmpBtnAction() {

        int res = view.getEmpSelectedIndex();
        User user = model.getEmployee(res);

        Optional<ButtonType> resConfirm = Message.showConfirm(
                "Do you want to fire\n" +
                        "Employee: " + user.getName() + " from the position of: " + user.getAccessLevel().toString(),
                "Fire Employee?");

        resConfirm.ifPresent((ButtonType c) -> {
            if (c.getText().equals("OK")) {
                model.fireEmployee(res);
                model.updateData();
                view.updateEmployeesData();
            }
        });
    }

    public void editEmpBtnAction() {

        User user = model.getEmployee(view.getEmpSelectedIndex());

        Optional<ArrayList<String>> res = new AddUserDialog().showAndGetResult(
                user.getName(), user.getBirthday(), user.getPhoneNo(), user.getEmail(),
                user.getPassword(), user.getSalary() + "", user.getAccessLevel().toString()
        );

        res.ifPresent(e -> {

            String[] data = new String[8];

            for (String s : e) {
                if (s != null && s.isEmpty()) {
                    Message.showError("A field is empty,\nPlease fill all fields");
                    return;
                }
            }

            try {
                data[2] = Long.parseLong(e.get(2)) + "";
            } catch (NumberFormatException f) {
                Message.showError("Please enter numeric value in phone no. field");
                return;
            }

            try {
                data[5] = Integer.parseInt(e.get(5)) + "";
            } catch (NumberFormatException f) {
                Message.showError("Please enter numeric value in salary field");
                return;
            }

            try {
                Date date = new SimpleDateFormat("dd-MM-yyyy").parse(e.get(1));
                data[1] = e.get(1);
            } catch (ParseException f) {
                Message.showError("Invalid date format\nDate format should be DD-MM-YYYY\nEx: 01/01/1983");
                return;
            }

            String access = e.get(6).toLowerCase();

            if (access.equals("admin") || access.equals("manager") || access.equals("librarian")) {
                data[6] = access;
            } else {
                Message.showError("Please enter a valid value in the access field");
                return;
            }

            data[0] = e.get(0);
            data[3] = e.get(3);
            data[4] = e.get(4);
            data[7] = "Active";

            User newUserData = new User(data);

            if (model.editUser(newUserData)) {
                model.updateData();
                view.updateEmployeesData();
                Message.showInfo("Data edited", "Information");
            } else {
                Message.showError("User with the given phNo doesn't exist");
            }

        });
    }

    public void chkLibPerfBtnAction() {

        int res = view.getEmpSelectedIndex();
        User user = model.getEmployee(res);
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

    public void logoutBtnAction() {
        model.logout();
    }

    // ************************ END ************************* //

}
