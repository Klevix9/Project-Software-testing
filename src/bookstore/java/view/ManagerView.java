
package bookstore.java.view;

import bookstore.java.model.ManagerModel;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class ManagerView extends Pane {

    private ComboBox<String> bookListBox, libListBox;
    private Button addBookBtn, incrStkBtn, fullInfoBtn, chkLibPerfBtn, chkStrPerfBtn, logoutBtn;

    private final ManagerModel model;

    // *************** Constructor/Initializer ************** //

    public ManagerView(ManagerModel model) {
        this.model = model;
        init();
    }

    private void init() {

        Label appLabel = new Label("Book Store");
        appLabel.getStyleClass().add("app_label");
        appLabel.setLayoutX(150);
        appLabel.setLayoutY(20);

        Label mode = new Label("Manager");
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
        this.addBookBtn.setLayoutX(90);
        this.addBookBtn.setLayoutY(180);

        this.incrStkBtn = new Button("Add Stock");
        this.incrStkBtn.getStyleClass().add("btn");
        this.incrStkBtn.setLayoutX(200);
        this.incrStkBtn.setLayoutY(180);

        this.fullInfoBtn = new Button("Full Info");
        this.fullInfoBtn.getStyleClass().add("btn");
        this.fullInfoBtn.setLayoutX(310);
        this.fullInfoBtn.setLayoutY(180);

        Label libLabel = new Label("Librarians:");
        libLabel.getStyleClass().add("heading");
        libLabel.setLayoutX(125);
        libLabel.setLayoutY(240);

        this.libListBox = new ComboBox<>();
        this.libListBox.getStyleClass().add("field");
        this.libListBox.setLayoutX(125);
        this.libListBox.setLayoutY(270);

        this.chkLibPerfBtn = new Button("Work Perf.");
        this.chkLibPerfBtn.getStyleClass().add("btn");
        this.chkLibPerfBtn.setLayoutX(200);
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
        updateLibrariansData();
        updateBooksData();

        this.getChildren().addAll(
                mode, appLabel, bookLabel, bookListBox, addBookBtn, incrStkBtn, fullInfoBtn,
                libLabel, libListBox, chkLibPerfBtn, chkStrPerfBtn, logoutBtn
        );

    }

    // ************************ END ************************* //

    // ************************ Fn ************************** //

    public void showLayout(BorderPane mainLayout) {
        updateLibrariansData();
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

    public void updateLibrariansData() {

        libListBox.getItems().remove(0, libListBox.getItems().size());

        model.getLibrarians().forEach(e -> {
            libListBox.getItems().add(e.getName());
        });

        libListBox.getSelectionModel().select(0);

    }

    // ************************ END ************************* //

    // ******************* Getter/Setter ******************** //

    public int getBookSelectedIndex() {
        return bookListBox.getSelectionModel().getSelectedIndex();
    }

    public int getLibSelectedIndex() {
        return libListBox.getSelectionModel().getSelectedIndex();
    }

    public void setBookListBoxAction(EventHandler<ActionEvent> value) {
        bookListBox.setOnAction(value);
    }

    public void setLibListBoxAction(EventHandler<ActionEvent> value) {
        libListBox.setOnAction(value);
    }

    public void setAddBookBtnAction(EventHandler<ActionEvent> value) {
        addBookBtn.setOnAction(value);
    }

    public void setIncrStkBtnAction(EventHandler<ActionEvent> value) {
        incrStkBtn.setOnAction(value);
    }

    public void setFullInfoBtnAction(EventHandler<ActionEvent> value) {
        fullInfoBtn.setOnAction(value);
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
