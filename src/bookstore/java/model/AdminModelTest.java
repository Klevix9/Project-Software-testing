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

public class AdminModelTest {
    private AdminModel adminModel;
    private StoreLogic logic;
    private GUI gui;

// Test class for AdminModel
public class AdminModelTest {
private AdminModel adminModel;
private StoreLogic logic;
private GUI gui;

// Set up method to initialize objects before each test
@Before
public void setUp() {
    // Mock StoreLogic and GUI objects
    logic = mock(StoreLogic.class);
    gui = mock(GUI.class);
    // Create AdminModel object with mocked dependencies
    adminModel = new AdminModel(logic, gui);
}

// Test case for the logout method
@Test
public void testLogout() {
    // Act: Call the logout method
    adminModel.logout();

    // Assert: Verify that logOut and setLayoutToLogin methods are called once each
    verify(logic, times(1)).logOut();
    verify(gui, times(1)).setLayoutToLogin();
}

// Test case for adding a book
@Test
public void testAddBook() {
    // Arrange: Create a Book object and set up logic mock behavior
    Book book = new Book("1234567890", "Book Name", "Category", "Author", "Publisher", "01-01-2022", "10", "15", "20");
    when(logic.getBook("1234567890")).thenReturn(null);

    // Act: Call the addBook method
    boolean result = adminModel.addBook(book);

    // Assert: Verify that getBook and addBook methods are called once each, and result is true
    verify(logic, times(1)).getBook("0");
    verify(logic, times(1)).addBook(book);
    assertTrue(result);
}

// Test case for adding a user
@Test
public void testAddUser() {
    // Arrange: Create a User object and set up logic mock behavior
    User user = new User(new String[]{"John Doe"});
    when(logic.isUser(user.getPhoneNo())).thenReturn(false);

    // Act: Call the addUser method
    boolean result = adminModel.addUser(user);

    // Assert: Verify that isUser and addUser methods are called once each, and result is true
    verify(logic, times(1)).isUser(user.getPhoneNo());
    verify(logic, times(1)).addUser(user);
    assertTrue(result);
}

// Test case for adding a user when the user already exists
@Test
public void testAddUser_UserAlreadyExists() {
    // Arrange: Create a User object and set up logic mock behavior indicating the user already exists
    User user = new User(new String[]{"John Doe"});
    when(logic.isUser(user.getPhoneNo())).thenReturn(true);

    // Act: Call the addUser method
    boolean result = adminModel.addUser(user);

    // Assert: Verify that isUser is called once, but addUser is never called, and result is false
    verify(logic, times(1)).isUser(user.getPhoneNo());
    verify(logic, never()).addUser(user);
    assertFalse(result);
}

// Test case for editing a user
@Test
public void testEditUser() {
    // Arrange: Create a User object and set up logic mock behavior indicating the user exists
    User user = new User(new String[]{"John Doe"});
    when(logic.isUser(user.getPhoneNo())).thenReturn(true);

    // Act: Call the editUser method
    boolean result = adminModel.editUser(user);

    // Assert: Verify that isUser and editUser methods are called once each, and result is true
    verify(logic, times(1)).isUser(user.getPhoneNo());
    verify(logic, times(1)).editUser(user);
    assertTrue(result);
}

// Test case for editing a user that does not exist
@Test
public void testEditUser_UserDoesNotExist() {
    // Arrange: Create a User object and set up logic mock behavior indicating the user does not exist
    User user = new User(new String[]{"John Doe"});
    when(logic.isUser(user.getPhoneNo())).thenReturn(false);

    // Act: Call the editUser method
    boolean result = adminModel.editUser(user);

    // Assert: Verify that isUser is called once, but editUser is never called, and result is false
    verify(logic, times(1)).isUser(user.getPhoneNo());
    verify(logic, never()).editUser(user);
    assertFalse(result);
}

// Test case for adding stock to a book
@Test
public void testAddStock() {
    // Arrange: Set up variables and mock behavior
    int index = 0;
    int stock = 5;
    Book book = new Book("1234567890", "Book Name", "Category", "Author", "Publisher", "01-01-2022", "10", "15", "20");
    when(logic.getBook(index+"")).thenReturn(book);

    // Check if the books list is not empty
    if(!adminModel.getBooks().isEmpty()) {
        // Act: Call the addStock method
        boolean result = adminModel.addStock(index, stock);

        // Assert: Verify that getBook and setStocksOf methods are called once each, and result is true
        verify(logic, times(1)).getBook(index + "");
        verify(logic, times(1)).setStocksOf("1234567890", 25);
        assertTrue(result);
    }
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
        boolean result = adminModel.addStock(index, stock);

        // Assert: Verify that getBook and setStocksOf methods are never called, and result is false
        verify(logic, never()).getBook(String.valueOf(index));
        verify(logic, never()).setStocksOf(anyString(), anyInt());
        assertFalse(result);
    }

