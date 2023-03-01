package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

// Represents a shopping cart in user account
public class Cart implements Writable {

    // fields to represent changing properties of an account
    private List<Product> wishlist;  // a list of chose products
    private List<Integer> quantityList;  // quantity added for each product

    // Creates a list of new products
    // REQUIRES: products.size() == quantities.size(); all entries in quantities are > 0;
    //           all entries in both lists are non-null
    // EFFECTS: creates a new cart that have a list of products with their corresponding quantities
    public Cart(List<Product> wishlist, List<Integer> quantityList) {
//        wishlist = new ArrayList<Product>();
//        quantityList = new ArrayList<Integer>();
        this.wishlist = wishlist;
        this.quantityList = quantityList;
    }

    // MODIFIES: this
    // EFFECTS: add a product to the wishlist by the quantity of i
    public void addProductToWishlist(Product product, int i) {
        if (!wishlist.contains(product)) {
            wishlist.add(product);
            quantityList.add(i);
        } else {
            int index = wishlist.indexOf(product);
            int num = quantityList.get(index) + i;
            quantityList.set(index, num);
        }
    }

    // MODIFIES: this
    // EFFECTS: delete a product from the wishlist in the cart
    public void removeProductFromWishlist(Product product) {
        if (wishlist.contains(product)) {
            int index = wishlist.indexOf(product);
            wishlist.remove(product);
            quantityList.remove(index);
        }
    }

    // EFFECTS: returns the total number of products on the wishlist
    public int sizeWishlist() {
        return wishlist.size();
    }

    // EFFECTS: returns true if the wishlist contains a product by searching its name
    //          false otherwise
    public boolean isProductInCart(String name) {
        boolean acc = false;
        for (Product p : wishlist) {
            if (p.getName().equals(name)) {
                acc = true;
            }
        }
        return acc;
    }

    // EFFECTS: finds the quantity (int) of a product on the wishlist by searching its name
    //          returns 0 if the product is not on the wishlist
    public int findQuantityForProduct(String name) {
        int num = 0;
        for (Product p : wishlist) {
            if (p.getName().equals(name)) {
                int index = wishlist.indexOf(p);
                num = quantityList.get(index);
            }
        }
        return num;
    }

    // EFFECTS: finds the product on the wishlist by searching its name
    //          returns null if the product is not on the wishlist
    public Product getProduct(String name) {
        Product product = null;
        for (Product p : wishlist) {
            if (p.getName().equals(name)) {
                product = p;
            }
        }
        return product;
    }

    // EFFECTS: prints all products with their quantities in the cart
    public List<String> getFullList() {
        List<String> fullList = new ArrayList<>();
        for (Product p : wishlist) {
            int index = wishlist.indexOf(p);
            int num = quantityList.get(index);
            String print = "[" + p.getName()  + ", quantity: " + num + "]";
            fullList.add(print);
        }
        return fullList;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("wishlist", productsToJson());
        json.put("quantityList", quantitiesToJson());
        return json;
    }

    // EFFECTS: returns products on the wishlist as a JSON array
    private JSONArray productsToJson() {
        JSONArray jsonArray = new JSONArray();
        for (Product p : wishlist) {
            jsonArray.put(p.toJson());
        }
        return jsonArray;
    }

    // EFFECTS: returns quantities associated with products as a JSON array
    private JSONArray quantitiesToJson() {
        JSONArray jsonArray = new JSONArray();
        for (Integer i : quantityList) {
            Object o = (int) i;
            jsonArray.put(o);
        }
        return jsonArray;
    }
}

