package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AccountTest {

    private Product test1product;
    private Product test2product;
    private Account test1account;
    private Account test2account;
    private ProductList test1PL;
    private ProductList test2PL;

    @BeforeEach
    public void setup(){
        test1product = new Product("Apple",5.2, new Date(20230328));
        test2product = new Product("Purdy's Chocolate Box",35.98, new Date(20240615));
        test1PL = new ProductList();
        test2PL = new ProductList();
        test2PL.addProduct(test1product);
        test2PL.addProduct(test2product);
        test1account = new Account("Jennifer Brown", 105.67, test1PL);
        test2account = new Account("Henry Hsu", 13.2, test2PL);
    }

    @Test
    public void testConstructor(){
        assertEquals("Jennifer Brown", test1account.getName());
        assertEquals(13.2, test2account.getBalance());
        assertEquals(test1PL, test1account.getCartList());
        assertEquals(test2PL, test2account.getCartList());
    }

    @Test
    public void testTopUpBalance(){
        assertEquals(105.68, test1account.topUpBalance(0.01));
        assertEquals(156.68, test1account.topUpBalance(51));
        assertEquals(159.08, test1account.topUpBalance(2.4));
    }

    @Test
    public void testMakePurchase(){
        assertEquals(13, test2account.makePurchase(0.2));
        assertEquals(9.77, test2account.makePurchase(3.23));
        assertEquals(0, test2account.makePurchase(9.77));
    }

}
