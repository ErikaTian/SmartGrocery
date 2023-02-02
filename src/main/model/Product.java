package model;

import java.util.Date;

// Represents a product with a name, a price, expiration date
public class Product {

    // fields to represent changing properties of a product
    private String name;  // product name
    private double price;    // product price
    private Date bb;  // best-before date for product

    // EFFECTS: constructs product with a name, a price, an age, a quantity in storage
    //          expiration date
    public Product(String name, double price, Date bb) {
        this.name = name;
        this.price = price;
        this.bb = bb;
    }

    // EFFECTS: returns a product's name
    public String getName() {
        return name;
    }

    // EFFECTS: returns a product's price
    public double getPrice() {
        return price;
    }

    // EFFECTS: returns a product's best before date
    public Date getBB() {
        return bb;
    }
}




