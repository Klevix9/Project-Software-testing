package bookstore.java.cmp;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextInputDialog;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MessageTest {

    private Alert alert;
    private TextInputDialog td;

    @BeforeEach
    public void setUp() {
        alert = Mockito.mock(Alert.class);
        td = Mockito.mock(TextInputDialog.class);
        Message.setAlert(alert);
        Message.setTextInputDialog(td);
    }

    @Test
    public void testShowError() {
        String message = "Error message";
        Message.showError(message);
        Mockito.verify(alert).setAlertType(Alert.AlertType.ERROR);
        Mockito.verify(alert).setContentText(message);
        Mockito.verify(alert).setHeaderText("Error");
        Mockito.verify(alert).show();
    }

    @Test
    public void testShowInfo() {
        String message = "Info message";
        String headerText = "Info";
        Message.showInfo(message, headerText);
        Mockito.verify(alert).setAlertType(Alert.AlertType.INFORMATION);
        Mockito.verify(alert).setContentText(message);
        Mockito.verify(alert).setHeaderText(headerText);
        Mockito.verify(alert).show();
    }

    @Test
    public void testShowConfirm() {
        String message = "Confirmation message";
        String headerText = "Confirm";
        ButtonType buttonType = ButtonType.OK;
        Mockito.when(alert.showAndWait()).thenReturn(Optional.of(buttonType));
        Optional<ButtonType> result = Message.showConfirm(message, headerText);
        Mockito.verify(alert).setAlertType(Alert.AlertType.CONFIRMATION);
        Mockito.verify(alert).setContentText(message);
        Mockito.verify(alert).setHeaderText(headerText);
        Mockito.verify(alert).showAndWait();
        assertEquals(Optional.of(buttonType), result);
    }

    @Test
    public void testShowInput() {
        String message = "Input message";
        String headerText = "Input";
        String inputText = "User input";
        Mockito.when(td.showAndWait()).thenReturn(Optional.of(inputText));
        Optional<String> result = Message.showInput(message, headerText);
        Mockito.verify(td).setContentText(message);
        Mockito.verify(td).setHeaderText(headerText);
        Mockito.verify(td).showAndWait();
        assertEquals(Optional.of(inputText), result);
    }
}
