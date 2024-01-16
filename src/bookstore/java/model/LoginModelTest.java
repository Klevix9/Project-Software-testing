package bookstore.java.model;

import bookstore.java.cmp.User;
import bookstore.java.StoreLogic;
import bookstore.java.GUI;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class LoginModelTest {
    private LoginModel loginModel;
    private StoreLogic logic;
    private GUI gui;

    @Before
    public void setUp() {
        logic = mock(StoreLogic.class);
        gui = mock(GUI.class);
        loginModel = new LoginModel(logic, gui);
    }

    @Test
    public void testLogin_ValidCredentials() {
        // Arrange
        String emailPh = "test@example.com";
        String password = "password";
        User user = new User(new String[]{"John Doe"});
        when(logic.getUser(emailPh, password)).thenReturn(user);

        // Act
        boolean result = loginModel.login();

        // Assert
        verify(logic, times(1)).getUser("", "");
    }

    @Test
    public void testChangeView_Librarian() {
        // Arrange
        int res = 1;

        // Act
        loginModel.changeView(res);

        // Assert
        verify(gui, times(1)).setLayoutToLibrarian();
    }

    @Test
    public void testChangeView_Manager() {
        // Arrange
        int res = 2;

        // Act
        loginModel.changeView(res);

        // Assert
        verify(gui, times(1)).setLayoutToManager();
    }

    @Test
    public void testChangeView_Administrator() {
        // Arrange
        int res = 3;

        // Act
        loginModel.changeView(res);

        // Assert
        verify(gui, times(1)).setLayoutToAdministrator();
    }

    @Test
    public void testChangeView_Default() {
        // Arrange
        int res = 0;

        // Act
        loginModel.changeView(res);

        // Assert
        verify(gui, never()).setLayoutToLibrarian();
        verify(gui, never()).setLayoutToManager();
        verify(gui, never()).setLayoutToAdministrator();
    }

    @Test
    public void testSetEmailPh() {
        // Arrange
        String emailPh = "test@example.com";

        // Act
        loginModel.setEmailPh(emailPh);

        // Assert
        assertEquals(emailPh, loginModel.getEmailPh());
    }

    @Test
    public void testSetPassword() {
        // Arrange
        String password = "password";

        // Act
        loginModel.setPassword(password);

        // Assert
        assertEquals(password, loginModel.getPassword());
    }

    @Test
    public void testGetCurrUser() {
        // Arrange
        User user = new User(new String[]{"John Doe"});
        loginModel.setCurrUser(user);

        // Act
        User result = loginModel.getCurrUser();

        // Assert
        assertEquals(user, result);
    }
}