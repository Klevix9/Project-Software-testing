package bookstore.java.model;

import bookstore.java.cmp.Book;
import bookstore.java.cmp.Bill;
import bookstore.java.GUI;
import bookstore.java.StoreLogic;
import bookstore.java.cmp.User;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class LibrarianModelTest {
    private LibrarianModel librarianModel;
    private StoreLogic logic;
    private GUI gui;

    @Before
    public void setUp() {
        logic = mock(StoreLogic.class);
        gui = mock(GUI.class);
        librarianModel = new LibrarianModel(logic, gui);
    }

    @Test
    public void testLogout() {
        // Arrange

        // Act
        librarianModel.logout();

        // Assert
        verify(logic, times(1)).logOut();
        verify(gui, times(1)).setLayoutToLogin();
    }

    @Test
    public void testUpdateData() {
        // Arrange

        // Act
        librarianModel.updateData();

        // Assert
        verify(logic, times(1)).saveBillsData();
        verify(logic, times(1)).saveBookData();
    }

    @Test
    public void testGetCurrUser() {
        // Arrange
        User user = new User(new String[]{"John Doe"});
        when(logic.getLoggedInUser()).thenReturn(user);

        // Act
        User result = librarianModel.getCurrUser();

        // Assert
        verify(logic, times(1)).getLoggedInUser();
        assertEquals(user, result);
    }

    @Test
    public void testGetBook() {
        // Arrange
        ArrayList<Book> books = new ArrayList<>();
        books.add(
                new Book("Book Name", "1234567890", "Category", "Author", "Publisher", "01-01-2022", "15", "10", "20"));
        when(logic.getAllBooks()).thenReturn(books);

        // Act
        Book result = librarianModel.getBook(0);

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
        ArrayList<Book> result = librarianModel.getBooks();

        // Assert
        verify(logic, times(1)).getAllBooks();
        assertEquals(books, result);
    }

    @Test
    public void testGetBill() {
        // Arrange
        int billNo = 1;
        Bill bill = new Bill(new String[] { "1", "10" });
        when(logic.getBill(billNo)).thenReturn(bill);

        // Act
        Bill result = librarianModel.getBill(billNo);

        // Assert
        verify(logic, times(1)).getBill(billNo);
        assertEquals(bill, result);
    }

    @Test
    public void testGetBillDataForExport() {
        // Arrange
        int billNo = 1;
        String billData = "Bill Data";
        when(logic.getBillDataInPrintableFormat(billNo)).thenReturn(billData);

        // Act
        String result = librarianModel.getBillDataForExport(billNo);

        // Assert
        verify(logic, times(1)).getBillDataInPrintableFormat(billNo);
        assertEquals(billData, result);
    }

    @Test
    public void testExportBillData() {
        // Arrange
        int billNo = 1;
        String data = "Bill Data";

        // Act
        librarianModel.exportBillData(billNo, data);

        // Assert
        verify(logic, times(1)).exportBill(billNo, data);
    }
}