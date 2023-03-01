package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

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

        List<Product> wl = new ArrayList<Product>();
        List<Integer> ql = new ArrayList<Integer>();
        testCart = new Cart(wl, ql);
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
    void testRemoveProductFromWishlist() {
        testCart.addProductToWishlist(test1product, 1);
        testCart.removeProductFromWishlist(test1product);
        assertEquals(0,testCart.sizeWishlist());
        testCart.addProductToWishlist(test1product, 3);
        testCart.addProductToWishlist(test2product, 2);
        testCart.addProductToWishlist(test3product, 1);
        testCart.removeProductFromWishlist(test1product);
        testCart.removeProductFromWishlist(test2product);
        assertEquals(1,testCart.sizeWishlist());
        assertNull(testCart.getProduct("Apple"));
        assertEquals(test3product,testCart.getProduct("Elephant Instant Noodles"));
        testCart.removeProductFromWishlist(test1product);
        assertEquals(1,testCart.sizeWishlist());
        assertNull(testCart.getProduct("Apple"));
        assertEquals(test3product,testCart.getProduct("Elephant Instant Noodles"));
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
    void testIsProductInCart() {
        assertFalse(testCart.isProductInCart("Apple"));
        testCart.addProductToWishlist(test1product, 1);
        assertTrue(testCart.isProductInCart("Apple"));
        assertFalse(testCart.isProductInCart("Purdy's Chocolate Box"));
    }

    @Test
    void testFindQuantityForProduct() {
        testCart.addProductToWishlist(test1product, 16);
        assertEquals(16,testCart.findQuantityForProduct("Apple"));
        assertEquals(0,testCart.findQuantityForProduct("Elephant Instant Noodles"));
    }

    @Test
    void testGetProduct() {
        testCart.addProductToWishlist(test1product, 5);
        testCart.addProductToWishlist(test2product, 10);
        assertNull(testCart.getProduct("Elephant Instant Noodles"));
        assertEquals(test1product,testCart.getProduct("Apple"));
    }

    @Test
    void testGetFullList() {
        List<String> fullList = new ArrayList<>();
        assertEquals(fullList,testCart.getFullList());
        testCart.addProductToWishlist(test1product, 1);
        testCart.addProductToWishlist(test2product, 2);
        testCart.addProductToWishlist(test2product, 3);
        String s1 = "[Apple, quantity: 1]";
        String s2 = "[Purdy's Chocolate Box, quantity: 5]";
        fullList.add(s1);
        fullList.add(s2);
        assertEquals(fullList,testCart.getFullList());
    }
}
