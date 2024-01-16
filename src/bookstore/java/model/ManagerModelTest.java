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

    @Before
    public void setUp() {
        logic = mock(StoreLogic.class);
        gui = mock(GUI.class);
        managerModel = new ManagerModel(logic, gui);
    }

    @Test
    public void testLogout() {
        // Arrange

        // Act
        managerModel.logout();

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
        boolean result = managerModel.addBook(book);

        // Assert
        verify(logic, times(1)).getBook("0");
        verify(logic, times(1)).addBook(book);
        assertTrue(result);
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
        boolean result = managerModel.addStock(index, stock);

        // Assert
        verify(logic, never()).getBook(String.valueOf(index));
        verify(logic, never()).setStocksOf(anyString(), anyInt());
        assertFalse(result);
    }

    @Test
    public void testUpdateData() {
        // Arrange

        // Act
        managerModel.updateData();

        // Assert
        verify(logic, times(1)).saveBookData();
    }

    @Test
    public void testGetCurrUser() {
        // Arrange
        User user = new User(new String[]{"John Doe"});
        when(logic.getLoggedInUser()).thenReturn(user);

        // Act
        User result = managerModel.getCurrUser();

        // Assert
        verify(logic, times(1)).getLoggedInUser();
        assertEquals(user, result);
    }

    @Test
    public void testGetLibrarians() {
        // Arrange
        ArrayList<User> librarians = new ArrayList<>();
        librarians.add(new User(new String[]{"Librarian 1"}));
        librarians.add(new User(new String[]{"Librarian 2"}));
        when(logic.getLibrarians()).thenReturn(librarians);

        // Act
        ArrayList<User> result = managerModel.getLibrarians();

        // Assert
        verify(logic, times(1)).getLibrarians();
        assertEquals(librarians, result);
    }

    @Test
    public void testGetUser() {
        // Arrange
        ArrayList<User> librarians = new ArrayList<>();
        librarians.add(new User(new String[]{"Librarian 1"}));
        librarians.add(new User(new String[]{"Librarian 2"}));
        when(logic.getLibrarians()).thenReturn(librarians);

        // Act
        User result = managerModel.getUser(0);

        // Assert
        verify(logic, times(1)).getLibrarians();
        assertEquals(librarians.get(0), result);
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
        ArrayList<Bill> result = managerModel.getBills(user);

        // Assert
        verify(logic, times(1)).getAllBillsFor(user);
        assertEquals(bills, result);
    }

    @Test
    public void testGetBills() {
        // Arrange
        ArrayList<Bill> bills = new ArrayList<>();
        bills.add(new Bill(new String[] { "1", "10" }));
        bills.add(new Bill(new String[] { "2", "20" }));
        when(logic.getAllBills()).thenReturn(bills);

        // Act
        ArrayList<Bill> result = managerModel.getBills();

        // Assert
        verify(logic, times(1)).getAllBills();
        assertEquals(bills, result);
    }

    @Test
    public void testGetBook() {
        // Arrange
        ArrayList<Book> books = new ArrayList<>();
        books.add(
                new Book("Book Name", "1234567890", "Category", "Author", "Publisher", "01-01-2022", "15", "10", "20"));
        when(logic.getAllBooks()).thenReturn(books);

        // Act
        Book result = managerModel.getBook(0);

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
        ArrayList<Book> result = managerModel.getBooks();

        // Assert
        verify(logic, times(1)).getAllBooks();
        assertEquals(books, result);
    }
}