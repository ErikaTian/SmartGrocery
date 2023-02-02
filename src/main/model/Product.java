package model;

import java.util.Date;

// Represents a product with a name, a price, an age, a quantity in storage, expiration date
public class Product {

    // fields to represent changing properties of a patient
    private String name;  // product name
    private double price;    // product price
    private int quantity;   // # of product in storage
    private Date bb;  // best-before date for product
//    private ReviewList reviews; // reviews for product

    // EFFECTS: constructs product with a name, a price, an age, a quantity in storage
    //          expiration date (add reviews later)
    public Product(String name, double price, int quantity, Date bb) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.bb = bb;
//        this.reviews = reviews;
    }

    // EFFECTS: returns a product's name
    public String getName() {
        return name;
    }

    // EFFECTS: returns a product's price
    public double getPrice() {
        return price;
    }

    // EFFECTS: returns a product's quantity at storage
    public int getQuantity() {
        return quantity;
    }

    // EFFECTS: returns a product's best before date
    public Date getBB() {
        return bb;
    }

//    // EFFECTS: returns a product's customer reviews
//    public ReviewList getReviews() {
//        return reviews;
//    }
}




