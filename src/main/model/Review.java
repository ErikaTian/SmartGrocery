package model;

import java.util.Date;

// Represents a review for each product
public class Review {

    // fields to represent changing properties of a review
    private Date date;    // date when the review was written
    private String comment;  // comments
    private int rate;    // the rate of product

    // REQUIRES: the rate is from 1-5, 1 is the least satisfied, and 5 is the most satisfied
    // EFFECTS: constructs a review with any comment, a rate
    public Review(Date date, String comment, int rate) {
        this.date = date;
        this.comment = comment;
        this.rate = rate;
    }

    // EFFECTS: returns a review's date when it was written
    public Date getDate() {
        return date;
    }

    // EFFECTS: returns a review's comment
    public String getComment() {
        return comment;
    }

    // EFFECTS: returns a review's rate
    public int getRate() {
        return rate;
    }
}
