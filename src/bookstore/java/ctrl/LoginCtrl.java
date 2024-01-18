import bookstore.java.cmp.Message;
import bookstore.java.cmp.User;
import bookstore.java.ctrl.LoginCtrl;
import bookstore.java.model.LoginModel;
import bookstore.java.view.LoginView;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;

public class LoginCtrlTest {

    private LoginModel model;
    private LoginView view;
    private LoginCtrl controller;

    @Before
    public void setUp() {
        model = mock(LoginModel.class);
        view = mock(LoginView.class);
        controller = new LoginCtrl(model, view);
    }

    @Test
    public void testLoginBtnActionSuccessfulLoginLibrarian() {
        // Mocking the view's getEmailPhFieldText and getPasswordFieldText methods
        when(view.getEmailPhFieldText()).thenReturn("librarian@example.com");
        when(view.getPasswordFieldText()).thenReturn("password123");

        // Mocking the model's setEmailPh, setPassword, login, and getCurrUser methods
        doNothing().when(model).setEmailPh("librarian@example.com");
        doNothing().when(model).setPassword("password123");
        when(model.login()).thenReturn(true);
        String[] userData = {"John Doe", "1990-01-01", "1234567890", "john.doe@example.com", "password", "5000", "admin", "active"};
        when(model.getCurrUser()).thenReturn(new User(userData));

        // Mocking the view's setEmailPhFieldText, setPasswordFieldText, and changeView methods
        doNothing().when(view).setEmailPhFieldText("");
        doNothing().when(view).setPasswordFieldText("");
        doNothing().when(model).changeView(1);

        // Calling the method to be tested
        controller.loginBtnAction();

        // Verifying that the necessary methods were called
        verify(view, times(1)).getEmailPhFieldText();
        verify(view, times(1)).getPasswordFieldText();
        verify(model, times(1)).setEmailPh("librarian@example.com");
        verify(model, times(1)).setPassword("password123");
        verify(model, times(1)).login();
        verify(model, times(1)).getCurrUser();
        verify(view, times(1)).setEmailPhFieldText("");
        verify(view, times(1)).setPasswordFieldText("");
        verify(model, times(1)).changeView(1);
    }

    @Test
    public void testLoginBtnActionUnsuccessfulLogin() {
        // Mocking the view's getEmailPhFieldText and getPasswordFieldText methods
        when(view.getEmailPhFieldText()).thenReturn("invalid@example.com");
        when(view.getPasswordFieldText()).thenReturn("invalidpassword");

        // Mocking the model's setEmailPh, setPassword, and login methods
        doNothing().when(model).setEmailPh("invalid@example.com");
        doNothing().when(model).setPassword("invalidpassword");
        when(model.login()).thenReturn(false);

        // Mocking the Message.showError method
        doNothing().when(Message.class);
        Message.showError(anyString());

        // Calling the method to be tested
        controller.loginBtnAction();

        // Verifying that the necessary methods were called
        verify(view, times(1)).getEmailPhFieldText();
        verify(view, times(1)).getPasswordFieldText();
        verify(model, times(1)).setEmailPh("invalid@example.com");
        verify(model, times(1)).setPassword("invalidpassword");
        verify(model, times(1)).login();
        verify(view, never()).setEmailPhFieldText("");
        verify(view, never()).setPasswordFieldText("");
        verify(model, never()).changeView(anyInt());
    }
}
