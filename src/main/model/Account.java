package model;

import model.exceptions.InsufficientValueException;
import model.exceptions.NonPositiveException;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;

// Represents an account for each user
public class Account implements Writable {

    private static final int INITIAL_VALUE = 0;

    // fields to represent changing properties of an account
    private String name;  // customer name
    private double balance;    // account balance
    private Cart cart;  // a wishlist of products with their quantities in the cart

    // EFFECTS: constructs an account with a customer name, a balance, and a wishlist of
    //          products with quantities in the cart
    public Account(String name, double balance, Cart cart) {
        this.name = name;
        this.balance = balance;
        this.cart = cart;
    }

    // EFFECTS: constructs an account with a customer name, an initial balance of 0,
    //          and an empty cart without any product
    public Account(String name) {
        this.name = name;
        this.balance = INITIAL_VALUE;
        this.cart = new Cart(new ArrayList<>(), new ArrayList<>());
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

    // MODIFIES: this
    // EFFECTS: the balance increases by the amount in this account
    public double topUpBalance(double amount) throws NonPositiveException {
        if (amount <= 0) {
            throw new NonPositiveException();
        }
        balance = balance + amount;

        EventLog.getInstance().logEvent(new Event("User added $" + amount + " to the account."));

        return balance;
    }

    // MODIFIES: this
    // EFFECTS: the balance decreases by the amount in this account
    public double makePurchase(double amount) throws NonPositiveException, InsufficientValueException {
        if (amount <= 0) {
            throw new NonPositiveException();
        }
        if (balance < amount) {
            throw new InsufficientValueException();
        }
        balance = balance - amount;
        return balance;
    }

    // MODIFIES: this
    // EFFECTS: add p to the wishlist in the cart by the quantity of i
    public void addProductToCart(Product p, int i) {
        cart.addProductToWishlist(p, i);
    }

    // MODIFIES: this
    // EFFECTS: remove the product from the cart by searching its name
    public void removeProductFromCart(String name) {
        Product p;
        p = cart.getProduct(name);
        cart.removeProductFromWishlist(p);
    }

    // EFFECTS: convert Account to JSONObject
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("balance", balance);
        json.put("cart", cart.toJson());
        return json;
    }
}
