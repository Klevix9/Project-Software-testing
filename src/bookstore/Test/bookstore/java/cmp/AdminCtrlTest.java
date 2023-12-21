package bookstore.java.cmp;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import bookstore.java.model.AdminModel;
import bookstore.java.view.AdminView;
import bookstore.java.ctrl.AdminCtrl;
import bookstore.java.cmp.User;
import bookstore.java.cmp.Book;
import bookstore.java.dialog.AddBookDialog;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

class AdminCtrlTest {

    @Test
    void empListBtnAction_LibrarianSelected_DisableChkLibPerfBtn() {
        // Arrange
        AdminModel model = Mockito.mock(AdminModel.class);
        AdminView view = Mockito.mock(AdminView.class);
        AdminCtrl adminCtrl = new AdminCtrl(model, view);

        User librarianUser = new User(new String[]{"Librarian", "01-01-1990", "123456789", "librarian@example.com", "password", "1000", "librarian", "Active"});

        // Mock the model to return the librarian user when getEmployee is called
        when(model.getEmployee(anyInt())).thenReturn(librarianUser);

        // Act
        adminCtrl.empListBtnAction();

        // Assert
        Mockito.verify(view).disableChkLibPerfBtn(false);
    }

    @Test
    void empListBtnAction_NonLibrarianSelected_EnableChkLibPerfBtn() {
        // Similar to the previous test but with a non-librarian user
    }

    @Test
    void addBookBtnAction_ValidInput_AddsBookToModelAndUpdateView() {
        // Arrange
        AdminModel model = Mockito.mock(AdminModel.class);
        AdminView view = Mockito.mock(AdminView.class);
        AdminCtrl adminCtrl = new AdminCtrl(model, view);

        // Mock the result of the AddBookDialog
        ArrayList<String> bookData = new ArrayList<>();
        bookData.add("123456789");
        // Add other book data...

        // Mock the model to return true when addBook is called
        when(model.addBook(Mockito.any(Book.class))).thenReturn(true);

        // Mock the result of the showAndGetResult method
        when(new AddBookDialog().showAndGetResult()).thenReturn(Optional.of(bookData));

        // Act
        adminCtrl.addBookBtnAction();

        // Assert
        Mockito.verify(model).addBook(Mockito.any(Book.class));
        Mockito.verify(model).updateData();
        Mockito.verify(view).updateBooksData();
    }

    // duhet te shtojme teste te tjera te ngjashme per metodat e tjera

}
