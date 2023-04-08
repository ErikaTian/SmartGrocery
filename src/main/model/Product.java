package model;

import org.json.JSONObject;
import persistence.Writable;

import java.util.Date;
import java.util.Objects;

// Represents a product with a name, a price, expiration date
public class Product implements Writable {

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

    /*
     * EFFECTS: returns a string representation of a product
     * Acknowledgement: this method is modified from "AccountNotRobust" project
     */
    public String toString() {
        String priceStr = String.format("%.2f", price);  // get price to 2 decimal places as a string
        String dateStr = String.valueOf(bb.getTime());
        return  name + ", $" + priceStr
                + ", " + dateStr;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Product product = (Product) o;
        return name.equals(product.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    // EFFECTS: convert Product to JSONObject
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("price", price);
        json.put("bb", String.valueOf(bb.getTime()));
        return json;
    }
}




