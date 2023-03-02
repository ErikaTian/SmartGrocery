package persistence;

import model.Account;
import model.Cart;
import model.Product;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

// this class is inspired by JsonSerializationDemo project
// github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
public class JsonTest {
    protected void checkProduct(String name, double price, Date date, Product product) {
        assertEquals(name, product.getName());
        assertEquals(price, product.getPrice());
        assertEquals(date, product.getBB());
    }
}
