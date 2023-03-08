package persistence;

import model.Account;
import model.AccountMap;
import model.Cart;
import model.Product;
import model.exceptions.DuplicateAccountException;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

// this class is inspired by JsonSerializationDemo project
// github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
public class JsonWriterTest extends JsonTest {

    @Test
    void testWriterInvalidFile() {
        try {
            List<Product> pl= new ArrayList<>();
            List<Integer> il= new ArrayList<>();
            Cart c = new Cart(pl, il);
            Account a = new Account("Erika", 100, c);
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyWorkroom() {
        try {
            List<Product> pl= new ArrayList<>();
            List<Integer> il= new ArrayList<>();
            Cart c = new Cart(pl, il);
            Account a = new Account("Erika",100, c);
            AccountMap accounts = new AccountMap();
            accounts.addAccount("Erika", a);
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyCartInAccount.json");
            writer.open();
            writer.write(a);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyCartInAccount.json");
            accounts = reader.read();
            assertEquals("Erika", a.getName());
            assertEquals(100, a.getBalance());
            assertEquals(0, a.getCart().getWishlist().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        } catch (DuplicateAccountException e) {
            System.out.println("This user has already exists!");
        }
    }

    @Test
    void testWriterGeneralWorkroom() {
        try {
            Product p1 = new Product("Apple",5.2, new Date(20230328));
            Product p2 = new Product("Purdy's Chocolate Box",35.98, new Date(20240615));
            Product p3 = new Product("Elephant Instant Noodles",3.82, new Date(20240126));
            List<Product> pl= new ArrayList<>();
            List<Integer> il= new ArrayList<>();
            pl.add(p3);
            pl.add(p1);
            pl.add(p2);
            il.add(14);
            il.add(20);
            il.add(7);
            Cart c = new Cart(pl, il);
            Account a = new Account("Erika", 100, c);
            AccountMap accounts = new AccountMap();
            accounts.addAccount("Erika", a);
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralCartInAccount.json");
            writer.open();
            writer.write(a);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralCartInAccount.json");
            accounts = reader.read();
            assertEquals("Erika", a.getName());
            assertEquals(100, a.getBalance());
            assertEquals(3, a.getCart().getWishlist().size());
            List<Product> productList = a.getCart().getWishlist();
            List<Integer> quantityList = a.getCart().getQuantityList();
            assertEquals(14, quantityList.get(0));
            assertEquals(20, quantityList.get(1));
            assertEquals(7, quantityList.get(2));
            checkProduct("Elephant Instant Noodles",3.82, new Date(20240126), productList.get(0));
            checkProduct("Apple", 5.2, new Date(20230328), productList.get(1));
            checkProduct("Purdy's Chocolate Box",35.98, new Date(20240615), productList.get(2));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        } catch (DuplicateAccountException e) {
            System.out.println("This user has already exists!");
        }
    }
}
