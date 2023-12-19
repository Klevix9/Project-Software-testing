
package bookstore.java.ctrl;

import bookstore.java.cmp.Bill;
import bookstore.java.cmp.Book;
import bookstore.java.cmp.Message;
import bookstore.java.model.LibrarianModel;
import bookstore.java.view.LibrarianView;

import java.util.Optional;

import javafx.scene.control.ButtonType;

public class LibrarianCtrl {

    private final LibrarianModel model;
    private final LibrarianView view;

    // *************** Constructor/Initializer ************** //

    public LibrarianCtrl(LibrarianModel model, LibrarianView view) {

        this.model = model;
        this.view = view;

        // set actions
        view.setBookListBoxAction(e -> bookListBoxAction());
        view.setFullInfoBtnAction(e -> fullInfoBtnAction());
        view.setCheckoutBtnAction(e -> checkoutBtnAction());
        view.setLogoutBtnAction(e -> logoutBtnAction());
    }

    // ************************ END ************************* //

    // ************************ Fn ************************** //

    public void bookListBoxAction() {

        int res = view.getSelectedIndex();
        Book book = model.getBook(res);

        if (book != null) {
            view.updateDataFields(book);
        }
    }

    public void fullInfoBtnAction() {

        int res = view.getSelectedIndex();
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

    public void checkoutBtnAction() {

        int res = view.getSelectedIndex();
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
                    view.updateDataFields(book);

                } else {
                    Message.showError("Billing was unsuccessful, Quantity exceeds stock");
                }

            } catch (NumberFormatException e) {
                Message.showError("Invalid input, Please enter a number");
            }

        });

    }

    public void logoutBtnAction() {
        model.logout();
    }

    // ************************ END ************************* //

}