package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;

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
        assertEquals(3,test1List.sizeProductList());
        assertEquals(test1product,test1List.findProduct("Apple"));
    }

    @Test
    void testRemoveProduct() {
        test1List.addProduct(test1product);
        test1List.addProduct(test2product);
        test1List.addProduct(test3product);
        test1List.removeProduct(test1product);
        test1List.removeProduct(test2product);
        assertEquals(1,test1List.sizeProductList());
        LinkedList test1ListResult = new LinkedList<String>();
        test1ListResult.add("[Elephant Instant Noodles, $3.82, 20240126]");
        assertEquals(test1ListResult,test1List.getFullList());
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
        LinkedList test1ListResult = new LinkedList<String>();
        test1ListResult.add("[Apple, $5.20, 20230328]");
        test1ListResult.add("[Purdy's Chocolate Box, $35.98, 20240615]");
        test1ListResult.add("[Elephant Instant Noodles, $3.82, 20240126]");
        assertEquals(test1ListResult,test1List.getFullList());
    }

    @Test
    void testFindProduct() {
        test1List.addProduct(test1product);
        test1List.addProduct(test2product);
        test1List.addProduct(test3product);
        assertEquals(test1product,test1List.findProduct("Apple"));
        assertEquals(null,test1List.findProduct(""));
        assertEquals(null,test1List.findProduct("Banana"));
    }
}
