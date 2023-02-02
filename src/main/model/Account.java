package model;

import java.util.Date;

public class Account {

    // fields to represent changing properties of an account
    private String name;  // customer name
    private double balance;    // remaining account balance
    private ProductList cartList;  // a list of selected products in the cart

    // EFFECTS: constructs an account with a customer name, a balance, and a list of selected products in the cart
    public Account(String name, double balance, ProductList cartList) {
        this.name = name;
        this.balance = balance;
        this.cartList = cartList;
    }

    // EFFECTS: returns an account's customer name
    public String getName() {
        return name;
    }

    // EFFECTS: returns an account's balance
    public double getBalance() {
        return balance;
    }

    // EFFECTS: returns a list of selected products in the cart for this account
    public ProductList getCartList() {
        return cartList;
    }

    // REQUIRES: amount > 0
    // MODIFIES: this
    // EFFECTS: the balance increases by the amount in this account
    public double topUpBalance(double amount) {
        return balance = balance + amount;
    }

    // REQUIRES: amount > 0 AND balance >= amount before making a purchase
    // MODIFIES: this
    // EFFECTS: the balance decreases by the amount in this account
    public double makePurchase(double amount) {
        return balance = balance - amount;
    }
}
