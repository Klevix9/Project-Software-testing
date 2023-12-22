import bookstore.java.cmp.User;
import bookstore.java.ctrl.LoginCtrl;
import bookstore.java.model.LoginModel;
import bookstore.java.view.LoginView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class LoginCtrlTest {

    private LoginModelStub model;
    private LoginViewStub view;
    private LoginCtrl loginCtrl;

    @Before
    public void setUp() {
        model = new LoginModelStub();
        view = new LoginViewStub();
        loginCtrl = new LoginCtrl(model, view);
    }

    @Test
    public void testLoginBtnActionWithValidCredentials() {
        // Stubbing data
        model.setLoginResult(true);
        model.setUserToReturn(new User("John Doe", "john@example.com", "password", User.AccessLevel.LIBRARIAN));

        // Triggering the method
        loginCtrl.loginBtnAction();

        // Verifying that the view is cleared and the layout is changed
        assertTrue(view.isEmailCleared());
        assertTrue(view.isPasswordCleared());
        assertEquals(1, model.getChangeViewParam());
    }

    @Test
    public void testLoginBtnActionWithInvalidCredentials() {
        // Stubbing data
        model.setLoginResult(false);

        // Triggering the method
        loginCtrl.loginBtnAction();

        // Verifying that an error message is shown
        assertTrue(view.isErrorShown());
        assertFalse(view.isEmailCleared());
        assertFalse(view.isPasswordCleared());
        assertEquals(0, model.getChangeViewParam());
    }

    // Add more tests for boundary and equivalence cases as needed

    // Stub class for LoginModel
    private static class LoginModelStub extends LoginModel {
        private boolean loginResult;
        private User userToReturn;
        private int changeViewParam;

        public void setLoginResult(boolean loginResult) {
            this.loginResult = loginResult;
        }

        public void setUserToReturn(User user) {
            this.userToReturn = user;
        }

        public int getChangeViewParam() {
            return changeViewParam;
        }

        @Override
        public boolean login() {
            return loginResult;
        }

        @Override
        public User getCurrUser() {
            return userToReturn;
        }

        @Override
        public void changeView(int res) {
            this.changeViewParam = res;
        }
    }

    // Stub class for LoginView
    private static class LoginViewStub extends LoginView {
        private boolean errorShown;
        private boolean emailCleared;
        private boolean passwordCleared;

        public boolean isErrorShown() {
            return errorShown;
        }

        public boolean isEmailCleared() {
            return emailCleared;
        }

        public boolean isPasswordCleared() {
            return passwordCleared;
        }

        @Override
        public String getEmailPhFieldText() {
            return "john@example.com";
        }

        @Override
        public String getPasswordFieldText() {
            return "password";
        }

        @Override
        public void setEmailPhFieldText(String text) {
            emailCleared = true;
        }

        @Override
        public void setPasswordFieldText(String text) {
            passwordCleared = true;
        }

        @Override
        public void setLoginBtnAction(EventHandler<ActionEvent> value) {
            // Simulate button click
            value.handle(mock(ActionEvent.class));
        }

        @Override
        public void setLoginBtnAction(EventHandler<ActionEvent> value) {
            // Simulate button click
            value.handle(mock(ActionEvent.class));
        }

        @Override
        public void setLoginBtnAction(EventHandler<ActionEvent> value) {
            // Simulate button click
            value.handle(mock(ActionEvent.class));
        }
    }
}
