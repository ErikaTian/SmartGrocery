package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

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
        assertEquals(3,test1List.sizeList());
        assertEquals(test1product,test1List.findProduct("Apple"));
    }

    @Test
    void testRemoveProduct() {
        test1List.addProduct(test1product);
        test1List.addProduct(test2product);
        test1List.addProduct(test3product);
        test1List.removeProduct(test1product);
        test1List.removeProduct(test2product);
        assertEquals(1,test1List.sizeList());
        List<String> test1ListResult = new LinkedList<>();
        test1ListResult.add(test3product.getName());
        assertEquals(test1ListResult,test1List.getFullList());
    }

    @Test
    void testSizeProductList() {
        test1List.addProduct(test1product);
        test1List.addProduct(test2product);
        assertEquals(2,test1List.sizeList());
        test1List.addProduct(test3product);
        assertEquals(3,test1List.sizeList());
    }

    @Test
    void testGetFullList() {
        test1List.addProduct(test1product);
        test1List.addProduct(test2product);
        test1List.addProduct(test3product);
        List<String> test1ListResult = new LinkedList<>();
        test1ListResult.add(test1product.getName());
        test1ListResult.add(test2product.getName());
        test1ListResult.add(test3product.getName());
        assertEquals(test1ListResult,test1List.getFullList());
    }

    @Test
    void testFindProduct() {
        test1List.addProduct(test1product);
        test1List.addProduct(test2product);
        test1List.addProduct(test3product);
        assertEquals(test1product,test1List.findProduct("Apple"));
        assertNull(test1List.findProduct(""));
        assertNull(test1List.findProduct("Banana"));
    }
}
