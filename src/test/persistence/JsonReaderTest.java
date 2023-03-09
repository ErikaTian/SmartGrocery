package persistence;

import model.Account;
import model.AccountMap;
import model.Product;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
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
            assertEquals(0, a2.getBalance());
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
            Account a = accounts.getAccountByName("Erika");
            assertEquals("Erika", a.getName());
            assertEquals(100, a.getBalance());
            List<Product> pl = a.getCart().getWishlist();
            List<Integer> il = a.getCart().getQuantityList();
            assertEquals(3, pl.size());
            assertEquals(3, il.size());
            assertEquals(10, il.get(0));
            assertEquals(3, il.get(1));
            assertEquals(5, il.get(2));
            checkProduct("Apple", 5.2, new Date(20230328), pl.get(0));
            checkProduct("Purdy's Chocolate Box", 35.98, new Date(20240615), pl.get(1));
            checkProduct("Elephant Instant Noodles", 3.82, new Date(20240126), pl.get(2));
            // Note: date formatting still has problems !!!

//Note: cannot use below plan as two same objects will be stored at different memory slots
//            Product p1 = new Product("Apple",5.2, new Date(20230328));
//            Product p2 = new Product("Purdy's Chocolate Box",35.98, new Date(20240615));
//            Product p3 = new Product("Elephant Instant Noodles",3.82, new Date(20240126));
//            List<Product> testPl = new ArrayList<>();
//            testPl.add(p1);
//            testPl.add(p2);
//            testPl.add(p3);
//            List<Integer> testIl = new ArrayList<>();
//            testIl.add(10);
//            testIl.add(3);
//            testIl.add(5);
//            checkAccount(testPl, testIl, a.getCart());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

}
