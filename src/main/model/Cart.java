package model;

import java.util.ArrayList;
import java.util.List;

// Represents a shopping cart in user account
public class Cart {

    //    private static final int INITIAL_QUANTITY = 1;  // may not need this
    // fields to represent changing properties of an account
    private List<Product> wishlist;  // a list of chose products
    private List<Integer> quantityList;  // quantity added for each product

    // Creates an empty list of new products
    // REQUIRES: products.size() == quantities.size(); all entries in quantities are > 0;
    //           all entries in both lists are non-null
    // MODIFIES: this
    // EFFECTS: creates a new cart that have a list of products with their corresponding quantities
    public Cart() {
        wishlist = new ArrayList<Product>();
        quantityList = new ArrayList<Integer>();
    }

    // MODIFIES: this
    // EFFECTS: add a product to the wishlist by the quantity of i
    public void addProductToWishlist(Product p, int i) {
        if (!wishlist.contains(p)) {
            wishlist.add(p);
            quantityList.add(i);
        } else {
            int index = wishlist.indexOf(p);
            int num = quantityList.get(index) + i;
            quantityList.set(index, num);
        }
    }

    // EFFECTS: returns the number of products on the wishlist
    public int sizeWishlist() {
        return wishlist.size();
    }

    // EFFECTS: finds the quantity (int) of a product on the wishlist by searching its name
    //          returns 0 if the product is not on the wishlist
    public int findQuantityForProduct(String name) {
//        String msg = null;
        int num = 0;
        for (Product p : wishlist) {
            if (p.getName().equals(name)) {
                int index = wishlist.indexOf(p);
                num = quantityList.get(index);
//                msg = p.toString() + "," + "[" + "Quantity : " + Integer.toString(num) + "]";
            }
        }
        return num;
    }
}

