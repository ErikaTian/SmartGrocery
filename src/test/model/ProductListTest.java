package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProductListTest {
    private Product test1product;
    private Product test2product;
    private Product test3product;

    private ProductList test1List;

    @BeforeEach
    void runBefore() {
        test1product = new Product("Apple",5.2, 100,
                new Date(20230328));
        test2product = new Product("Purdy's Chocolate Box",35.98, 35,
                new Date(20240615));
        test3product = new Product("Elephant Instant Noodles",3.82, 17,
                new Date(20240126));

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
//        assertEquals(...,test1List.getFullList()); ?? how to write a list of strings
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
//      assertEquals(...,test1List.getFullList()); ?? how to write a list of strings
    }
}
