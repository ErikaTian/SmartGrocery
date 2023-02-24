package model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/* A representation of a collection of products
 * plus a few commands can be used to do some tasks for the list of products:
 * add or delete a new product, count the number of products on the list
 * print a list of all products' names, etc.
 */
public class ProductList {

    private List<Product> productList;

    // Creates an empty list of new products
    public ProductList() {
        productList = new LinkedList<>();
    }
    //example: if you allow clients to pass in more types, then change the field:
    // private List<Product> productList;
    // and change the constructor:
    // public ProductList(List<Product> products){
    //            productList = products;
    //        }


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
            fullList.add(p.toString());
        }
        return fullList;
    }

    // EFFECTS: finds a certain product by name
    public Product findProduct(String name) {
        Product result = null;
        for (Product p : productList) {
            if (p.getName().equals(name)) {
                // Q: "p.getName() == name" is wrong as Java is comparing references
                // instead of objects!!! String is not a primitive type, cannot use "=="
                result = p;
            }
        }
        return result;
    }
}