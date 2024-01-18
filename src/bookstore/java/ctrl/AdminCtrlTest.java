package bookstore.java.ctrl;
import static org.mockito.Mockito.*;

import bookstore.java.cmp.Book;
import bookstore.java.ctrl.AdminCtrl;
import bookstore.java.dialog.AddBookDialog;
import bookstore.java.cmp.Message;
import bookstore.java.model.AdminModel;
import bookstore.java.view.AdminView;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AdminCtrlTest {

    private AdminModel model;
    private AdminView view;
    private AdminCtrl controller;

    @Before
    public void setUp() {
        model = mock(AdminModel.class);
        view = mock(AdminView.class);
        controller = new AdminCtrl(model, view);
    }

    @Test
    public void testAddBookBtnAction_ValidBook() {
        // Mocking the result of the AddBookDialog
        when(new AddBookDialog().showAndGetResult()).thenReturn(
                Optional.of(new ArrayList<>(List.of("123456789", "Title", "Author", "Publisher", "01-01-2022", "5", "10.0", "15.0", "50")))
        );

        // Mocking the model behavior
        when(model.addBook(any(Book.class))).thenReturn(true);

        // Mocking Message.showInfo to avoid actual GUI interactions
        doNothing().when(Message.class);
        Message.showInfo(anyString(), anyString());

        // Executing the method
        controller.addBookBtnAction();

        // Verifying the interactions
        verify(model, times(1)).addBook(any(Book.class));
        verify(model, times(1)).updateData();
        verify(view, times(1)).updateBooksData();
    }

    @Test
    public void testAddBookBtnAction_InvalidBook() {
        // Mocking the result of the AddBookDialog with invalid input
        when(new AddBookDialog().showAndGetResult()).thenReturn(
                Optional.of(new ArrayList<>(List.of("123456789", "Title", "Author", "Publisher", "01-01-2022", "5", "10.0", "15.0", "50")))
        );

        // Mocking Message.showError to avoid actual GUI interactions
        doNothing().when(Message.class);
        Message.showError(anyString());

        // Executing the method
        controller.addBookBtnAction();

        // Verifying the interactions
        verify(model, never()).addBook(any(Book.class));
        verify(model, never()).updateData();
        verify(view, never()).updateBooksData();
    }

    @Test
    public void testCheckoutBtnActionUnsuccessfulBilling() {
        // Mocking the view's getBookSelectedIndex method
        when(view.getBookSelectedIndex()).thenReturn(0);

        // Mocking the Message.showInput method to return a valid quantity
        when(Message.showInput(anyString(), anyString())).thenReturn(Optional.of("15"));

        // Mocking the model's checkOutBook method
        when(model.checkOutBook(anyString(), anyInt())).thenReturn(-1);

        // Calling the method to be tested
        controller.checkoutBtnAction();

        // Verifying that the necessary methods were called
        verify(view, times(1)).getBookSelectedIndex();
        verify(Message, times(1)).showInput(anyString(), anyString());
        verify(model, times(1)).checkOutBook(eq("123"), eq(15));
        verify(model, never()).getBill(anyInt());
        verify(model, never()).updateData();
        verify(Message, times(1)).showError(anyString());
    }

    @Test
    public void testFullInfoBtnActionBookExists() {
        // Mocking the view's getBookSelectedIndex method
        when(view.getBookSelectedIndex()).thenReturn(0);

        // Mocking the model's getBook method
        when(model.getBook(anyInt())).thenReturn(new Book("123", "Sample Book", "Fiction", "John Doe", "Publisher", "01-01-2022", 20, 15, 100));

        // Calling the method to be tested
        controller.fullInfoBtnAction();

        // Verifying that the necessary methods were called
        verify(view, times(1)).getBookSelectedIndex();
        verify(model, times(1)).getBook(eq(0));

    }
