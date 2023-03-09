package persistence;

import model.Product;
import java.util.Date;

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
