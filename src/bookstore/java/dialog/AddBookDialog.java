
package bookstore.java.dialog;

import bookstore.ResourceLoader;

import java.util.ArrayList;
import java.util.Optional;

import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class AddBookDialog {

    Dialog<ArrayList<String>> dialog;

    public AddBookDialog() {

        dialog = new Dialog<>();
        dialog.setHeaderText("Fill Book data\n(*All fields are compulsory)");
        dialog.setTitle("Add Book");

        // Set the button types.
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        Pane pane = new Pane();
        pane.getStylesheets().add(
                new ResourceLoader().loadStylesheet("myStyle").toExternalForm()
        );

        Label isbnLabel = new Label("ISBN:");
        isbnLabel.getStyleClass().add("heading");
        isbnLabel.setLayoutX(20);
        isbnLabel.setLayoutY(20);

        TextField isbnField = new TextField();
        isbnField.setEditable(true);
        isbnField.setPromptText("Enter a numeric value");
        isbnField.getStyleClass().add("value_field");
        isbnField.setLayoutX(140);
        isbnField.setLayoutY(15);

        Label nameLabel = new Label("Name:");
        nameLabel.getStyleClass().add("heading");
        nameLabel.setLayoutX(20);
        nameLabel.setLayoutY(70);

        TextField nameField = new TextField();
        nameField.setEditable(true);
        nameField.setPromptText("Name of the book");
        nameField.getStyleClass().add("value_field");
        nameField.setLayoutX(140);
        nameField.setLayoutY(65);

        Label categoryLabel = new Label("Category:");
        categoryLabel.getStyleClass().add("heading");
        categoryLabel.setLayoutX(20);
        categoryLabel.setLayoutY(120);

        TextField categoryField = new TextField();
        categoryField.setEditable(true);
        categoryField.setPromptText("Category of the book");
        categoryField.getStyleClass().add("value_field");
        categoryField.setLayoutX(140);
        categoryField.setLayoutY(115);

        Label publisherLabel = new Label("Publisher:");
        publisherLabel.getStyleClass().add("heading");
        publisherLabel.setLayoutX(20);
        publisherLabel.setLayoutY(170);

        TextField publisherField = new TextField();
        publisherField.setEditable(true);
        publisherField.setPromptText("Publisher of the book");
        publisherField.getStyleClass().add("value_field");
        publisherField.setLayoutX(140);
        publisherField.setLayoutY(165);

        Label pDateLabel = new Label("Publish Date:");
        pDateLabel.getStyleClass().add("heading");
        pDateLabel.setLayoutX(20);
        pDateLabel.setLayoutY(220);

        TextField pDateField = new TextField();
        pDateField.setEditable(true);
        pDateField.setPromptText("Date format: DD-MM-YYYY");
        pDateField.getStyleClass().add("value_field");
        pDateField.setLayoutX(140);
        pDateField.setLayoutY(215);

        Label cpLabel = new Label("Cost Price:");
        cpLabel.getStyleClass().add("heading");
        cpLabel.setLayoutX(20);
        cpLabel.setLayoutY(270);

        TextField cpField = new TextField();
        cpField.setEditable(true);
        cpField.setPromptText("Enter a numeric value");
        cpField.getStyleClass().add("value_field");
        cpField.setLayoutX(140);
        cpField.setLayoutY(265);

        Label spLabel = new Label("Selling Price:");
        spLabel.getStyleClass().add("heading");
        spLabel.setLayoutX(20);
        spLabel.setLayoutY(320);

        TextField spField = new TextField();
        spField.setEditable(true);
        spField.setPromptText("Enter a numeric value");
        spField.getStyleClass().add("value_field");
        spField.setLayoutX(140);
        spField.setLayoutY(315);

        Label authorLabel = new Label("Author:");
        authorLabel.getStyleClass().add("heading");
        authorLabel.setLayoutX(20);
        authorLabel.setLayoutY(370);

        TextField authorField = new TextField();
        authorField.setEditable(true);
        authorField.setPromptText("Author of the book");
        authorField.getStyleClass().add("value_field");
        authorField.setLayoutX(140);
        authorField.setLayoutY(365);

        Label stockLabel = new Label("Stock:");
        stockLabel.getStyleClass().add("heading");
        stockLabel.setLayoutX(20);
        stockLabel.setLayoutY(420);

        TextField stockField = new TextField();
        stockField.setEditable(true);
        stockField.setPromptText("Enter a numeric value");
        stockField.getStyleClass().add("value_field");
        stockField.setLayoutX(140);
        stockField.setLayoutY(415);

        pane.getChildren().addAll(
                isbnLabel, isbnField,
                nameLabel, nameField,
                categoryLabel, categoryField,
                publisherLabel, publisherField,
                pDateLabel, pDateField,
                cpLabel, cpField,
                spLabel, spField,
                authorLabel, authorField,
                stockLabel, stockField
        );

        dialog.getDialogPane().setContent(pane);

        // Convert the result to book data list
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == ButtonType.OK) {

                ArrayList<String> data = new ArrayList<>();
                data.add(isbnField.getText());
                data.add(nameField.getText());
                data.add(categoryField.getText());
                data.add(publisherField.getText());
                data.add(pDateField.getText());
                data.add(cpField.getText());
                data.add(spField.getText());
                data.add(authorField.getText());
                data.add(stockField.getText());

                return data;
            }
            return null;
        });

    }

    public Optional<ArrayList<String>> showAndGetResult() {
        return dialog.showAndWait();
    }

}
