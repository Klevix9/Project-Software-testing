
package bookstore.java.view;

import bookstore.java.model.AdminModel;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class AdminView extends Pane {

    private ComboBox<String> bookListBox, empListBox;
    private Button addBookBtn, incrStkBtn, checkoutBtn, fullInfoBtn,
            addEmpBtn, remEmpBtn, editEmpBtn, chkLibPerfBtn,
            chkStrPerfBtn, logoutBtn;

    private final AdminModel model;

    // *************** Constructor/Initializer ************** //

    public AdminView(AdminModel model) {
        this.model = model;
        init();
    }

    private void init() {

        Label appLabel = new Label("Book Store");
        appLabel.getStyleClass().add("app_label");
        appLabel.setLayoutX(150);
        appLabel.setLayoutY(20);

        Label mode = new Label("Administrator");
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

        this.addBookBtn = new Button("Add Book");
        this.addBookBtn.getStyleClass().add("btn");
        this.addBookBtn.setLayoutX(30);
        this.addBookBtn.setLayoutY(180);

        this.incrStkBtn = new Button("Add Stock");
        this.incrStkBtn.getStyleClass().add("btn");
        this.incrStkBtn.setLayoutX(140);
        this.incrStkBtn.setLayoutY(180);

        this.checkoutBtn = new Button("Checkout");
        this.checkoutBtn.getStyleClass().add("btn");
        this.checkoutBtn.setLayoutX(260);
        this.checkoutBtn.setLayoutY(180);

        this.fullInfoBtn = new Button("Full Info");
        this.fullInfoBtn.getStyleClass().add("btn");
        this.fullInfoBtn.setLayoutX(370);
        this.fullInfoBtn.setLayoutY(180);

        Label libLabel = new Label("Employee(s):");
        libLabel.getStyleClass().add("heading");
        libLabel.setLayoutX(125);
        libLabel.setLayoutY(240);

        this.empListBox = new ComboBox<>();
        this.empListBox.getStyleClass().add("field");
        this.empListBox.setLayoutX(125);
        this.empListBox.setLayoutY(270);

        this.addEmpBtn = new Button("Add");
        this.addEmpBtn.getStyleClass().add("btn");
        this.addEmpBtn.setLayoutX(30);
        this.addEmpBtn.setLayoutY(320);

        this.remEmpBtn = new Button("Remove");
        this.remEmpBtn.getStyleClass().add("btn");
        this.remEmpBtn.setLayoutX(140);
        this.remEmpBtn.setLayoutY(320);

        this.editEmpBtn = new Button("Edit");
        this.editEmpBtn.getStyleClass().add("btn");
        this.editEmpBtn.setLayoutX(260);
        this.editEmpBtn.setLayoutY(320);

        this.chkLibPerfBtn = new Button("Work Perf.");
        this.chkLibPerfBtn.getStyleClass().add("btn");
        this.chkLibPerfBtn.setLayoutX(370);
        this.chkLibPerfBtn.setLayoutY(320);

        this.chkStrPerfBtn = new Button("Store Perf.");
        this.chkStrPerfBtn.getStyleClass().add("btn");
        this.chkStrPerfBtn.setLayoutX(140);
        this.chkStrPerfBtn.setLayoutY(380);

        this.logoutBtn = new Button("Log out");
        this.logoutBtn.getStyleClass().add("btn");
        this.logoutBtn.setLayoutX(260);
        this.logoutBtn.setLayoutY(380);

        // update combobox data
        updateEmployeesData();
        updateBooksData();

        this.getChildren().addAll(
                mode, appLabel, bookLabel, bookListBox, addBookBtn, incrStkBtn, checkoutBtn, fullInfoBtn,
                libLabel, empListBox, addEmpBtn, remEmpBtn, editEmpBtn, chkLibPerfBtn,
                chkStrPerfBtn, logoutBtn
        );

    }

    // ************************ END ************************* //

    // ************************ Fn ************************** //

    public void showLayout(BorderPane mainLayout) {
        updateEmployeesData();
        updateBooksData();
        mainLayout.setCenter(this);
    }

    public void updateBooksData() {

        bookListBox.getItems().remove(0, bookListBox.getItems().size());

        model.getBooks().forEach(e -> {
            bookListBox.getItems().add(e.getName());
        });

        bookListBox.getSelectionModel().select(0);

    }

    public void updateEmployeesData() {

        empListBox.getItems().remove(0, empListBox.getItems().size());

        model.getEmployees().forEach(e -> {
            empListBox.getItems().add(e.getName() + " (" + e.getAccessLevel().toString() + ")");
        });

        empListBox.getSelectionModel().select(0);

        if (empListBox.getItems().get(0).contains("manager"))
            disableChkLibPerfBtn(true);

    }

    // ************************ END ************************* //

    // ******************* Getter/Setter ******************** //

    public int getBookSelectedIndex() {
        return bookListBox.getSelectionModel().getSelectedIndex();
    }

    public int getEmpSelectedIndex() {
        return empListBox.getSelectionModel().getSelectedIndex();
    }

    public void setBookListBoxAction(EventHandler<ActionEvent> value) {
        bookListBox.setOnAction(value);
    }

    public void setEmpListBoxAction(EventHandler<ActionEvent> value) {
        empListBox.setOnAction(value);
    }

    public void setAddBookBtnAction(EventHandler<ActionEvent> value) {
        addBookBtn.setOnAction(value);
    }

    public void setIncrStkBtnAction(EventHandler<ActionEvent> value) {
        incrStkBtn.setOnAction(value);
    }

    public void setCheckoutBtnAction(EventHandler<ActionEvent> value) {
        checkoutBtn.setOnAction(value);
    }

    public void setFullInfoBtnAction(EventHandler<ActionEvent> value) {
        fullInfoBtn.setOnAction(value);
    }

    public void setAddEmpBtnAction(EventHandler<ActionEvent> value) {
        addEmpBtn.setOnAction(value);
    }

    public void setRemEmpBtnAction(EventHandler<ActionEvent> value) {
        remEmpBtn.setOnAction(value);
    }

    public void setEditEmpBtnAction(EventHandler<ActionEvent> value) {
        editEmpBtn.setOnAction(value);
    }

    public void disableChkLibPerfBtn(boolean value) {
        chkLibPerfBtn.setDisable(value);
    }

    public void setChkLibPerfBtnAction(EventHandler<ActionEvent> value) {
        chkLibPerfBtn.setOnAction(value);
    }

    public void setChkStrPerfBtnAction(EventHandler<ActionEvent> value) {
        chkStrPerfBtn.setOnAction(value);
    }

    public void setLogoutBtnAction(EventHandler<ActionEvent> value) {
        logoutBtn.setOnAction(value);
    }

    // *********************** END ************************** //

}
