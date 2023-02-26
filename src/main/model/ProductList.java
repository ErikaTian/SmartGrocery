package model;

import java.util.LinkedList;
import java.util.List;

/* A representation of a collection of products
 * plus a few commands can be used to do some tasks for this list:
 * add or delete a new product, count the number of products on the list
 * print the information of all products on the list, etc.
 */
public class ProductList implements ListManager {

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
    public void addProduct(Product p) {
        productList.add(p);     //Q: assume all products are different somewhere?
    }

    // MODIFIES: this
    // REQUIRES: delete a product from the list
    public void removeProduct(Product product) {
        productList.remove(product);
    }

    // EFFECTS: returns the number of products on the list
    @Override
    public int sizeList() {
        return productList.size();
    }

    // EFFECTS: prints a list of all products' names
    @Override
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
                // Q: "p.getName() == name" is wrong as Java is comparing references
                // instead of objects!!! String is not a primitive type, cannot use "=="
                result = p;
            }
        }
        return result;
    }
}
