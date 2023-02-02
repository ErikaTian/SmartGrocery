package model;

import java.awt.*;
import java.util.Date;

// Represents a review for each product
public class Review {

    // fields to represent changing properties of a patient
    private Date date;
    private String comment;  // comments
    private int rate;    // the rate of product
//    private Image image; // photos

    // REQUIRES: the rate is from 1-5, 1 is the least satisfied, and 5 is the most satisfied
    // EFFECTS: constructs a review with any comment, a rate, any photo
    public Review(Date date, String comment, int rate) {
        this.date = date;
        this.comment = comment;
        this.rate = rate;
//        this.image = image;
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

//    // EFFECTS: returns any photo attached to a review
//    public Image getImage() {
//        return image;
//    }
}
