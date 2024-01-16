package bookstore.java.cmp;

import bookstore.java.cmp.Bill;
import org.junit.Before;
import org.junit.Test;

import static org.testng.AssertJUnit.assertEquals;

public class BillTest {

    private Bill bill;

    @Before
    public void setUp() {
        String[] data = {"1", "1234567890", "123456789", "2021-01-01", "2", "10", "5"};
        bill = new Bill(data);
    }

    @Test
    public void testGetBillNo() {
        assertEquals(1, bill.getBillNo());
    }

    @Test
    public void testGetLibrarianPhNo() {
        assertEquals("1234567890", bill.getLibrarianPhNo());
    }

    @Test
    public void testGetISBN() {
        assertEquals("123456789", bill.getISBN());
    }

    @Test
    public void testGetBillDate() {
        assertEquals("2021-01-01", bill.getBillDate());
    }

    @Test
    public void testGetQuantity() {
        assertEquals(2, bill.getQuantity());
    }

    @Test
    public void testGetPrice() {
        assertEquals(10, bill.getPrice());
    }

    @Test
    public void testGetProfit() {
        assertEquals(5, bill.getProfit());
    }

    @Test
    public void testSetBillNo() {
        bill.setBillNo(2);
        assertEquals(2, bill.getBillNo());
    }

    @Test
    public void testSetLibrarianPhNo() {
        bill.setName("9876543210");
        assertEquals("9876543210", bill.getLibrarianPhNo());
    }

    @Test
    public void testSetISBN() {
        bill.setISBN("987654321");
        assertEquals("987654321", bill.getISBN());
    }

    @Test
    public void testSetBillDate() {
        bill.setBillDate("2022-01-01");
        assertEquals("2022-01-01", bill.getBillDate());
    }

    @Test
    public void testSetQuantity() {
        bill.setQuantity(3);
        assertEquals(3, bill.getQuantity());
    }

    @Test
    public void testSetPrice() {
        bill.setPrice(15);
        assertEquals(15, bill.getPrice());
    }

    @Test
    public void testSetProfit() {
        bill.setProfit(8);
        assertEquals(8, bill.getProfit());
    }

    @Test
    public void testToString() {
        String expected = "ISBN: 123456789, Date: 2021-01-01, Quantity: 2, Price: 10";
        assertEquals(expected, bill.toString());
    }

    @Test
    public void testGetAllData() {
        String expected = "1,1234567890,123456789,2021-01-01,2,10,5";
        assertEquals(expected, bill.getAllData());
    }
}