
package bookstore.java.cmp;

import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextInputDialog;

public class Message {

    private static Alert alert = new Alert(AlertType.NONE);
    private static TextInputDialog td = new TextInputDialog();

    public static void showError(String message) {
        alert.setAlertType(AlertType.ERROR);
        alert.setContentText(message);
        alert.setHeaderText("Error");
        alert.show();
    }

    public static void showInfo(String message, String headerText) {
        alert.setAlertType(AlertType.INFORMATION);
        alert.setContentText(message);
        alert.setHeaderText(headerText);
        alert.show();
    }

    public static Optional<ButtonType> showConfirm(String message, String headerText) {
        alert.setAlertType(AlertType.CONFIRMATION);
        alert.setContentText(message);
        alert.setHeaderText(headerText);
        return alert.showAndWait();
    }

    public static Optional<String> showInput(String message, String headerText) {
        td.setContentText(message);
        td.setHeaderText(headerText);
        return td.showAndWait();
    }

}
