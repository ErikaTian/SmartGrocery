package model;

import java.util.ArrayList;

/* A representation of a collection of products
 * plus a few commands can be used to do some tasks for the list of products:
 * add or delete a new product, count the number of products on the list
 * print a list of all products' names, etc.
 */
public class ProductList {

    private ArrayList<Product> productList;

    // Creates an empty list of new products
    public ProductList() {
        productList = new ArrayList<>();
    }

    // MODIFIES: this
    // REQUIRES: add a product to the list
    public void addProduct(Product product) {
        productList.add(product);
    }

    // MODIFIES: this
    // REQUIRES: delete a product from the list
    public void removeProduct(Product product) {
        productList.remove(product);
    }

    // EFFECTS: returns the number of products on the list
    public int sizeProductList() {
        return productList.size();
    }

    // EFFECTS: prints a list of all products' names
    public ArrayList<String> getFullList() {
        ArrayList<String> fullList = new ArrayList<>();
        for (Product p : productList) {
            fullList.add(p.getName());
        }
        return fullList;
    }

}