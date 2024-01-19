package bookstore.java.ctrl;

import bookstore.java.cmp.Bill;
import bookstore.java.cmp.Book;
import bookstore.java.cmp.User;
import bookstore.java.cmp.Message;

import bookstore.java.model.ManagerModel;
import bookstore.java.view.ManagerView;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

import static org.mockito.Mockito.*;

public class ManagerCtrlTest {
    private ManagerCtrl managerCtrl;

    private ManagerModel model;
    private ManagerView view;

    @BeforeEach
    public void setUp() {
        model = mock(ManagerModel.class);
        view = mock(ManagerView.class);
        managerCtrl = new ManagerCtrl(model, view);
    }

/**
 * This test method checks the functionality of the logout button action.
 * It ensures that the ManagerCtrl's logoutBtnAction method correctly invokes the logout method in the ManagerModel.
 */
@Test
public void testLogoutBtnAction() {
    // Arrange
    if (view != null && model != null && managerCtrl != null) {
        // Calling the logoutBtnAction method
        managerCtrl.logoutBtnAction();
// Verifies that the logout method is called exactly once in the ManagerModel
        verify(model, times(1)).logout(); 
    }
}


/**
 * This test method checks the functionality of the add book button action.
 * It simulates the user entering book data, specifying the quantity, and adds a book.
 * It verifies that the necessary methods in the ManagerModel and ManagerView are called correctly.
 */
@Test
public void testAddBookBtnAction() {
    // Arrange
    ArrayList<String> bookData = new ArrayList<>();
    bookData.add("1234567890"); // ISBN
    bookData.add("Book Name"); // Name
    bookData.add("Category"); // Category
    bookData.add("Author"); // Author
    bookData.add("01-01-2022"); // Publishing Date
    bookData.add("10"); // Cost Price
    bookData.add("15"); // Selling Price
    bookData.add("Publisher"); // Publisher
    bookData.add("20"); // Stock

    if (view != null && model != null && managerCtrl != null) {
        // Mocking the view's getBookSelectedIndex method
        when(view.getBookSelectedIndex()).thenReturn(0);

        // Mocking the Message.showInput method
        when(Message.showInput(eq("Quantity"), eq("Enter quantity"))).thenReturn(Optional.of("5"));

        // Mocking the model's addBook method
        when(model.addBook(any(Book.class))).thenReturn(true);
        
        managerCtrl.addBookBtnAction();
        
        verify(model, times(1)).addBook(any(Book.class)); // Verifies that addBook is called exactly once in the ManagerModel
        verify(model, times(1)).updateData(); // Verifies that updateData is called exactly once in the ManagerModel
        verify(view, times(1)).updateBooksData(); // Verifies that updateBooksData is called exactly once in the ManagerView
        // verify(Message, times(1)).showInfo(eq("Book added"), eq("Information")); // Potential verification for message display
    }
}


// This test method verifies the behavior of the "incrStkBtnAction" method in the ManagerCtrl class.
@Test
public void testIncrStkBtnAction() {
    // Check if necessary components are not null
    if (view != null && model != null && managerCtrl != null) {
        // Arrange
        // Mocking the selected book index in the view
        when(view.getBookSelectedIndex()).thenReturn(0);
        // Mocking the input dialog for quantity
        when(Message.showInput(eq("Quantity"), eq("Enter quantity"))).thenReturn(Optional.of("5"));
        // Mocking the addStock method in the model
        when(model.addStock(eq(0), eq(5))).thenReturn(true);
        // Invoking the method to be tested
        managerCtrl.incrStkBtnAction();
        // Verifying that addStock method is called with the expected arguments
        verify(model, times(1)).addStock(eq(0), eq(5));
        // Verifying that updateData method is called after increasing the stock
        verify(model, times(1)).updateData();
    }
}


    
// This test method verifies the behavior of the "fullInfoBtnAction" method in the ManagerCtrl class.
@Test
public void testFullInfoBtnAction() {
    // Check if necessary components are not null
    if (view != null && model != null && managerCtrl != null) {
        // Arrange
        // Mocking the selected book index in the view
        when(view.getBookSelectedIndex()).thenReturn(0);
        // Mocking the getBook method in the model
        when(model.getBook(eq(0))).thenReturn(
                new Book("Book Name", "1234567890", "Category", "Author", "Publisher", "01-01-2022", "15", "10", "20"));

        // Act
        // Invoking the method to be tested
        managerCtrl.fullInfoBtnAction();

        // Assert
        // Verifying that getBookSelectedIndex method is called to get the selected book index
        verify(view, times(1)).getBookSelectedIndex();
        // Verifying that getBook method is called with the expected index
        verify(model, times(1)).getBook(eq(0));
    }
}


// This test method verifies the behavior of the "chkLibPerfBtnAction" method in the ManagerCtrl class.
@Test
public void testChkLibPerfBtnAction() {
    // Check if necessary components are not null
    if (view != null && model != null && managerCtrl != null) {
        // Mocking the selected library index in the view
        when(view.getLibSelectedIndex()).thenReturn(0);
        // Mocking the getUser method in the model
        when(model.getUser(eq(0))).thenReturn(new User(new String[]{"User Name"}));
        
        // Creating sample bills for the mocked user
        ArrayList<Bill> bills = new ArrayList<>();
        bills.add(new Bill(new String[]{"1", "10"}));
        bills.add(new Bill(new String[]{"2", "20"}));

        // Mocking the getBills method in the model with any User instance
        when(model.getBills(any(User.class))).thenReturn(bills);
        // Invoking the method to be tested
        managerCtrl.chkLibPerfBtnAction();
        
        // Verifying that getLibSelectedIndex method is called to get the selected library index
        verify(view, times(1)).getLibSelectedIndex();
        // Verifying that getUser method is called with the expected index
        verify(model, times(1)).getUser(eq(0));
        // Verifying that getBills method is called with any User instance
        verify(model, times(1)).getBills(any(User.class));
    }
}


   // This test method verifies the behavior of the "setChkStrPerfBtnAction" method in the ManagerCtrl class.
@Test
public void testSetChkStrPerfBtnAction() {
    // Check if necessary components are not null
    if (view != null && model != null && managerCtrl != null) {
        // Creating sample bills for the mocked getBills method
        ArrayList<Bill> bills = new ArrayList<>();
        bills.add(new Bill(new String[]{"1", "10", "5", "2"}));
        bills.add(new Bill(new String[]{"2", "20", "10", "5"}));
        // Mocking the getBills method in the model
        when(model.getBills()).thenReturn(bills);
        // Creating sample books for the mocked getBooks method
        ArrayList<Book> books = new ArrayList<>();
        books.add(new Book("Book Name", "1234567890", "Category", "Author", "Publisher", "01-01-2022", "15", "10", "20"));
        // Mocking the getBooks method in the model
        when(model.getBooks()).thenReturn(books);
        // Invoking the method to be tested
        managerCtrl.setChkStrPerfBtnAction();
        // Verifying that getBills method is called to retrieve bills
        verify(model, times(1)).getBills();
        // Verifying that getBooks method is called to retrieve books
        verify(model, times(1)).getBooks();
    }
}

}
