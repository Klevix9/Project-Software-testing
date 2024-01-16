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

    @Before
    public void setUp() {
        logic = mock(StoreLogic.class);
        gui = mock(GUI.class);
        adminModel = new AdminModel(logic, gui);
    }

    @Test
    public void testLogout() {
        // Arrange

        // Act
        adminModel.logout();

        // Assert
        verify(logic, times(1)).logOut();
        verify(gui, times(1)).setLayoutToLogin();
    }

    @Test
    public void testAddBook() {
        // Arrange
        Book book = new Book("1234567890", "Book Name", "Category", "Author", "Publisher", "01-01-2022", "10", "15",
                "20");
        when(logic.getBook("1234567890")).thenReturn(null);

        // Act
        boolean result = adminModel.addBook(book);

        // Assert
        verify(logic, times(1)).getBook("0");
        verify(logic, times(1)).addBook(book);
        assertTrue(result);
    }

    @Test
    public void testAddUser() {
        // Arrange
        User user = new User(new String[]{"John Doe"});
        when(logic.isUser(user.getPhoneNo())).thenReturn(false);

        // Act
        boolean result = adminModel.addUser(user);

        // Assert
        verify(logic, times(1)).isUser(user.getPhoneNo());
        verify(logic, times(1)).addUser(user);
        assertTrue(result);
    }

    @Test
    public void testAddUser_UserAlreadyExists() {
        // Arrange
        User user = new User(new String[]{"John Doe"});
        when(logic.isUser(user.getPhoneNo())).thenReturn(true);

        // Act
        boolean result = adminModel.addUser(user);

        // Assert
        verify(logic, times(1)).isUser(user.getPhoneNo());
        verify(logic, never()).addUser(user);
        assertFalse(result);
    }

    @Test
    public void testEditUser() {
        // Arrange
        User user = new User(new String[]{"John Doe"});
        when(logic.isUser(user.getPhoneNo())).thenReturn(true);

        // Act
        boolean result = adminModel.editUser(user);

        // Assert
        verify(logic, times(1)).isUser(user.getPhoneNo());
        verify(logic, times(1)).editUser(user);
        assertTrue(result);
    }

    @Test
    public void testEditUser_UserDoesNotExist() {
        // Arrange
        User user = new User(new String[]{"John Doe"});
        when(logic.isUser(user.getPhoneNo())).thenReturn(false);

        // Act
        boolean result = adminModel.editUser(user);

        // Assert
        verify(logic, times(1)).isUser(user.getPhoneNo());
        verify(logic, never()).editUser(user);
        assertFalse(result);
    }

    @Test
    public void testAddStock() {
        // Arrange
        int index = 0;
        int stock = 5;
        Book book = new Book("1234567890", "Book Name", "Category", "Author", "Publisher", "01-01-2022", "10", "15",
                "20");
        when(logic.getBook(index+"")).thenReturn(book);
        if(! adminModel.getBooks().isEmpty()) {
            // Act
            boolean result = adminModel.addStock(index, stock);

            // Assert
            verify(logic, times(1)).getBook(index + "");
            verify(logic, times(1)).setStocksOf("1234567890", 25);
            assertTrue(result);
        }
    }

    @Test
    public void testAddStock_InvalidStock() {
        // Arrange
        int index = 0;
        int stock = -5;
        Book book = new Book("1234567890", "Book Name", "Category", "Author", "Publisher", "01-01-2022", "10", "15",
                "20");
        when(logic.getBook(String.valueOf(index))).thenReturn(book);

        // Act
        boolean result = adminModel.addStock(index, stock);

        // Assert
        verify(logic, never()).getBook(String.valueOf(index));
        verify(logic, never()).setStocksOf(anyString(), anyInt());
        assertFalse(result);
    }

    @Test
    public void testCheckOutBook() {
        // Arrange
        String isbn = "1234567890";
        int quantity = 2;
        when(logic.addBill(anyString(), anyString(), anyInt())).thenReturn(1);
        if (adminModel.getCurrUser()!=null) {
            // Act
            int result = adminModel.checkOutBook(isbn, quantity);

            // Assert
            verify(logic, times(1)).addBill(anyString(), anyString(), anyInt());
            assertEquals(1, result);
        }
    }

    @Test
    public void testFireEmployee() {
        // Arrange
        int index = 0;
        User employee = new User(new String[]{"John Doe"});
        if (!adminModel.getEmployees().isEmpty()) {
            when(adminModel.getEmployee(index)).thenReturn(employee);

            // Act
            adminModel.fireEmployee(index);

            // Assert
            verify(adminModel, times(1)).getEmployee(index);
            verify(logic, times(1)).fireUser(employee.getPhoneNo());
        }
    }

    @Test
    public void testUpdateData() {
        // Arrange

        // Act
        adminModel.updateData();

        // Assert
        verify(logic, times(1)).saveBookData();
        verify(logic, times(1)).saveBillsData();
        verify(logic, times(1)).saveUserData();
    }

    @Test
    public void testExportBillData() {
        // Arrange
        int billNo = 1;
        String data = "Bill data";

        // Act
        adminModel.exportBillData(billNo, data);

        // Assert
        verify(logic, times(1)).exportBill(billNo, data);
    }

    @Test
    public void testGetCurrUser() {
        // Arrange
        User user = new User(new String[]{"John Doe"});
        when(logic.getLoggedInUser()).thenReturn(user);

        // Act
        User result = adminModel.getCurrUser();

        // Assert
        verify(logic, times(1)).getLoggedInUser();
        assertEquals(user, result);
    }

    @Test
    public void testGetEmployees() {
        // Arrange
        ArrayList<User> employees = new ArrayList<>();
        employees.add(new User(new String[]{"Employee 1"}));
        employees.add(new User(new String[]{"Employee 2"}));
        when(logic.getEmployees()).thenReturn(employees);

        // Act
        ArrayList<User> result = adminModel.getEmployees();

        // Assert
        verify(logic, times(1)).getEmployees();
        assertEquals(employees, result);
    }

    @Test
    public void testGetEmployee() {

        logic = mock(StoreLogic.class);
        gui = mock(GUI.class);
        adminModel = mock(AdminModel.class);
        // Arrange
        ArrayList<User> employees = new ArrayList<>();
        employees.add(new User(new String[]{"Employee 1"}));
        employees.add(new User(new String[]{"Employee 2"}));
        when(adminModel.getEmployees()).thenReturn(employees);


        // Assert
        verify(adminModel, never()).getEmployees();
    }

    @Test
    public void testGetBook() {
        // Arrange
        ArrayList<Book> books = new ArrayList<>();
        books.add(
                new Book("Book Name", "1234567890", "Category", "Author", "Publisher", "01-01-2022", "15", "10", "20"));
        when(logic.getAllBooks()).thenReturn(books);

        // Act
        Book result = adminModel.getBook(0);

        // Assert
        verify(logic, times(1)).getAllBooks();
        assertEquals(books.get(0), result);
    }

    @Test
    public void testGetBooks() {
        // Arrange
        ArrayList<Book> books = new ArrayList<>();
        books.add(
                new Book("Book Name", "1234567890", "Category", "Author", "Publisher", "01-01-2022", "15", "10", "20"));
        when(logic.getAllBooks()).thenReturn(books);

        // Act
        ArrayList<Book> result = adminModel.getBooks();

        // Assert
        verify(logic, times(1)).getAllBooks();
        assertEquals(books, result);
    }

    @Test
    public void testGetBills() {
        // Arrange
        ArrayList<Bill> bills = new ArrayList<>();
        bills.add(new Bill(new String[] { "1", "10" }));
        bills.add(new Bill(new String[] { "2", "20" }));
        when(logic.getAllBills()).thenReturn(bills);

        // Act
        ArrayList<Bill> result = adminModel.getBills();

        // Assert
        verify(logic, times(1)).getAllBills();
        assertEquals(bills, result);
    }

    @Test
    public void testGetBills_User() {
        // Arrange
        User user = new User(new String[]{"John Doe"});
        ArrayList<Bill> bills = new ArrayList<>();
        bills.add(new Bill(new String[] { "1", "10" }));
        bills.add(new Bill(new String[] { "2", "20" }));
        when(logic.getAllBillsFor(user)).thenReturn(bills);

        // Act
        ArrayList<Bill> result = adminModel.getBills(user);

        // Assert
        verify(logic, times(1)).getAllBillsFor(user);
        assertEquals(bills, result);
    }

    @Test
    public void testGetBill() {
        // Arrange
        int billNo = 1;
        Bill bill = new Bill(new String[] { "1", "10" });
        when(logic.getBill(billNo)).thenReturn(bill);

        // Act
        Bill result = adminModel.getBill(billNo);

        // Assert
        verify(logic, times(1)).getBill(billNo);
        assertEquals(bill, result);
    }

    @Test
    public void testGetBillDataForExport() {
        // Arrange
        int billNo = 1;
        String billData = "Bill data";
        when(logic.getBillDataInPrintableFormat(billNo)).thenReturn(billData);

        // Act
        String result = adminModel.getBillDataForExport(billNo);

        // Assert
        verify(logic, times(1)).getBillDataInPrintableFormat(billNo);
        assertEquals(billData, result);
    }
}