package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProductListTest {
    private Product test1product;
    private Product test2product;
    private Product test3product;

    private ProductList test1List;

    @BeforeEach
    void runBefore() {
        test1product = new Product("Apple",5.2, new Date(20230328));
        test2product = new Product("Purdy's Chocolate Box",35.98, new Date(20240615));
        test3product = new Product("Elephant Instant Noodles",3.82, new Date(20240126));

        test1List = new ProductList();
    }

    @Test
    void testAddProduct() {
        test1List.addProduct(test1product);
        test1List.addProduct(test2product);
        test1List.addProduct(test3product);
    }

    @Test
    void testRemoveProduct() {
        test1List.addProduct(test1product);
        test1List.addProduct(test2product);
        test1List.addProduct(test3product);
        test1List.removeProduct(test1product);
        test1List.removeProduct(test2product);
        assertEquals(1,test1List.sizeProductList());
        ArrayList test1ListName = new ArrayList<String>();
        test1ListName.add("[Elephant Instant Noodles, $3.82, 20240126]");
        assertEquals(test1ListName,test1List.getFullList());
    }
    @Test
    void testSizeProductList() {
        test1List.addProduct(test1product);
        test1List.addProduct(test2product);
        assertEquals(2,test1List.sizeProductList());
        test1List.addProduct(test3product);
        assertEquals(3,test1List.sizeProductList());
    }

    @Test
    void testGetFullList() {
        test1List.addProduct(test1product);
        test1List.addProduct(test2product);
        test1List.addProduct(test3product);
        ArrayList test1ListName = new ArrayList<String>();
        test1ListName.add("[Apple, $5.20, 20230328]");
        test1ListName.add("[Purdy's Chocolate Box, $35.98, 20240615]");
        test1ListName.add("[Elephant Instant Noodles, $3.82, 20240126]");
        assertEquals(test1ListName,test1List.getFullList());
    }

    // write tests for findProduct methods !!!
}
