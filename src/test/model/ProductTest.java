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
        test1product = new Product("Apple",5.2, 100,
                new Date(20230328));
        test2product = new Product("Purdy's Chocolate Box",35.98, 35,
                new Date(20240615));
        test3product = new Product("Elephant Instant Noodles",3.82, 17,
                new Date(20240126));
    }

    @Test
    void testConstructor() {
        assertEquals("Apple", test1product.getName());
        assertEquals(5.2, test1product.getPrice());
        assertEquals(new Date(20240615), test2product.getBB());
        assertEquals(17, test3product.getQuantity());
    }
}