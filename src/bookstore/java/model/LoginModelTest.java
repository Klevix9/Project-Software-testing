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

    // Set up method to initialize objects before each test
    @Before
    public void setUp() {
        // Mock StoreLogic and GUI objects
        logic = mock(StoreLogic.class);
        gui = mock(GUI.class);
        // Create LibrarianModel object with mocked dependencies
        librarianModel = new LibrarianModel(logic, gui);
    }

    // Test case for the logout method
    @Test
    public void testLogout() {
        // Act: Call the logout method
        librarianModel.logout();

        // Assert: Verify that logOut and setLayoutToLogin methods are called once each
        verify(logic, times(1)).logOut();
        verify(gui, times(1)).setLayoutToLogin();
    }

    // Test case for updating data
    @Test
    public void testUpdateData() {
        // Act: Call the updateData method
        librarianModel.updateData();

        // Assert: Verify that saveBillsData and saveBookData methods are called once each
        verify(logic, times(1)).saveBillsData();
        verify(logic, times(1)).saveBookData();
    }

    // Test case for getting the current user
    @Test
    public void testGetCurrUser() {
        // Arrange: Set up variables and mock behavior
        User user = new User(new String[]{"John Doe"});
        when(logic.getLoggedInUser()).thenReturn(user);

        // Act: Call the getCurrUser method
        User result = librarianModel.getCurrUser();

        // Assert: Verify that getLoggedInUser method is called once, and the result is the expected user
        verify(logic, times(1)).getLoggedInUser();
        assertEquals(user, result);
    }

    // Test case for getting a book by index
    @Test
    public void testGetBook() {
        // Arrange: Set up variables and mock behavior
        ArrayList<Book> books = new ArrayList<>();
        books.add(new Book("Book Name", "1234567890", "Category", "Author", "Publisher", "01-01-2022", "15", "10", "20"));
        when(logic.getAllBooks()).thenReturn(books);

        // Act: Call the getBook method
        Book result = librarianModel.getBook(0);

        // Assert: Verify that getAllBooks method is called once, and the result is the expected book
        verify(logic, times(1)).getAllBooks();
        assertEquals(books.get(0), result);
    }

    // Test case for getting the list of all books
    @Test
    public void testGetBooks() {
        // Arrange: Set up variables and mock behavior
        ArrayList<Book> books = new ArrayList<>();
        books.add(new Book("Book Name", "1234567890", "Category", "Author", "Publisher", "01-01-2022", "15", "10", "20"));
        when(logic.getAllBooks()).thenReturn(books);

        // Act: Call the getBooks method
        ArrayList<Book> result = librarianModel.getBooks();

        // Assert: Verify that getAllBooks method is called once, and the result is the expected list of books
        verify(logic, times(1)).getAllBooks();
        assertEquals(books, result);
    }

    // Test case for getting a specific bill by bill number
    @Test
    public void testGetBill() {
        // Arrange: Set up variables and mock behavior
        int billNo = 1;
        Bill bill = new Bill(new String[]{"1", "10"});
        when(logic.getBill(billNo)).thenReturn(bill);

        // Act: Call the getBill method
        Bill result = librarianModel.getBill(billNo);

        // Assert: Verify that getBill method is called once, and the result is the expected bill
        verify(logic, times(1)).getBill(billNo);
        assertEquals(bill, result);
    }

    // Test case for getting bill data in printable format for export
    @Test
    public void testGetBillDataForExport() {
        // Arrange: Set up variables and mock behavior
        int billNo = 1;
        String billData = "Bill Data";
        when(logic.getBillDataInPrintableFormat(billNo)).thenReturn(billData);

        // Act: Call the getBillDataForExport method
        String result = librarianModel.getBillDataForExport(billNo);

        // Assert: Verify that getBillDataInPrintableFormat method is called once, and the result is the expected bill data
        verify(logic, times(1)).getBillDataInPrintableFormat(billNo);
        assertEquals(billData, result);
    }

    // Test case for exporting bill data
    @Test
    public void testExportBillData() {
        // Arrange: Set up variables
        int billNo = 1;
        String data = "Bill Data";

        // Act: Call the exportBillData method
        librarianModel.exportBillData(billNo, data);

        // Assert: Verify that exportBill method is called once
        verify(logic, times(1)).exportBill(billNo, data);
    }
}
