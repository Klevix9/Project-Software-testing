package bookstore.java.cmp;
import bookstore.java.cmp.Access;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AccessTest {

    @Test
    void testToString() {
        assertEquals("admin", Access.ADMINISTRATOR.toString());
        assertEquals("manager", Access.MANAGER.toString());
        assertEquals("librarian", Access.LIBRARIAN.toString());
    }

    @Test
    void testValues() {
        Access[] values = Access.values();
        assertEquals(3, values.length);
        assertEquals(Access.ADMINISTRATOR, values[0]);
        assertEquals(Access.MANAGER, values[1]);
        assertEquals(Access.LIBRARIAN, values[2]);
    }

    @Test
    void testValueOf() {
        assertEquals(Access.ADMINISTRATOR, Access.valueOf("ADMINISTRATOR"));
        assertEquals(Access.MANAGER, Access.valueOf("MANAGER"));
        assertEquals(Access.LIBRARIAN, Access.valueOf("LIBRARIAN"));
    }
}
