package bookstore.java.cmp;

import org.junit.Test;

import static org.testng.AssertJUnit.assertEquals;

public class AccessTest {

    @Test
    public void testToString() {
        assertEquals("admin", Access.ADMINISTRATOR.toString());
        assertEquals("manager", Access.MANAGER.toString());
        assertEquals("librarian", Access.LIBRARIAN.toString());
    }
}