    // Test case for checking out a book
    @Test
    public void testCheckOutBook() {
        // Arrange: Set up variables and mock behavior
        String isbn = "1234567890";
        int quantity = 2;
        when(logic.addBill(anyString(), anyString(), anyInt())).thenReturn(1);

        // Check if the current user is not null
        if (adminModel.getCurrUser() != null) {
            // Act: Call the checkOutBook method
            int result = adminModel.checkOutBook(isbn, quantity);

            // Assert: Verify that addBill method is called once, and the result is 1
            verify(logic, times(1)).addBill(anyString(), anyString(), anyInt());
            assertEquals(1, result);
        }
    }

    // Test case for firing an employee
    @Test
    public void testFireEmployee() {
        // Arrange: Set up variables and mock behavior
        int index = 0;
        User employee = new User(new String[]{"John Doe"});

        // Check if the employees list is not empty
        if (!adminModel.getEmployees().isEmpty()) {
            when(adminModel.getEmployee(index)).thenReturn(employee);

            // Act: Call the fireEmployee method
            adminModel.fireEmployee(index);

            // Assert: Verify that getEmployee and fireUser methods are called once each
            verify(adminModel, times(1)).getEmployee(index);
            verify(logic, times(1)).fireUser(employee.getPhoneNo());
        }
    }

    // Test case for updating data
    @Test
    public void testUpdateData() {
        // Act: Call the updateData method
        adminModel.updateData();

        // Assert: Verify that saveBookData, saveBillsData, and saveUserData methods are called once each
        verify(logic, times(1)).saveBookData();
        verify(logic, times(1)).saveBillsData();
        verify(logic, times(1)).saveUserData();
    }

    // Test case for exporting bill data
    @Test
    public void testExportBillData() {
        // Arrange: Set up variables
        int billNo = 1;
        String data = "Bill data";

        // Act: Call the exportBillData method
        adminModel.exportBillData(billNo, data);

        // Assert: Verify that exportBill method is called once
        verify(logic, times(1)).exportBill(billNo, data);
    }

    // Test case for getting the current user
    @Test
    public void testGetCurrUser() {
        // Arrange: Set up variables and mock behavior
        User user = new User(new String[]{"John Doe"});
        when(logic.getLoggedInUser()).thenReturn(user);

        // Act: Call the getCurrUser method
        User result = adminModel.getCurrUser();

        // Assert: Verify that getLoggedInUser method is called once, and the result is the expected user
        verify(logic, times(1)).getLoggedInUser();
        assertEquals(user, result);
    }

    // Test case for getting the list of employees
    @Test
    public void testGetEmployees() {
        // Arrange: Set up variables and mock behavior
        ArrayList<User> employees = new ArrayList<>();
        employees.add(new User(new String[]{"Employee 1"}));
        employees.add(new User(new String[]{"Employee 2"}));
        when(logic.getEmployees()).thenReturn(employees);

        // Act: Call the getEmployees method
        ArrayList<User> result = adminModel.getEmployees();

        // Assert: Verify that getEmployees method is called once, and the result is the expected list of employees
        verify(logic, times(1)).getEmployees();
        assertEquals(employees, result);
    }

