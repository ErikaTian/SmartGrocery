package persistence;

import model.Account;
import model.AccountMap;
import model.Product;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

// this class is inspired by JsonSerializationDemo project
// github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
public class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            AccountMap account = reader.read();
            fail();
        } catch (IOException e) {
            // all good here
        }
    }

    @Test
    void testReaderEmptyCart() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyCartInAccount.json");
        try {
            AccountMap accounts = reader.read();
            Account a1 = accounts.getAccountByName("Erika");
            assertEquals("Erika", a1.getName());
            assertEquals(100, a1.getBalance());
            assertEquals(0, a1.getCart().sizeWishlist());
            Account a2 = accounts.getAccountByName("Peter");
            assertEquals("Peter", a2.getName());
            assertEquals(20, a2.getBalance());
            assertEquals(0, a2.getCart().sizeWishlist());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralCart() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralCartInAccount.json");
        try {
            AccountMap accounts = reader.read();
            Account a1 = accounts.getAccountByName("Erika");
            Account a2 = accounts.getAccountByName("Peter");
            assertEquals("Erika", a1.getName());
            assertEquals(100, a1.getBalance());
            List<Product> pl1 = a1.getCart().getWishlist();
            List<Integer> il1 = a1.getCart().getQuantityList();
            List<Product> pl2 = a2.getCart().getWishlist();
            List<Integer> il2 = a2.getCart().getQuantityList();
            assertEquals(3, pl1.size());
            assertEquals(3, il1.size());
            assertEquals(10, il1.get(0));
            assertEquals(3, il1.get(1));
            assertEquals(5, il1.get(2));
            assertEquals(2, pl2.size());
            assertEquals(2, il2.size());
            assertEquals(1, il2.get(0));
            assertEquals(99, il2.get(1));
            checkProduct("Apple", 5.2, new Date(20230328), pl1.get(0));
            checkProduct("Purdy's Chocolate Box", 35.98, new Date(20240615), pl1.get(1));
            checkProduct("Elephant Instant Noodles", 3.82, new Date(20240126), pl1.get(2));
            checkProduct("Apple", 5.2, new Date(20230328), pl2.get(0));
            checkProduct("Purdy's Chocolate Box", 35.98, new Date(20240615), pl2.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
