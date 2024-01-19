package bookstore.java.model;

import bookstore.java.cmp.Book;
import bookstore.java.cmp.User;
import bookstore.java.cmp.Bill;
import bookstore.java.StoreLogic;
import bookstore.java.GUI;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class ManagerModelTest {
    private ManagerModel managerModel;
    private StoreLogic logic;
    private GUI gui;

    // Set up method to initialize objects before each test
    @Before
    public void setUp() {
        // Mock StoreLogic and GUI objects
        logic = mock(StoreLogic.class);
        gui = mock(GUI.class);
        // Create ManagerModel object with mocked dependencies
        managerModel = new ManagerModel(logic, gui);
    }

    // Test case for the logout method
    @Test
    public void testLogout() {
        // Act: Call the logout method
        managerModel.logout();

        // Assert: Verify that logOut and setLayoutToLogin methods are called once each
        verify(logic, times(1)).logOut();
        verify(gui, times(1)).setLayoutToLogin();
    }

    // Test case for adding a book
    @Test
    public void testAddBook() {
        // Arrange: Set up variables and mock behavior
        Book book = new Book("1234567890", "Book Name", "Category", "Author", "Publisher", "01-01-2022", "10", "15", "20");
        when(logic.getBook("1234567890")).thenReturn(null);

        // Act: Call the addBook method
        boolean result = managerModel.addBook(book);

        // Assert: Verify that getBook and addBook methods are called once each, and the result is true
        verify(logic, times(1)).getBook("0");
        verify(logic, times(1)).addBook(book);
        assertTrue(result);
    }

    // Test case for adding stock with invalid stock value
    @Test
    public void testAddStock_InvalidStock() {
        // Arrange: Set up variables and mock behavior
        int index = 0;
        int stock = -5;
        Book book = new Book("1234567890", "Book Name", "Category", "Author", "Publisher", "01-01-2022", "10", "15", "20");
        when(logic.getBook(String.valueOf(index))).thenReturn(book);

        // Act: Call the addStock method
        boolean result = managerModel.addStock(index, stock);

        // Assert: Verify that getBook and setStocksOf methods are never called, and result is false
        verify(logic, never()).getBook(String.valueOf(index));
        verify(logic, never()).setStocksOf(anyString(), anyInt());
        assertFalse(result);
    }

    // Test case for updating data
    @Test
    public void testUpdateData() {
        // Act: Call the updateData method
        managerModel.updateData();

        // Assert: Verify that saveBookData method is called once
        verify(logic, times(1)).saveBookData();
    }

    // Test case for getting the current user
    @Test
    public void testGetCurrUser() {
        // Arrange: Set up variables and mock behavior
        User user = new User(new String[]{"John Doe"});
        when(logic.getLoggedInUser()).thenReturn(user);

        // Act: Call the getCurrUser method
        User result = managerModel.getCurrUser();

        // Assert: Verify that getLoggedInUser method is called once, and the result is the expected user
        verify(logic, times(1)).getLoggedInUser();
        assertEquals(user, result);
    }

    // Test case for getting the list of librarians
    @Test
    public void testGetLibrarians() {
        // Arrange: Set up variables and mock behavior
        ArrayList<User> librarians = new ArrayList<>();
        librarians.add(new User(new String[]{"Librarian 1"}));
        librarians.add(new User(new String[]{"Librarian 2"}));
        when(logic.getLibrarians()).thenReturn(librarians);

        // Act: Call the getLibrarians method
        ArrayList<User> result = managerModel.getLibrarians();

        // Assert: Verify that getLibrarians method is called once, and the result is the expected list of librarians
        verify(logic, times(1)).getLibrarians();
        assertEquals(librarians, result);
    }

    // Test case for getting a user by index
    @Test
    public void testGetUser() {
        // Arrange: Set up variables and mock behavior
        ArrayList<User> librarians = new ArrayList<>();
        librarians.add(new User(new String[]{"Librarian 1"}));
        librarians.add(new User(new String[]{"Librarian 2"}));
        when(logic.getLibrarians()).thenReturn(librarians);

        // Act: Call the getUser method
        User result = managerModel.getUser(0);

        // Assert: Verify that getLibrarians method is called once, and the result is the expected user
        verify(logic, times(1)).getLibrarians();
        assertEquals(librarians.get(0), result);
    }

    // Test case for getting bills for a specific user
    @Test
    public void testGetBills_User() {
        // Arrange: Set up variables and mock behavior
        User user = new User(new String[]{"John Doe"});
        ArrayList<Bill> bills = new ArrayList<>();
        bills.add(new Bill(new String[]{"1", "10"}));
        bills.add(new Bill(new String[]{"2", "20"}));
        when(logic.getAllBillsFor(user)).thenReturn(bills);

        // Act: Call the getBills method for a specific user
        ArrayList<Bill> result = managerModel.getBills(user);

        // Assert: Verify that getAllBillsFor method is called once, and the result is the expected list of bills
        verify(logic, times(1)).getAllBillsFor(user);
        assertEquals(bills, result);
    }

    // Test case for getting all bills
    @Test
    public void testGetBills() {
        // Arrange: Set up variables and mock behavior
        ArrayList<Bill> bills = new ArrayList<>();
        bills.add(new Bill(new String[]{"1", "10"}));
        bills.add(new Bill(new String[]{"2", "20"}));
        when(logic.getAllBills()).thenReturn(bills);

        // Act: Call the getBills method for all users
        ArrayList<Bill> result = managerModel.getBills();

        // Assert: Verify that getAllBills method is called once, and the result is the expected list of bills
        verify(logic, times(1)).getAllBills();
        assertEquals(bills, result);
    }

    // Test case for getting a book by index
    @Test
    public void testGetBook() {
        // Arrange: Set up variables and mock behavior
        ArrayList<Book> books = new ArrayList<>();
        books.add(new Book("Book Name", "1234567890", "Category", "Author", "Publisher", "01-01-2022", "15", "10", "20"));
        when(logic.getAllBooks()).thenReturn(books);

        // Act: Call the getBook method
        Book result = managerModel.getBook(0);

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
        ArrayList<Book> result = managerModel.getBooks();

        // Assert: Verify that getAllBooks method is called once, and the result is the expected list of books
        verify(logic, times(1)).getAllBooks();
        assertEquals(books, result);
    }
}
