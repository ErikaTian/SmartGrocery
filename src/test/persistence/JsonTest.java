package persistence;

import model.Account;
import model.Cart;
import model.Product;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkAccount(List<Product> wishlist, List<Integer> quantityList, Cart cart) {
        assertEquals(wishlist, cart.getWishlist());
        assertEquals(quantityList, cart.getQuantityList());
    }
}
