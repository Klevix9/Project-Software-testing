package bookstore.java.cmp;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextInputDialog;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import bookstore.java.cmp.Message;

import java.util.Optional;

import static org.mockito.Mockito.*;

class MessageTest {

    @Mock
    private Alert alertMock;

    @Mock
    private TextInputDialog dialogMock;

    @InjectMocks
    private Message message;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        message = new Message();
        message.alert = alertMock;
        message.td = dialogMock;
    }

    @Test
    void showError() {
        message.showError("Test Error");
        verify(alertMock).setAlertType(Alert.AlertType.ERROR);
        verify(alertMock).setContentText("Test Error");
        verify(alertMock).setHeaderText("Error");
        verify(alertMock).show();
    }

    @Test
    void showInfo() {
        message.showInfo("Test Info", "Info Header");
        verify(alertMock).setAlertType(Alert.AlertType.INFORMATION);
        verify(alertMock).setContentText("Test Info");
        verify(alertMock).setHeaderText("Info Header");
        verify(alertMock).show();
    }

    @Test
    void showConfirm() {
        when(alertMock.showAndWait()).thenReturn(Optional.of(ButtonType.OK));

        Optional<ButtonType> result = message.showConfirm("Test Confirm", "Confirm Header");

        verify(alertMock).setAlertType(Alert.AlertType.CONFIRMATION);
        verify(alertMock).setContentText("Test Confirm");
        verify(alertMock).setHeaderText("Confirm Header");
        verify(alertMock).showAndWait();

        // Check if the result is as expected
        assert result.isPresent();
        assert result.get() == ButtonType.OK;
    }

    @Test
    void showInput() {
        when(dialogMock.showAndWait()).thenReturn(Optional.of("Test Input"));

        Optional<String> result = message.showInput("Test Input", "Input Header");

        verify(dialogMock).setContentText("Test Input");
        verify(dialogMock).setHeaderText("Input Header");
        verify(dialogMock).showAndWait();

        // Check if the result is as expected.
        assert result.isPresent();
        assert result.get().equals("Test Input");
    }
}