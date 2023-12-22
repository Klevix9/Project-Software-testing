import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class ManagerCtrlTest {

    private ManagerModelStub model;
    private ManagerViewStub view;
    private ManagerCtrl managerCtrl;

    @Before
    public void setUp() {
        model = new ManagerModelStub();
        view = new ManagerViewStub();
        managerCtrl = new ManagerCtrl(model, view);
    }

    @Test
    public void testLogoutBtnAction() {
        managerCtrl.logoutBtnAction();
        assertTrue(model.isLogoutCalled());
    }

    @Test
    public void testAddBookBtnAction() {
        // Stubbing data
        view.setAddBookDialogResult(Optional.of(Arrays.asList("123456789", "BookName", "Category", "Author", "01-01-2022", "10.5", "15.5", "Description", "50")));

        // Triggering the method
        managerCtrl.addBookBtnAction();

        // Verifying that the model's addBook method is called
        assertTrue(model.isAddBookCalled());
        // Verifying that the view's updateBooksData method is called
        assertTrue(view.isUpdateBooksDataCalled());
    }

    @Test
    public void testIncrStkBtnAction() {
        // Stubbing data
        view.setBookSelectedIndex(0);
        view.setShowInputResult(Optional.of("10"));

        // Triggering the method
        managerCtrl.incrStkBtnAction();

        // Verifying that the model's addStock method is called
        assertTrue(model.isAddStockCalled());
        // Verifying that the view's updateBooksData method is called
        assertTrue(view.isUpdateBooksDataCalled());
    }

    @Test
    public void testFullInfoBtnAction() {
        // Stubbing data
        view.setBookSelectedIndex(0);
        model.setBookToReturn(new Book("123456789", "BookName", "Category", "Author", "01-01-2022", "10.5", "15.5", "Description", "50"));

        // Triggering the method
        managerCtrl.fullInfoBtnAction();

        // Verifying that the Message.showInfo method is called
        assertTrue(view.isShowInfoCalled());
    }

    @Test
    public void testChkLibPerfBtnAction() {
        // Stubbing data
        view.setLibSelectedIndex(0);
        model.setUserToReturn(new User("LibrarianName", "librarian@example.com", "password", User.AccessLevel.LIBRARIAN));
        model.setBillsToReturn(Arrays.asList(new Bill(1, 5, 10)));

        // Triggering the method
        managerCtrl.chkLibPerfBtnAction();

        // Verifying that the Message.showInfo method is called
        assertTrue(view.isShowInfoCalled());
    }

    @Test
    public void testSetChkStrPerfBtnAction() {
        // Stubbing data
        model.setBillsToReturn(Arrays.asList(new Bill(1, 5, 10)));
        model.setBooksToReturn(Arrays.asList(new Book("123456789", "BookName", "Category", "Author", "01-01-2022", "10.5", "15.5", "Description", "50")));

        // Triggering the method
        managerCtrl.setChkStrPerfBtnAction();

        // Verifying that the Message.showInfo method is called
        assertTrue(view.isShowInfoCalled());
    }

    // Add more tests for boundary and equivalence cases as needed

    // Stub class for ManagerModel
    private static class ManagerModelStub extends ManagerModel {
        private boolean logoutCalled;
        private boolean addBookCalled;
        private boolean addStockCalled;
        private boolean updateDataCalled;
        private User userToReturn;
        private List<Bill> billsToReturn;
        private List<Book> booksToReturn;

        public ManagerModelStub() {
            super(null, null);
        }

        public boolean isLogoutCalled() {
            return logoutCalled;
        }

        public boolean isAddBookCalled() {
            return addBookCalled;
        }

        public boolean isAddStockCalled() {
            return addStockCalled;
        }

        public boolean isUpdateDataCalled() {
            return updateDataCalled;
        }

        public void setUserToReturn(User user) {
            this.userToReturn = user;
        }

        public void setBillsToReturn(List<Bill> bills) {
            this.billsToReturn = bills;
        }

        public void setBooksToReturn(List<Book> books) {
            this.booksToReturn = books;
        }

        @Override
        public void logout() {
            logoutCalled = true;
        }

        @Override
        public boolean addBook(Book book) {
            addBookCalled = true;
            return super.addBook(book);
        }

        @Override
        public boolean addStock(int index, int stock) {
            addStockCalled = true;
            return super.addStock(index, stock);
        }

        @Override
        public void updateData() {
            updateDataCalled = true;
        }

        @Override
        public User getUser(int index) {
            return userToReturn;
        }

        @Override
        public List<Bill> getBills(User user) {
            return billsToReturn;
        }

        @Override
        public List<Bill> getAllBills() {
            return billsToReturn;
        }

        @Override
        public Book getBook(int index) {
            return booksToReturn.get(index);
        }

        @Override
        public List<Book> getAllBooks() {
            return booksToReturn;
        }
    }

    // Stub class for ManagerView
    private static class ManagerViewStub extends ManagerView {
        private boolean showInfoCalled;
        private boolean updateBooksDataCalled;
        private int bookSelectedIndex;
        private int libSelectedIndex;
        private Optional<List<String>> addBookDialogResult;
        private Optional<String> showInputResult;

        public ManagerViewStub() {
            super(null);
        }

        public boolean isShowInfoCalled() {
            return showInfoCalled;
        }

        public boolean isUpdateBooksDataCalled() {
            return updateBooksDataCalled;
        }

        public void setBookSelectedIndex(int index) {
            this.bookSelectedIndex = index;
        }

        public void setLibSelectedIndex(int index) {
            this.libSelectedIndex = index;
        }

        public void setShowInputResult(Optional<String> result) {
            this.showInputResult = result;
        }

        public void setAddBookDialogResult(Optional<List<String>> result) {
            this.addBookDialogResult = result;
        }

        @Override
        public void updateBooksData() {
            updateBooksDataCalled = true;
        }

        @Override
        public int getBookSelectedIndex() {
            return bookSelectedIndex;
        }

        @Override
        public int getLibSelectedIndex() {
            return libSelectedIndex;
        }

        @Override
        public Optional<List<String>> showAddBookDialog
