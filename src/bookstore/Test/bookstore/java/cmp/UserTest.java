package bookstore.java.cmp;

import bookstore.java.cmp.Access;
import bookstore.java.cmp.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserTest {

    @Test
    void constructorShouldSetFieldsCorrectly() {
        String[] userData = {"John Doe", "1990-01-01", "123456789", "john@example.com", "password", "50000", "admin", "active"};

        User user = new User(userData);

        assertEquals("John Doe", user.getName());
        assertEquals("1990-01-01", user.getBirthday());
        assertEquals("123456789", user.getPhoneNo());
        assertEquals("john@example.com", user.getEmail());
        assertEquals("password", user.getPassword());
        assertEquals(50000, user.getSalary());
        assertEquals(Access.ADMINISTRATOR, user.getAccessLevel());
        assertEquals("active", user.getStatus());
    }

    @Test
    void getAllDataShouldReturnFormattedData() {
        User user = new User(new String[]{"John Doe", "1990-01-01", "123456789", "john@example.com", "password", "50000", "admin", "active"});

        String expectedData = "John Doe,1990-01-01,123456789,john@example.com,password,50000,ADMINISTRATOR,active";
        String actualData = user.getAllData();

        assertEquals(expectedData, actualData);
    }

    @Test
    void toStringShouldReturnFormattedString() {
        User user = new User(new String[]{"John Doe", "1990-01-01", "123456789", "john@example.com", "password", "50000", "admin", "active"});

        String expectedString = "Name: John Doe, Salary: 50000, Access Level: ADMINISTRATOR";
        String actualString = user.toString();

        assertEquals(expectedString, actualString);
    }
}