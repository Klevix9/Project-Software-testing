package bookstore.java.cmp;

import bookstore.java.cmp.Access;
import bookstore.java.cmp.User;
import org.junit.Before;
import org.junit.Test;

import static org.testng.AssertJUnit.assertEquals;

public class UserTest {

    private User user;

    @Before
    public void setUp() {
        String[] userData = {"John Doe", "1990-01-01", "1234567890", "john.doe@example.com", "password", "5000", "admin", "active"};
        user = new User(userData);
    }

    @Test
    public void testGetName() {
        assertEquals("John Doe", user.getName());
    }

    @Test
    public void testGetSalary() {
        assertEquals(5000, user.getSalary());
    }

    @Test
    public void testGetAccessLevel() {
        assertEquals(Access.LIBRARIAN, user.getAccessLevel());
    }

    @Test
    public void testToString() {
        String expected = "Name: John Doe, Salary: 5000, Access Level: librarian";
        assertEquals(expected, user.toString());
    }

    @Test
    public void testGetAllData() {
        String expected = "John Doe,1990-01-01,1234567890,john.doe@example.com,password,5000,librarian,active";
        assertEquals(expected, user.getAllData());
    }

    @Test
    public void testSetName() {
        user.setName("Jane Smith");
        assertEquals("Jane Smith", user.getName());
    }

    @Test
    public void testSetSalary() {
        user.setSalary(6000);
        assertEquals(6000, user.getSalary());
    }

    @Test
    public void testSetAccessLevel() {
        user.setAccessLevel(Access.MANAGER);
        assertEquals(Access.MANAGER, user.getAccessLevel());
    }

    @Test
    public void testSetStatus() {
        user.setStatus("inactive");
        assertEquals("inactive", user.getStatus());
    }
}