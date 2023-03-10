package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AccountMapTest {
    private Account test1account;
    private Account test2account;
    private Account test3account;
    private AccountMap testCollection;
    private Product testProduct;
    private Cart testCart;

    @BeforeEach
    public void setup(){
        test1account = new Account("Jennifer Brown");
        test2account = new Account("Henry Hsu");
        test3account = new Account("Erika Tian");
        testCollection = new AccountMap();
        testProduct = new Product("Apple",5.2, new Date(20230328));
        List<Product> wl = new ArrayList<>();
        List<Integer> ql = new ArrayList<>();
        testCart = new Cart(wl, ql);
        testCart.addProductToWishlist(testProduct, 1);
    }

    @Test
    public void testConstructor(){
        assertEquals(0, testCollection.getSize());
        assertNull(testCollection.getAccountByName("Erika"));
    }

    @Test
    public void testAddAccount(){
        testCollection.addAccount("Jennifer Brown", test1account);
        assertEquals(1, testCollection.getSize());
        assertTrue(testCollection.hasAccountWithName("Jennifer Brown"));
        assertFalse(testCollection.hasAccountWithName("Henry Hsu"));
        testCollection.addAccount("Henry Hsu", test2account);
        testCollection.addAccount("Erika Tian", test3account);
        assertEquals(3, testCollection.getSize());
        assertTrue(testCollection.hasAccountWithName("Jennifer Brown"));
        assertTrue(testCollection.hasAccountWithName("Henry Hsu"));
        assertTrue(testCollection.hasAccountWithName("Erika Tian"));
        assertFalse(testCollection.hasAccountWithName("Harry Potter"));
        Account a = new Account("Erika Tian", 100, testCart);
        testCollection.addAccount("Erika Tian", a);
        assertEquals(3, testCollection.getSize());
        Account result = testCollection.getAccountByName("Erika Tian");
        Cart c = result.getCart();
        assertEquals(1, c.sizeWishlist());
        assertEquals(testProduct, c.getWishlist().get(0));
    }
}
