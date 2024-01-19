package bookstore.java.ctrl;
import bookstore.java.cmp.Bill;
import bookstore.java.cmp.Book;
import bookstore.java.cmp.Message;
import bookstore.java.model.LibrarianModel;
import bookstore.java.view.LibrarianView;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.mockito.Mockito.*;

public class LibrarianCtrlTest {



    private LibrarianModel model;
    private LibrarianView view;
    private LibrarianCtrl controller;


     // Setup method to initialize objects before each test
    @Before
    public void setUp() {
        model = mock(LibrarianModel.class);
        view = mock(LibrarianView.class);
        controller = new LibrarianCtrl(model, view);
    }


//The testBookListBoxActionBookExists method is a unit test
    @Test
    public void testBookListBoxActionBookExists() {
        // Mocks the getSelectedIndex method of the view mock object to return 0 when called.
        when(view.getSelectedIndex()).thenReturn(0);

        // Mocks the getBook method of the model mock object to return a specific Book object when called with any integer argument.
        when(model.getBook(anyInt())).thenReturn(new Book("123", "Sample Book", "Fiction", "John Doe", "Publisher", "01-01-2022"));

        // Calls the method bookListBoxAction of the controller object, which is the method being tested.
        controller.bookListBoxAction();

        // Verifying that the necessary methods were called

        //Ensures that the getSelectedIndex method of the view mock object is called exactly once during the test.
        verify(view, times(1)).getSelectedIndex();
         //Verifies that the getBook method of the model mock object is called with the argument 0 exactly once during the test.
        verify(model, times(1)).getBook(eq(0));
        // Checks that the updateDataFields method of the view mock object is called with any instance of the Book class exactly once during the test.
        verify(view, times(1)).updateDataFields(any(Book.class));
    }

    @Test
    public void testBookListBoxActionBookNotExists() {
// This line mocks the behavior of the getSelectedIndex method of the view object. 
//It indicates that when the getSelectedIndex method is called, it should return 1.
when(view.getSelectedIndex()).thenReturn(1);

 // This line mocks the behavior of the getBook method of the model object. It specifies that when the getBook method is 
//called with any integer argument, it should return null, indicating that there is no book with the specified index.
when(model.getBook(anyInt())).thenReturn(null);

        // This line invokes the bookListBoxAction method of the controller object. This is the method being tested.
        controller.bookListBoxAction();

        // Verifying that the necessary methods were called

       //verifies that the getSelectedIndex method of the view object was called exactly once during the test. 
      //It ensures that the method is being used as expected.
        verify(view, times(1)).getSelectedIndex();
     //verifies that the getBook method of the model object was called with the argument 1 exactly once during the test. 
     //It checks that the method is being invoked correctly.
        verify(model, times(1)).getBook(eq(1));
       //checks that the updateDataFields method of the view object was never called during the test. It ensures that, 
      //in the case where the book does not exist, the view is not updated with any book data
        verify(view, never()).updateDataFields(any(Book.class));
    }

   @Test
public void testFullInfoBtnActionBookExists() {
    // Mocking the view's getSelectedIndex method
    when(view.getSelectedIndex()).thenReturn(0);

    // Mocking the model's getBook method
    when(model.getBook(anyInt())).thenReturn(new Book("123", "Sample Book", "Fiction", "John Doe", "Publisher", "01-01-2022"));

    // Mocking the Message.showInfo method to do nothing when called
    doNothing().when(Message.class);
    Message.showInfo(anyString(), anyString());

    // Calling the method to be tested
    controller.fullInfoBtnAction();

    // Verifying that the necessary methods were called
    verify(view, times(1)).getSelectedIndex(); // Ensures getSelectedIndex is called exactly once
    verify(model, times(1)).getBook(eq(0)); // Ensures getBook is called with argument 0 exactly once
}


  @Test
public void testFullInfoBtnActionBookNotExists() {
    // Mocking the view's getSelectedIndex method
    when(view.getSelectedIndex()).thenReturn(1);

    // Mocking the model's getBook method
    when(model.getBook(anyInt())).thenReturn(null);

    // Mocking the Message.showError method to do nothing when called
    doNothing().when(Message.class);
    Message.showError(anyString());

    // Calling the method to be tested
    controller.fullInfoBtnAction();

    // Verifying that the necessary methods were called
    verify(view, times(1)).getSelectedIndex(); // Ensures getSelectedIndex is called exactly once
    verify(model, times(1)).getBook(eq(1)); // Ensures getBook is called with argument 1 exactly once
}


    @Test
    public void testCheckoutBtnActionSuccessfulBilling() {
        // Mocking the view's getSelectedIndex method
        when(view.getSelectedIndex()).thenReturn(0);

        // Mocking the Message.showInput method to return a valid quantity
        when(Message.showInput(anyString(), anyString())).thenReturn(Optional.of("5"));

        // Mocking the model's getBook and checkOutBook methods
        when(model.getBook(anyInt())).thenReturn(new Book("123", "Sample Book", "Fiction", "John Doe", "Publisher", "01-01-2022"));
        when(model.checkOutBook(anyString(), anyInt())).thenReturn(1);

        // Mocking the model's getBill method

        // Mocking the Message.showConfirm method to return OK
        when(Message.showConfirm(anyString(), anyString())).thenReturn(Optional.of(ButtonType.OK));

        // Mocking the model's updateData method
        doNothing().when(model).updateData();

        // Calling the method to be tested
        controller.checkoutBtnAction();

        // Verifying that the necessary methods were called
        verify(view, times(1)).getSelectedIndex();
        verify(model, times(1)).getBook(eq(0));
        verify(model, times(1)).checkOutBook(eq("123"), eq(5));
        verify(model, times(1)).getBill(eq(1));
        verify(model, times(1)).updateData();
        verify(view, times(1)).updateDataFields(any(Book.class));
    }

    @Test
    public void testCheckoutBtnActionUnsuccessfulBilling() {
        // Mocking the view's getSelectedIndex method
        when(view.getSelectedIndex()).thenReturn(0);

        // Mocking the Message.showInput method to return an invalid quantity
        when(Message.showInput(anyString(), anyString())).thenReturn(Optional.of("15"));

        // Mocking the model's getBook and checkOutBook methods
        when(model.getBook(anyInt())).thenReturn(new Book("123", "Sample Book", "Fiction", "John Doe", "Publisher", "01-01-2022"));
        when(model.checkOutBook(anyString(), anyInt())).thenReturn(-1);

        // Mocking the Message.showError method
        doNothing().when(Message.class);
        Message.showError(anyString());

        // Calling the method to be tested
        controller.checkoutBtnAction();

        // Verifying that the necessary methods were called

       //Verifies that the getSelectedIndex method of the view object is called exactly once during the test.
        verify(view, times(1)).getSelectedIndex();
        //Verifies that the getBook method of the model object is called with the argument 0 exactly once during the test.
        verify(model, times(1)).getBook(eq(0));
        //Verifies that the checkOutBook method of the model object is called with arguments "123" and 15 exactly once during the test.
        verify(model, times(1)).checkOutBook(eq("123"), eq(15));
        //Ensures that the getBill method of the model object is never called during the test.
        verify(model, never()).getBill(anyInt());
       //Checks that the updateData method of the model object is never called during the test.
        verify(model, never()).updateData();
        //Ensures that the updateDataFields method of the view object is never called during the test.
        verify(view, never()).updateDataFields(any(Book.class));
    }
}
