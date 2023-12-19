
package bookstore.java.view;

import bookstore.java.model.LoginModel;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class LoginView extends Pane {

    private TextField emailPhField;
    private PasswordField passwordField;
    private Button loginBtn;

    private final LoginModel model;

    // *************** Constructor/Initializer ************** //

    public LoginView(LoginModel model) {
        this.model = model;
        init();
    }

    private void init() {

        Label appLabel = new Label("Book Store");
        appLabel.getStyleClass().add("app_label");
        appLabel.setLayoutX(150);
        appLabel.setLayoutY(20);

        Label emailLabel = new Label("Email/Phone No:");
        emailLabel.getStyleClass().add("heading");
        emailLabel.setLayoutX(125);
        emailLabel.setLayoutY(120);

        this.emailPhField = new TextField();
        this.emailPhField.setPromptText("Enter your email or phone no.");
        this.emailPhField.getStyleClass().add("field");
        this.emailPhField.setLayoutX(125);
        this.emailPhField.setLayoutY(150);

        Label passwordLabel = new Label("Password:");
        passwordLabel.getStyleClass().add("heading");
        passwordLabel.setLayoutX(125);
        passwordLabel.setLayoutY(205);

        this.passwordField = new PasswordField();
        this.passwordField.setPromptText("Enter your password");
        this.passwordField.getStyleClass().add("field");
        this.passwordField.setLayoutX(125);
        this.passwordField.setLayoutY(235);

        this.loginBtn = new Button("Login");
        this.loginBtn.getStyleClass().add("btn");
        this.loginBtn.setLayoutX(200);
        this.loginBtn.setLayoutY(300);

        this.getChildren().addAll(
                appLabel, emailLabel, emailPhField, passwordLabel, passwordField, loginBtn
        );

    }

    // ************************ END ************************* //

    // ************************ Fn ************************** //

    // ************************ END ************************* //

    // ******************* Getter/Setter ******************** //

    public String getEmailPhFieldText() {
        return emailPhField.getText();
    }

    public void setEmailPhFieldText(String text) {
        emailPhField.setText(text);
    }

    public void setEmailPhFieldAction(EventHandler<ActionEvent> value) {
        emailPhField.setOnAction(value);
    }

    public String getPasswordFieldText() {
        return passwordField.getText();
    }

    public void setPasswordFieldText(String text) {
        passwordField.setText(text);
    }

    public void setPasswordFieldAction(EventHandler<ActionEvent> value) {
        passwordField.setOnAction(value);
    }

    public void setLoginBtnAction(EventHandler<ActionEvent> value) {
        loginBtn.setOnAction(value);
    }

    // *********************** END ************************** //

}
