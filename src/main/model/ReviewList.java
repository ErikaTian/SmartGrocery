package model;

import java.util.ArrayList;

// Represents a list of reviews for each product
public class ReviewList {

    private ArrayList<Review> reviewList;

    // Creates an empty list of new reviews
    public ReviewList() {
        reviewList = new ArrayList<>();
    }

    // MODIFIES: this
    // REQUIRES: add a review to the list
    public void addReview(Review review) {
        reviewList.add(review);
    }

    // MODIFIES: this
    // REQUIRES: delete a review from the list
    public void removeReview(Review review) {
        reviewList.remove(review);
    }

    // EFFECTS: returns the number of reviews on the list
    public int sizeReviewList() {
        return reviewList.size();
    }

    // MODIFIES: this
    // EFFECTS: sort all reviews based on their rating from high to low

}
