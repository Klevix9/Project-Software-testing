
package bookstore.java.dialog;

import bookstore.ResourceLoader;

import java.util.ArrayList;
import java.util.Optional;

import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class AddUserDialog {

    Dialog<ArrayList<String>> dialog;
    TextField nameField, birthdayField, phNoField, emailField, passwordField, salaryField, accessField;

    public AddUserDialog() {

        dialog = new Dialog<>();
        dialog.setHeaderText("Fill User data\n(*All fields are compulsory)");
        dialog.setTitle("Add User");

        // Set the button types.
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        Pane pane = new Pane();
        pane.getStylesheets().add(
                new ResourceLoader().loadStylesheet("myStyle").toExternalForm()
        );

        Label nameLabel = new Label("Name:");
        nameLabel.getStyleClass().add("heading");
        nameLabel.setLayoutX(20);
        nameLabel.setLayoutY(20);

        this.nameField = new TextField();
        nameField.setEditable(true);
        nameField.setPromptText("Enter name");
        nameField.getStyleClass().add("value_field");
        nameField.setLayoutX(140);
        nameField.setLayoutY(15);

        Label birthdayLabel = new Label("Birthday:");
        birthdayLabel.getStyleClass().add("heading");
        birthdayLabel.setLayoutX(20);
        birthdayLabel.setLayoutY(70);

        this.birthdayField = new TextField();
        birthdayField.setEditable(true);
        birthdayField.setPromptText("Format: DD-MM-YYYY");
        birthdayField.getStyleClass().add("value_field");
        birthdayField.setLayoutX(140);
        birthdayField.setLayoutY(65);

        Label phNoLabel = new Label("Phone No.:");
        phNoLabel.getStyleClass().add("heading");
        phNoLabel.setLayoutX(20);
        phNoLabel.setLayoutY(120);

        this.phNoField = new TextField();
        phNoField.setEditable(true);
        phNoField.setPromptText("Enter numeric value");
        phNoField.getStyleClass().add("value_field");
        phNoField.setLayoutX(140);
        phNoField.setLayoutY(115);

        Label emailLabel = new Label("Email:");
        emailLabel.getStyleClass().add("heading");
        emailLabel.setLayoutX(20);
        emailLabel.setLayoutY(170);

        this.emailField = new TextField();
        emailField.setEditable(true);
        emailField.setPromptText("Email of the user");
        emailField.getStyleClass().add("value_field");
        emailField.setLayoutX(140);
        emailField.setLayoutY(165);

        Label passwordLabel = new Label("Password:");
        passwordLabel.getStyleClass().add("heading");
        passwordLabel.setLayoutX(20);
        passwordLabel.setLayoutY(220);

        this.passwordField = new TextField();
        passwordField.setEditable(true);
        passwordField.setPromptText("Password of the user");
        passwordField.getStyleClass().add("value_field");
        passwordField.setLayoutX(140);
        passwordField.setLayoutY(215);

        Label salaryLabel = new Label("Salary:");
        salaryLabel.getStyleClass().add("heading");
        salaryLabel.setLayoutX(20);
        salaryLabel.setLayoutY(270);

        this.salaryField = new TextField();
        salaryField.setEditable(true);
        salaryField.setPromptText("Enter a numeric value");
        salaryField.getStyleClass().add("value_field");
        salaryField.setLayoutX(140);
        salaryField.setLayoutY(265);

        Label accessLabel = new Label("User Type:");
        accessLabel.getStyleClass().add("heading");
        accessLabel.setLayoutX(20);
        accessLabel.setLayoutY(320);

        this.accessField = new TextField();
        accessField.setEditable(true);
        accessField.setPromptText("Admin/Manager/Librarian");
        accessField.getStyleClass().add("value_field");
        accessField.setLayoutX(140);
        accessField.setLayoutY(315);

        pane.getChildren().addAll(
                nameLabel, nameField,
                birthdayLabel, birthdayField,
                phNoLabel, phNoField,
                emailLabel, emailField,
                passwordLabel, passwordField,
                salaryLabel, salaryField,
                accessLabel, accessField
        );

        dialog.getDialogPane().setContent(pane);

        // Convert the result to book data list
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == ButtonType.OK) {

                ArrayList<String> data = new ArrayList<>();
                data.add(nameField.getText());
                data.add(birthdayField.getText());
                data.add(phNoField.getText());
                data.add(emailField.getText());
                data.add(passwordField.getText());
                data.add(salaryField.getText());
                data.add(accessField.getText());

                return data;
            }
            return null;
        });

    }

    public Optional<ArrayList<String>> showAndGetResult() {
        return dialog.showAndWait();
    }

    public Optional<ArrayList<String>> showAndGetResult(
            String name, String birthday, String phNo, String email,
            String password, String salary, String access
    ) {
        this.nameField.setText(name);
        this.birthdayField.setText(birthday);
        this.phNoField.setText(phNo);
        this.emailField.setText(email);
        this.passwordField.setText(password);
        this.salaryField.setText(salary);
        this.accessField.setText(access);
        return dialog.showAndWait();
    }

}
