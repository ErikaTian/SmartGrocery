package model;

import model.exceptions.InsufficientValueException;
import model.exceptions.NonPositiveException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class AccountTest {

    private Product test1product;
    private Product test2product;
    private Cart test1cart;
    private Cart test2cart;
    private Account test1account;
    private Account test2account;

    @BeforeEach
    public void setup(){
        test1product = new Product("Apple",5.2, new Date(20230328));
        test2product = new Product("Purdy's Chocolate Box",35.98, new Date(20240615));
        test1cart = new Cart();
        test2cart = new Cart();
        test2cart.addProductToWishlist(test1product, 2);
        test2cart.addProductToWishlist(test2product, 3);
        test1account = new Account("Jennifer Brown", 105.67, test1cart);
        test2account = new Account("Henry Hsu", 13.2, test2cart);
    }

    @Test
    public void testConstructor(){
        assertEquals("Jennifer Brown", test1account.getName());
        assertEquals(13.2, test2account.getBalance());
        assertEquals(0, test1account.getCart().sizeWishlist());
        assertEquals(2, test2account.getCart().sizeWishlist());
        assertEquals(2, test2account.getCart().findQuantityForProduct("Apple"));
        assertEquals(3, test2account.getCart().findQuantityForProduct("Purdy's Chocolate Box"));
    }

    @Test
    public void testTopUpBalance(){
        try {
            assertEquals(105.68, test1account.topUpBalance(0.01));
        } catch (NonPositiveException e) {
            fail();
        }
        try {
            assertEquals(156.68, test1account.topUpBalance(51));
        } catch (NonPositiveException e) {
            fail();
        }
        try {
            assertEquals(159.08, test1account.topUpBalance(2.4));
        } catch (NonPositiveException e) {
            fail();
        }
        try {
            test1account.topUpBalance(0);
            fail();
        } catch (NonPositiveException e) {
        }
        try {
            test1account.topUpBalance(-13);
            fail();
        } catch (NonPositiveException e) {
        }
    }

    @Test
    public void testMakePurchase(){
        try {
            assertEquals(13, test2account.makePurchase(0.2));
        } catch (NonPositiveException e) {
            fail();
        } catch (InsufficientValueException e) {
            fail();
        }
        try {
            assertEquals(9.77, test2account.makePurchase(3.23));
        } catch (NonPositiveException e) {
            fail();
        } catch (InsufficientValueException e) {
            fail();
        }
        try {
            assertEquals(0, test2account.makePurchase(9.77));
        } catch (NonPositiveException e) {
            fail();
        } catch (InsufficientValueException e) {
            fail();
        }
        try {
            test2account.makePurchase(0);
            fail();
        } catch (NonPositiveException e) {
        } catch (InsufficientValueException e) {
            fail();
        }
        try {
            test2account.makePurchase(-4);
            fail();
        } catch (NonPositiveException e) {
        } catch (InsufficientValueException e) {
            fail();
        }
        try {
            test1account.makePurchase(200);
            fail();
        } catch (NonPositiveException e) {
            fail();
        } catch (InsufficientValueException e) {
        }
    }

    @Test
    public void testAddProductToCart(){
        test1account.addProductToCart(test1product, 10);
        assertEquals(1, test1account.getCart().sizeWishlist());
        assertEquals(10, test1account.getCart().findQuantityForProduct("Apple"));
    }

    @Test
    public void testAddProductToCartMultipleTimes(){
        test1account.addProductToCart(test1product, 10);
        test1account.addProductToCart(test1product, 1);
        test1account.addProductToCart(test2product, 6);
        assertEquals(2, test1account.getCart().sizeWishlist());
        assertEquals(11, test1account.getCart().findQuantityForProduct("Apple"));
        assertEquals(6, test1account.getCart().findQuantityForProduct("Purdy's Chocolate Box"));
    }

    @Test
    public void testRemoveProductFromCart(){
        test2account.removeProductFromCart("Apple");
        assertEquals(1, test2account.getCart().sizeWishlist());
        assertEquals(0, test2account.getCart().findQuantityForProduct("Apple"));
        assertEquals(3, test2account.getCart().findQuantityForProduct("Purdy's Chocolate Box"));
    }

    @Test
    public void testRemoveProductFromCartMultipleTimes(){
        test2account.removeProductFromCart("Apple");
        test2account.removeProductFromCart("Purdy's Chocolate Box");
        assertEquals(0, test2account.getCart().sizeWishlist());
        assertFalse(test2account.getCart().isProductInCart("Apple"));
        assertFalse(test2account.getCart().isProductInCart("Purdy's Chocolate Box"));
    }
}
