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
            AccountMap accounts = new AccountMap();
            accounts.addAccount("Erika", a);
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
            writer.write(accounts);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyCartInAccount.json");
            accounts = reader.read();
            assertEquals("Erika", accounts.getAccountByName("Erika").getName());
            assertEquals(100, accounts.getAccountByName("Erika").getBalance());
            assertEquals(0, accounts.getAccountByName("Erika").getCart().getWishlist().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralWorkroom() {
        try {
            Product p1 = new Product("Apple",5.2, new Date(20230328));
            Product p2 = new Product("Purdy's Chocolate Box",35.98, new Date(20240615));
            Product p3 = new Product("Elephant Instant Noodles",3.82, new Date(20240126));
            List<Product> pl1= new ArrayList<>();
            List<Integer> il1= new ArrayList<>();
            List<Product> pl2= new ArrayList<>();
            List<Integer> il2= new ArrayList<>();
            pl1.add(p3);
            pl1.add(p1);
            pl1.add(p2);
            il1.add(14);
            il1.add(20);
            il1.add(7);
            pl2.add(p1);
            pl2.add(p2);
            il2.add(1);
            il2.add(99);
            Cart c1 = new Cart(pl1, il1);
            Cart c2 = new Cart(pl2, il2);
            Account a1 = new Account("Erika", 100, c1);
            Account a2 = new Account("Peter",20, c2);
            AccountMap accounts = new AccountMap();
            accounts.addAccount("Erika", a1);
            accounts.addAccount("Peter", a2);
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralCartInAccount.json");
            writer.open();
            writer.write(accounts);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralCartInAccount.json");
            accounts = reader.read();
            assertEquals("Erika", accounts.getAccountByName("Erika").getName());
            assertEquals(100, accounts.getAccountByName("Erika").getBalance());
            assertEquals(3, accounts.getAccountByName("Erika").getCart().getWishlist().size());
            List<Product> productList1 = accounts.getAccountByName("Erika").getCart().getWishlist();
            List<Integer> quantityList1 = accounts.getAccountByName("Erika").getCart().getQuantityList();
            assertEquals(14, quantityList1.get(0));
            assertEquals(20, quantityList1.get(1));
            assertEquals(7, quantityList1.get(2));
            checkProduct("Elephant Instant Noodles",3.82, new Date(20240126), productList1.get(0));
            checkProduct("Apple", 5.2, new Date(20230328), productList1.get(1));
            checkProduct("Purdy's Chocolate Box",35.98, new Date(20240615), productList1.get(2));

            assertEquals("Peter", accounts.getAccountByName("Peter").getName());
            assertEquals(20, accounts.getAccountByName("Peter").getBalance());
            assertEquals(2, accounts.getAccountByName("Peter").getCart().getWishlist().size());
            List<Product> productList2 = accounts.getAccountByName("Peter").getCart().getWishlist();
            List<Integer> quantityList2 = accounts.getAccountByName("Peter").getCart().getQuantityList();
            assertEquals(1, quantityList2.get(0));
            assertEquals(99, quantityList2.get(1));
            checkProduct("Apple", 5.2, new Date(20230328), productList2.get(0));
            checkProduct("Purdy's Chocolate Box",35.98, new Date(20240615), productList2.get(1));
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
