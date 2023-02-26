package model;

import model.exceptions.InsufficientValueException;
import model.exceptions.NonPositiveException;

// Represents an account for each user
public class Account {

    // fields to represent changing properties of an account
    private String name;  // customer name
    private double balance;    // remaining account balance
    private Cart cart;  // a wishlist of products with quantities in the cart
    //Note: should add an unchanging field like "FINAL STATIC INITIAL-VALUE = 0"

    // EFFECTS: constructs an account with a customer name, a balance, and a wishlist of
    //          products with quantities in the cart
    public Account(String name, double balance, Cart cart) {
        this.name = name;
        this.balance = balance;
        this.cart = cart;
    }

    // EFFECTS: returns an account's customer name
    public String getName() {
        return name;
    }

    // EFFECTS: returns an account's balance
    public double getBalance() {
        return balance;
    }

    // EFFECTS: returns a wishlist of products with quantities in the cart for user account
    public Cart getCart() {
        return cart;
    }

//    // Acknowledgement: this method is modified from "AccountRobust" project
//    private void checkBalanceInvariant() {
//        assert (balance >= 0);
//    }

    // MODIFIES: this
    // EFFECTS: the balance increases by the amount in this account
    public double topUpBalance(double amount) throws NonPositiveException {
//        checkBalanceInvariant();
        if (amount <= 0) {
            throw new NonPositiveException();
        }
        balance = balance + amount;
//        checkBalanceInvariant();
        return balance;
    }

    // MODIFIES: this
    // EFFECTS: the balance decreases by the amount in this account
    public double makePurchase(double amount) throws NonPositiveException, InsufficientValueException {
//        checkBalanceInvariant();
        if (amount <= 0) {
            throw new NonPositiveException();
        }
        if (balance < amount) {
            throw new InsufficientValueException();
        }
        balance = balance - amount;
//        checkBalanceInvariant();
        return balance;
    }

    // MODIFIES: this
    // EFFECTS: add the product to the wishlist in the cart
    public void addProductToCart(Product p, int i) {
        cart.addProductToWishlist(p, i);
    }

    // MODIFIES: this
    // EFFECTS: remove the product from the cart
    public void removeProductFromCart(String name) {
        Product p;
        p = cart.findProduct(name);
        cart.removeProductFromWishlist(p);

    }
}
