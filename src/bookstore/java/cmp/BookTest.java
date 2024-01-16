package bookstore.java.cmp;

import org.junit.Before;
import org.junit.Test;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertNotNull;

public class BookTest {

    private Book book;

    @Before
    public void setUp() {
        String[] data = {"123456789", "Book Name", "Category", "Publisher", "2021-01-01", "50", "100", "Author", "10"};
        book = new Book(data);
    }

    @Test
    public void testGetISBN() {
        assertNotNull(book.getISBN());
        if (book.getISBN() != null)
            assertEquals(123456789L, Long.parseLong(book.getISBN()+""));
    }

    @Test
    public void testGetName() {
        assertEquals("Book Name", book.getName());
    }

    @Test
    public void testGetCategory() {
        assertEquals("Category", book.getCategory());
    }

    @Test
    public void testGetPublisher() {
        assertEquals("Publisher", book.getPublisher());
    }

    @Test
    public void testGetPDate() {
        assertEquals("2021-01-01", book.getPDate());
    }

    @Test
    public void testGetCP() {
        assertEquals(50, book.getCP());
    }

    @Test
    public void testGetSP() {
        assertEquals(100, book.getSP());
    }

    @Test
    public void testGetAuthor() {
        assertEquals("Author", book.getAuthor());
    }

    @Test
    public void testGetStock() {
        assertEquals(10, book.getStock());
    }

    @Test
    public void testSetISBN() {
        book.setISBN(987654321L);
        assertEquals("987654321", book.getISBN().toString());
    }

    @Test
    public void testSetName() {
        book.setName("New Book Name");
        assertEquals("New Book Name", book.getName());
    }

    @Test
    public void testSetCategory() {
        book.setCategory("New Category");
        assertEquals("New Category", book.getCategory());
    }

    @Test
    public void testSetPublisher() {
        book.setPublisher("New Publisher");
        assertEquals("New Publisher", book.getPublisher());
    }

    @Test
    public void testSetPDate() {
        book.setPDate("2022-01-01");
        assertEquals("2022-01-01", book.getPDate());
    }

    @Test
    public void testSetCP() {
        book.setCP(60);
        assertEquals(60, book.getCP());
    }

    @Test
    public void testSetSP() {
        book.setSP(120);
        assertEquals(120, book.getSP());
    }

    @Test
    public void testSetAuthor() {
        book.setAuthor("New Author");
        assertEquals("New Author", book.getAuthor());
    }

    @Test
    public void testSetStock() {
        book.setStock(20);
        assertEquals(20, book.getStock());
    }

    @Test
    public void testToString() {
        String expected = "ISBN: 123456789, Book Name: Book Name, Author: Author, Stocks: 10";
        assertEquals(expected, book.toString());
    }

    @Test
    public void testGetAllData() {
        String expected = "123456789,Book Name,Category,Publisher,2021-01-01,50,100,Author,10";
        assertEquals(expected, book.getAllData());
    }
}