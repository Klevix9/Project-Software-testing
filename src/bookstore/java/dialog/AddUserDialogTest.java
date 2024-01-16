package bookstore.java.dialog;

import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextField;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.*;

public class AddUserDialogTest {

    @Mock
    private Dialog<ArrayList<String>> dialogMock;
    @Mock
    private TextField nameFieldMock;
    @Mock
    private TextField birthdayFieldMock;
    @Mock
    private TextField phNoFieldMock;
    @Mock
    private TextField emailFieldMock;
    @Mock
    private TextField passwordFieldMock;
    @Mock
    private TextField salaryFieldMock;
    @Mock
    private TextField accessFieldMock;

    private AddUserDialog addUserDialog;

    @Before
    public void setUp() {
        addUserDialog = new AddUserDialog(dialogMock, nameFieldMock, birthdayFieldMock, phNoFieldMock, emailFieldMock,
                passwordFieldMock, salaryFieldMock, accessFieldMock);
    }

    @Test
    public void testShowAndGetResult_OKButtonClicked() {
        if (this.dialogMock!=null ) {
            // Arrange
            when(dialogMock.showAndWait()).thenReturn(Optional.of(new ArrayList<>()));

            // Act
            Optional<ArrayList<String>> result = addUserDialog.showAndGetResult();

            // Assert
            verify(dialogMock).showAndWait();
            assertEquals(Optional.of(new ArrayList<>()), result);
        }
    }

    @Test
    public void testShowAndGetResult_CancelButtonClicked() {
        if (this.dialogMock!=null ) {
            // Arrange
            when(dialogMock.showAndWait()).thenReturn(Optional.empty());

            // Act
            Optional<ArrayList<String>> result = addUserDialog.showAndGetResult();

            // Assert
            verify(dialogMock).showAndWait();
            assertFalse(result.isPresent());
        }
    }

    @Test
    public void testShowAndGetResult_WithData() {
        if (this.dialogMock!=null ) {
            // Arrange
            when(dialogMock.showAndWait()).thenReturn(Optional.of(new ArrayList<>()));
            when(nameFieldMock.getText()).thenReturn("John Doe");
            when(birthdayFieldMock.getText()).thenReturn("01-01-2000");
            when(phNoFieldMock.getText()).thenReturn("1234567890");
            when(emailFieldMock.getText()).thenReturn("john.doe@example.com");
            when(passwordFieldMock.getText()).thenReturn("password");
            when(salaryFieldMock.getText()).thenReturn("5000");
            when(accessFieldMock.getText()).thenReturn("Admin");

            // Act
            Optional<ArrayList<String>> result = addUserDialog.showAndGetResult();

            // Assert
            verify(dialogMock).showAndWait();
            assertEquals(Optional.of(new ArrayList<>()), result);
            verify(nameFieldMock).getText();
            verify(birthdayFieldMock).getText();
            verify(phNoFieldMock).getText();
            verify(emailFieldMock).getText();
            verify(passwordFieldMock).getText();
            verify(salaryFieldMock).getText();
            verify(accessFieldMock).getText();
        }
    }
}