
package bookstore.java.view;

import bookstore.java.cmp.Book;
import bookstore.java.model.LibrarianModel;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class LibrarianView extends Pane {

    private ComboBox<String> bookListBox;
    private TextField isbnField, stockField, cpriceField, spriceField;
    private Button fullInfoBtn, checkoutBtn, logoutBtn;

    private final LibrarianModel model;

    // *************** Constructor/Initializer ************** //

    public LibrarianView(LibrarianModel model) {
        this.model = model;
        init();
    }

    private void init() {

        Label appLabel = new Label("Book Store");
        appLabel.getStyleClass().add("app_label");
        appLabel.setLayoutX(150);
        appLabel.setLayoutY(20);

        Label mode = new Label("Librarian");
        mode.getStyleClass().add("heading");
        mode.setLayoutX(350);
        mode.setLayoutY(18);

        Label bookLabel = new Label("Books:");
        bookLabel.getStyleClass().add("heading");
        bookLabel.setLayoutX(125);
        bookLabel.setLayoutY(100);

        this.bookListBox = new ComboBox<>();
        this.bookListBox.getStyleClass().add("field");
        this.bookListBox.setLayoutX(125);
        this.bookListBox.setLayoutY(130);

        Label isbnLabel = new Label("ISBN:");
        isbnLabel.getStyleClass().add("heading");
        isbnLabel.setLayoutX(130);
        isbnLabel.setLayoutY(190);

        this.isbnField = new TextField();
        this.isbnField.setEditable(false);
        this.isbnField.setPromptText("ISBN of the selected book");
        this.isbnField.getStyleClass().add("value_field");
        this.isbnField.setLayoutX(180);
        this.isbnField.setLayoutY(185);

        Label stockLabel = new Label("Stock:");
        stockLabel.getStyleClass().add("heading");
        stockLabel.setLayoutX(122);
        stockLabel.setLayoutY(235);

        this.stockField = new TextField();
        this.stockField.setEditable(false);
        this.stockField.setPromptText("Stock of the book");
        this.stockField.getStyleClass().add("value_field");
        this.stockField.setLayoutX(180);
        this.stockField.setLayoutY(230);

        Label cpriceLabel = new Label("Cost Price:");
        cpriceLabel.getStyleClass().add("heading");
        cpriceLabel.setLayoutX(83);
        cpriceLabel.setLayoutY(280);

        this.cpriceField = new TextField();
        this.cpriceField.setEditable(false);
        this.cpriceField.setPromptText("Cost price of the book");
        this.cpriceField.getStyleClass().add("value_field");
        this.cpriceField.setLayoutX(180);
        this.cpriceField.setLayoutY(275);

        Label spriceLabel = new Label("Selling Price:");
        spriceLabel.getStyleClass().add("heading");
        spriceLabel.setLayoutX(60);
        spriceLabel.setLayoutY(325);

        this.spriceField = new TextField();
        this.spriceField.setEditable(false);
        this.spriceField.setPromptText("Selling price of the book");
        this.spriceField.getStyleClass().add("value_field");
        this.spriceField.setLayoutX(180);
        this.spriceField.setLayoutY(320);

        this.fullInfoBtn = new Button("Full Info");
        this.fullInfoBtn.getStyleClass().add("btn");
        this.fullInfoBtn.setLayoutX(90);
        this.fullInfoBtn.setLayoutY(380);

        this.checkoutBtn = new Button("Check out");
        this.checkoutBtn.getStyleClass().add("btn");
        this.checkoutBtn.setLayoutX(200);
        this.checkoutBtn.setLayoutY(380);

        this.logoutBtn = new Button("Log out");
        this.logoutBtn.getStyleClass().add("btn");
        this.logoutBtn.setLayoutX(310);
        this.logoutBtn.setLayoutY(380);

        // add book data and update data fields
        updateBooksData();

        this.getChildren().addAll(
                mode, appLabel, bookLabel, bookListBox, isbnLabel, isbnField,
                stockLabel, stockField, cpriceLabel, cpriceField, spriceLabel,
                spriceField, fullInfoBtn, checkoutBtn, logoutBtn
        );

    }

    // ************************ END ************************* //

    // ************************ Fn ************************** //

    public void showLayout(BorderPane mainLayout) {
        updateBooksData();
        mainLayout.setCenter(this);
    }

    public void updateBooksData() {

        bookListBox.getItems().remove(0, bookListBox.getItems().size());

        model.getBooks().forEach(e -> {
            bookListBox.getItems().add(e.getName());
        });

        bookListBox.getSelectionModel().select(0);
        updateDataFields(model.getBook(0));

    }

    public void updateDataFields(Book book) {
        setISBN(book.getISBN() + "");
        setStock(book.getStock() + "");
        setCP(book.getCP() + "");
        setSP(book.getSP() + "");
    }

    // ************************ END ************************* //

    // ******************* Getter/Setter ******************** //

    public void setISBN(String isbn) {
        isbnField.setText(isbn);
    }

    public String getISBN() {
        return isbnField.getText();
    }

    public void setStock(String stock) {
        stockField.setText(stock);
    }

    public void setCP(String value) {
        cpriceField.setText(value);
    }

    public void setSP(String value) {
        spriceField.setText(value);
    }

    public int getSelectedIndex() {
        return bookListBox.getSelectionModel().getSelectedIndex();
    }

    public void setBookListBoxAction(EventHandler<ActionEvent> value) {
        bookListBox.setOnAction(value);
    }

    public void setLogoutBtnAction(EventHandler<ActionEvent> value) {
        logoutBtn.setOnAction(value);
    }

    public void setCheckoutBtnAction(EventHandler<ActionEvent> value) {
        checkoutBtn.setOnAction(value);
    }

    public void setFullInfoBtnAction(EventHandler<ActionEvent> value) {
        fullInfoBtn.setOnAction(value);
    }

    // *********************** END ************************** //

}
