package bookstore.java.cmp;

import static org.junit.Assert.assertEquals;

import bookstore.java.cmp.Bill;
import org.junit.Before;
import org.junit.Test;

public class BillTest {

    private Bill bill;

    @Before
    public void setUp() {
        // You can initialize the bill instance with test data here
        String[] testData = {"1", "123456789", "TestISBN", "2022-01-01", "5", "50", "20"};
        bill = new Bill(testData);
    }

    @Test
    public void testToString() {
        String expectedString = "ISBN: TestISBN, Date: 2022-01-01, Quantity: 5, Price: 50";
        assertEquals(expectedString, bill.toString());
    }

    @Test
    public void testGetAllData() {
        String expectedData = "1,123456789,TestISBN,2022-01-01,5,50,20";
        assertEquals(expectedData, bill.getAllData());
    }

    @Test
    public void testGettersAndSetters() {
        // Test getters
        assertEquals(1, bill.getBillNo());
        assertEquals("123456789", bill.getLibrarianPhNo());
        assertEquals("TestISBN", bill.getISBN());
        assertEquals("2022-01-01", bill.getBillDate());
        assertEquals(5, bill.getQuantity());
        assertEquals(50, bill.getPrice());
        assertEquals(20, bill.getProfit());

        // Test setters
        bill.setBillNo(2);
        bill.setName("987654321");
        bill.setISBN("NewTestISBN");
        bill.setBillDate("2022-02-01");
        bill.setQuantity(10);
        bill.setPrice(100);
        bill.setProfit(30);

        // Verify the changes
        assertEquals(2, bill.getBillNo());
        assertEquals("987654321", bill.getLibrarianPhNo());
        assertEquals("NewTestISBN", bill.getISBN());
        assertEquals("2022-02-01", bill.getBillDate());
        assertEquals(10, bill.getQuantity());
        assertEquals(100, bill.getPrice());
        assertEquals(30, bill.getProfit());
    }
}
