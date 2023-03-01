package persistence;

import model.Account;
import model.Product;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            Account a = reader.read();
            fail();
        } catch (IOException e) {
            // all good here
        }
    }

    @Test
    void testReaderEmptyCart() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyCart.json");
        try {
            Account a = reader.read();
            assertEquals("Erika", a.getName());
            assertEquals("100", a.getBalance());
            assertEquals(0, a.getCart().sizeWishlist());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralCart() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralCart.json");
        try {
            Account a = reader.read();
            assertEquals("Erika", a.getName());
            assertEquals("100", a.getBalance());
            List<Product> pl = a.getCart().getWishlist();
            List<Integer> il = a.getCart().getQuantityList();
            assertEquals(0, pl.size());
            assertEquals(0, il.size());

            Product p1 = new Product("Apple",5.2, new Date(20230328));
            Product p2 = new Product("Purdy's Chocolate Box",35.98, new Date(20240615));
            Product p3 = new Product("Elephant Instant Noodles",3.82, new Date(20240126));
            List<Product> testPl = new ArrayList<>();
            testPl.add(p1);
            testPl.add(p2);
            testPl.add(p3);
            List<Integer> testIl = new ArrayList<>();
            testIl.add(10);
            testIl.add(3);
            testIl.add(5);
            checkAccount(testPl, testIl, a.getCart());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

}