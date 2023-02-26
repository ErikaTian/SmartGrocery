package model;

import java.util.ArrayList;
import java.util.List;

/* A representation of a collection of reviews
 * plus a few commands can be used to do some tasks for this list:
 * add or delete a new review, count the number of reviews on the list
 * print the information of all reviews on the list, etc.
 */
public class ReviewList implements ListManager {

    private List<Review> reviewList;

    // Creates an empty list of new reviews
    public ReviewList() {
        reviewList = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: add a review to the list
    public void addReview(Review review) {
        reviewList.add(review);
    }

    // MODIFIES: this
    // EFFECTS: delete a review from the list
    public void removeReview(Review review) {
        reviewList.remove(review);
    }

    // EFFECTS: returns the number of reviews on the list
    @Override
    public int sizeList() {
        return reviewList.size();
    }

    // EFFECTS: prints a list of all reviews
    @Override
    public List<String> getFullList() {
        List<String> fullList = new ArrayList<>();
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
