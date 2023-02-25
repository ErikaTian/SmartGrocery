package model;

import java.util.ArrayList;
import java.util.List;

// Represents a list of reviews for each product
public class ReviewList {

    private List<Review> reviewList;

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

    // EFFECTS: prints a list of all reviews
    public ArrayList<String> getFullList() {
        ArrayList<String> fullList = new ArrayList<>();
        String result = null;
        for (Review r : reviewList) {
            fullList.add(r.toString());
        }
        return fullList;
    }

    // MODIFIES: this
    // EFFECTS: sort all reviews based on their rating from high to low;
    //          if two reviews have a same rate, then rank them based on their order in the list
//    public List<Review> sortReviewByRate() {
//        Review result = null;
//        int acc = 0;
//        for (Review r : reviewList) {
//            if (r.getRate() ???) {
//                ???
//            }
//        }
//        return ???;
//    }

}
