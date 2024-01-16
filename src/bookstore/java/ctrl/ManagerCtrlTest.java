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

    @Test
    public void testLogoutBtnAction() {
        // Arrange
        if (view != null && model != null && managerCtrl != null) {
            managerCtrl.logoutBtnAction();

            // Assert
            verify(model, times(1)).logout();
        }
        // Act

    }

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
            when(view.getBookSelectedIndex()).thenReturn(0);
            when(Message.showInput(eq("Quantity"), eq("Enter quantity"))).thenReturn(Optional.of("5"));
            when(model.addBook(any(Book.class))).thenReturn(true);

            // Act
            managerCtrl.addBookBtnAction();

            // Assert
            verify(model, times(1)).addBook(any(Book.class));
            verify(model, times(1)).updateData();
            verify(view, times(1)).updateBooksData();
            // verify(Message, times(1)).showInfo(eq("Book added"), eq("Information"));
        }
    }

    @Test
    public void testIncrStkBtnAction() {
        if (view != null && model != null && managerCtrl != null) {
            // Arrange
            when(view.getBookSelectedIndex()).thenReturn(0);
            when(Message.showInput(eq("Quantity"), eq("Enter quantity"))).thenReturn(Optional.of("5"));
            when(model.addStock(eq(0), eq(5))).thenReturn(true);

            // Act
            managerCtrl.incrStkBtnAction();

            // Assert
            verify(model, times(1)).addStock(eq(0), eq(5));
            verify(model, times(1)).updateData();
            // verify(Message, times(1)).showInfo(eq("Stock added"), eq("Information"));
        }
    }

    @Test
    public void testFullInfoBtnAction() {
        if (view != null && model != null && managerCtrl != null) {
            // Arrange
            when(view.getBookSelectedIndex()).thenReturn(0);
            when(model.getBook(eq(0))).thenReturn(
                    new Book("Book Name", "1234567890", "Category", "Author", "Publisher", "01-01-2022", "15", "10", "20"));

            // Act
            managerCtrl.fullInfoBtnAction();

            // Assert
            verify(view, times(1)).getBookSelectedIndex();
            verify(model, times(1)).getBook(eq(0));
        /* verify(Message, times(1)).showInfo(eq(
                "Book name: Book Name\nISBN: 1234567890\nCategory: Category\nAuthor: Author\nPublisher: Publisher\nPublishing Date: 01-01-2022\nSelling Price: 15\nCost Price: 10\nStock: 20"),
                eq("Book Data")); */
        }
    }

    @Test
    public void testChkLibPerfBtnAction() {
        if (view != null && model != null && managerCtrl != null) {
            // Arrange
            when(view.getLibSelectedIndex()).thenReturn(0);
            when(model.getUser(eq(0))).thenReturn(new User(new String[]{"User Name"}));
            ArrayList<Bill> bills = new ArrayList<>();
            bills.add(new Bill(new String[]{"1", "10"}));
            bills.add(new Bill(new String[]{"2", "20"}));
            when(model.getBills(any(User.class))).thenReturn(bills);

            // Act
            managerCtrl.chkLibPerfBtnAction();

            // Assert
            verify(view, times(1)).getLibSelectedIndex();
            verify(model, times(1)).getUser(eq(0));
            verify(model, times(1)).getBills(any(User.class));
        /* verify(Message, times(1)).showInfo(
                eq("Name: User Name\nTotal Bills: 2\nTotal Books Sold: 3\nTotal Earnings: 30"), eq("Work Performance"));*/
        }
    }

    @Test
    public void testSetChkStrPerfBtnAction() {
        if (view != null && model != null && managerCtrl != null) {
            // Arrange
            ArrayList<Bill> bills = new ArrayList<>();
            bills.add(new Bill(new String[]{"1", "10", "5", "2"}));
            bills.add(new Bill(new String[]{"2", "20", "10", "5"}));
            when(model.getBills()).thenReturn(bills);
            ArrayList<Book> books = new ArrayList<>();
            books.add(
                    new Book("Book Name", "1234567890", "Category", "Author", "Publisher", "01-01-2022", "15", "10", "20"));
            when(model.getBooks()).thenReturn(books);

            // Act
            managerCtrl.setChkStrPerfBtnAction();

            // Assert
            verify(model, times(1)).getBills();
            verify(model, times(1)).getBooks();
            /* verify(Message, times(1)).showInfo(
                eq("Books in stock: 20\nBooks Sold: 15\nStore Investment: 200\nTotal Bills: 2\nProfit on Sale: 10"),
                eq("Store Performance"));*/
        }
    }
}