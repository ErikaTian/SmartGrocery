package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CartTest {
    private Product test1product;
    private Product test2product;
    private Product test3product;

    private Cart testCart;

    @BeforeEach
    void runBefore() {
        test1product = new Product("Apple",5.2, new Date(20230328));
        test2product = new Product("Purdy's Chocolate Box",35.98, new Date(20240615));
        test3product = new Product("Elephant Instant Noodles",3.82, new Date(20240126));

        testCart = new Cart();
    }

    @Test
    void testAddProductToWishlist() {
        testCart.addProductToWishlist(test1product, 1);
        assertEquals(1,testCart.sizeWishlist());
        testCart.addProductToWishlist(test2product, 2);
        assertEquals(2,testCart.sizeWishlist());
        testCart.addProductToWishlist(test2product, 10);
        assertEquals(2,testCart.sizeWishlist());
        assertEquals(12,testCart.findQuantityForProduct("Purdy's Chocolate Box"));
    }

    @Test
    void testSizeWishlist() {
        testCart.addProductToWishlist(test1product, 1);
        assertEquals(1,testCart.sizeWishlist());
        testCart.addProductToWishlist(test2product, 2);
        testCart.addProductToWishlist(test3product, 3);
        assertEquals(3,testCart.sizeWishlist());
        testCart.addProductToWishlist(test3product, 4);
        assertEquals(3,testCart.sizeWishlist());
    }

    @Test
    void testFindQuantityForProduct() {
        testCart.addProductToWishlist(test1product, 16);
        assertEquals(16,testCart.findQuantityForProduct("Apple"));
        assertEquals(0,testCart.findQuantityForProduct("Elephant Instant Noodles"));
    }

}
