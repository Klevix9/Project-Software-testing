package bookstore.java.cmp;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;


import org.junit.Test;

import bookstore.java.cmp.Book;

import static org.junit.Assert.*;

public class BookTest { //ky eshte unit testing

    private Book book;


    public void testToString() {
        String[] testData = {"123456789", "Test Book", "Fiction", "Test Publisher", "2022-01-01", "50", "100", "Test Author", "20"};
        book = new Book(testData);

        String expected = "ISBN: 123456789, Book Name: Test Book, Author: Test Author, Stocks: 20";
        assertEquals(expected, book.toString());
    }

    @Test
    public void testGetAllData() {
        String[] testData = {"123456789", "Test Book", "Fiction", "Test Publisher", "2022-01-01", "50", "100", "Test Author", "20"};
        book = new Book(testData);

        String expected = "123456789,Test Book,Fiction,Test Publisher,2022-01-01,50,100,Test Author,20";
        assertEquals(expected, book.getAllData());
    }

    @Test
    public void testGettersAndSetters() {
        String[] testData = {"123456789", "Test Book", "Fiction", "Test Publisher", "2022-01-01", "50", "100", "Test Author", "20"};
        book = new Book(testData);

        // Test the getters and setters for various properties
        assertEquals(Long.valueOf("123456789"), book.getISBN());
        assertEquals("Test Book", book.getName());
        assertEquals("Fiction", book.getCategory());
        assertEquals("Test Publisher", book.getPublisher());
        assertEquals("2022-01-01", book.getPDate());
        assertEquals(50, book.getCP());
        assertEquals(100, book.getSP());
        assertEquals("Test Author", book.getAuthor());
        assertEquals(20, book.getStock());

        // Modify some properties and check if setters work
        book.setName("Updated Test Book");
        assertEquals("Updated Test Book", book.getName());

        book.setStock(30);
        assertEquals(30, book.getStock());
    }

    // Add more test cases as needed
}
