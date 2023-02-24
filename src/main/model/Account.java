package model;

import model.exceptions.NonPositiveException;

import java.util.InputMismatchException;

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

    // this function is modified from TellerApp (AccountRobust project)
    private void checkBalanceInvariant() {
        assert (balance >= 0);
    }

    // REQUIRES: amount > 0
    // MODIFIES: this
    // EFFECTS: the balance increases by the amount in this account
    public double topUpBalance(double amount) throws NonPositiveException, InputMismatchException {
        checkBalanceInvariant();

        if (amount <= 0) {
            throw new NonPositiveException();
        }

        balance = balance + amount;
        checkBalanceInvariant();
        return balance;
    }

    // REQUIRES: amount > 0 AND balance >= amount before making a purchase
    // MODIFIES: this
    // EFFECTS: the balance decreases by the amount in this account
    public double makePurchase(double amount) {
        return balance = balance - amount;
    }

    // MODIFIES: this
    // EFFECTS: add the product to the shopping list in the cart
    public void addProductToCart(Product p) {
        cartList.addProduct(p);
    }

    // MODIFIES: this
    // EFFECTS: remove the product from the cart
    public void removeProductFromCart(String name) {
        Product p;
        p = cartList.findProduct(name);
        cartList.removeProduct(p);
    }
}