    // Test case for getting an employee by index
    @Test
    public void testGetEmployee() {
        // Arrange: Set up variables and mock behavior
        ArrayList<User> employees = new ArrayList<>();
        employees.add(new User(new String[]{"Employee 1"}));
        employees.add(new User(new String[]{"Employee 2"}));
        when(adminModel.getEmployees()).thenReturn(employees);

        // Act: Call the getEmployee method

        // Assert: Verify that getEmployees method is never called
        verify(adminModel, never()).getEmployees();
    }

    // Test case for getting a book by index
    @Test
    public void testGetBook() {
        // Arrange: Set up variables and mock behavior
        ArrayList<Book> books = new ArrayList<>();
        books.add(new Book("Book Name", "1234567890", "Category", "Author", "Publisher", "01-01-2022", "15", "10", "20"));
        when(logic.getAllBooks()).thenReturn(books);

        // Act: Call the getBook method
        Book result = adminModel.getBook(0);

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
        ArrayList<Book> result = adminModel.getBooks();

        // Assert: Verify that getAllBooks method is called once, and the result is the expected list of books
        verify(logic, times(1)).getAllBooks();
        assertEquals(books, result);
    }

    // Test case for getting the list of all bills
    @Test
    public void testGetBills() {
        // Arrange: Set up variables and mock behavior
        ArrayList<Bill> bills = new ArrayList<>();
        bills.add(new Bill(new String[]{"1", "10"}));
        bills.add(new Bill(new String[]{"2", "20"}));
        when(logic.getAllBills()).thenReturn(bills);

        // Act: Call the getBills method
        ArrayList<Bill> result = adminModel.getBills();

        // Assert: Verify that getAllBills method is called once, and the result is the expected list of bills
        verify(logic, times(1)).getAllBills();
        assertEquals(bills, result);
    }

    // Test case for getting the list of bills for a specific user
    @Test
    public void testGetBills_User() {
        // Arrange: Set up variables and mock behavior
        User user = new User(new String[]{"John Doe"});
        ArrayList<Bill> bills = new ArrayList<>();
        bills.add(new Bill(new String[]{"1", "10"}));
        bills.add(new Bill(new String[]{"2", "20"}));
        when(logic.getAllBillsFor(user)).thenReturn(bills);

        // Act: Call the getBills method with a user parameter
        ArrayList<Bill> result = adminModel.getBills(user);

        // Assert: Verify that getAllBillsFor method is called once, and the result is the expected list of bills
        verify(logic, times(1)).getAllBillsFor(user);
        assertEquals(bills, result);
    }

    // Test case for getting a specific bill by bill number
    @Test
    public void testGetBill() {
        // Arrange: Set up variables and mock behavior
        int billNo = 1;
        Bill bill = new Bill(new String[]{"1", "10"});
        when(logic.getBill(billNo)).thenReturn(bill);

        // Act: Call the getBill method
        Bill result = adminModel.getBill(billNo);

        // Assert: Verify that getBill method is called once, and the result is the expected bill
        verify(logic, times(1)).getBill(billNo);
        assertEquals(bill, result);
    }

    // Test case for getting bill data in printable format for export
    @Test
    public void testGetBillDataForExport() {
        // Arrange: Set up variables and mock behavior
        int billNo = 1;
        String billData = "Bill data";
        when(logic.getBillDataInPrintableFormat(billNo)).thenReturn(billData);

        // Act: Call the getBillDataForExport method
        String result = adminModel.getBillDataForExport(billNo);

        // Assert: Verify that getBillDataInPrintableFormat method is called once, and the result is the expected bill data
        verify(logic, times(1)).getBillDataInPrintableFormat(billNo);
        assertEquals(billData, result);
    }
}
