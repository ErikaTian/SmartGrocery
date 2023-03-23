package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class ProductTest {

    private Product test1product;
    private Product test2product;
    private Product test3product;

    @BeforeEach
    void runBefore() {
        test1product = new Product("Apple",5.2, new Date(20230328));
        test2product = new Product("Purdy's Chocolate Box",35.98, new Date(20240615));
        test3product = new Product("Elephant Instant Noodles",3.82, new Date(20240126));
    }

    @Test
    void testConstructor() {
        assertEquals("Apple", test1product.getName());
        assertEquals(3.82, test3product.getPrice());
        assertEquals(new Date(20240615), test2product.getBB());
    }

    @Test
    void testToString() {
        assertEquals("Apple, $5.20, 20230328", test1product.toString());
    }

    @Test
    void testEquals() {
        Product p = null;
        String s = "product";
        assertFalse(test1product.equals(p));
        assertFalse(test1product.equals(s));
        assertTrue(test1product.equals(test1product));
        assertFalse(test1product.equals(test2product));
    }

    @Test
    void testHashCode() {
        assertEquals(63476569, test1product.hashCode());
        assertEquals(-907354604, test2product.hashCode());
        assertEquals(-911598411, test3product.hashCode());
    }
}