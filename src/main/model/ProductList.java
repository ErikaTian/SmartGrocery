package model;

import java.util.LinkedList;
import java.util.List;

/* A representation of a collection of products
 * plus a few commands can be used to do some tasks for this list:
 * add or delete a new product, count the number of products on the list
 * print the information of all products on the list, etc.
 */
public class ProductList {

    private List<Product> productList;

    // Creates an empty list of new products
    public ProductList() {
        productList = new LinkedList<>();
    }

    // MODIFIES: this
    // EFFECTS: add a product to the list
    public void addProduct(Product p) {
        productList.add(p);
    }

    // MODIFIES: this
    // EFFECTS: delete a product from the list
    public void removeProduct(Product product) {
        productList.remove(product);
    }

    // EFFECTS: returns the number of products on the list

    public int sizeList() {
        return productList.size();
    }

    // EFFECTS: prints a list of all products' names

    public List<String> getFullList() {
        List<String> fullList = new LinkedList<>();
        for (Product p : productList) {
            fullList.add(p.getName());
        }
        return fullList;
    }

    // EFFECTS: finds a certain product by name
    public Product findProduct(String name) {
        Product result = null;
        for (Product p : productList) {
            if (p.getName().equals(name)) {
                result = p;
            }
        }
        return result;
    }
}
