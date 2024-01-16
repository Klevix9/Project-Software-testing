package bookstore.java.cmp;

import org.testng.annotations.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.testng.AssertJUnit.assertEquals;

public class AccessTest {

    @Test
    public void testToString() {
        assertEquals("admin", Access.ADMINISTRATOR.toString());
        assertEquals("manager", Access.MANAGER.toString());
        assertEquals("librarian", Access.LIBRARIAN.toString());
    }

    @Test
    public void testAccessEnumValues() {
        Access[] values = Access.values();
        assertEquals(3, values.length);

        // Ensure each access level has a non-null string representation
        for (Access access : values) {
            assertNotNull(access.toString());
        }
    }

    @Test
    public void testAccessEnumOrder() {
        Access[] values = Access.values();

        // Ensure the order of enum values is as expected
        assertEquals(Access.ADMINISTRATOR, values[0]);
        assertEquals(Access.MANAGER, values[1]);
        assertEquals(Access.LIBRARIAN, values[2]);
    }
}
