package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReviewListTest {
    private Review test1review;
    private Review test2review;
    private Review test3review;

    private ReviewList test1ReviewList;

    @BeforeEach
    void runBefore() {
        test1review = new Review(new Date(20200416),"This product has very good quality! " +
                "I love it!", 5);
        test2review = new Review(new Date(20190803),"This tool is not as good as I thought...", 3);
        test3review = new Review(new Date(20180519),"Nice and clean but the delivery was a bit slow :(",
                4);
        test1ReviewList = new ReviewList();
    }

    @Test
    void testAddReview() {
        test1ReviewList.addReview(test1review);
        test1ReviewList.addReview(test2review);
        test1ReviewList.addReview(test3review);
        assertEquals(3, test1ReviewList.sizeReviewList());
    }

    @Test
    void testRemoveReview() {
        test1ReviewList.addReview(test1review);
        test1ReviewList.addReview(test2review);
        test1ReviewList.addReview(test3review);
        test1ReviewList.removeReview(test1review);
        test1ReviewList.removeReview(test3review);
        assertEquals(1, test1ReviewList.sizeReviewList());
        ArrayList test1ListResult = new ArrayList<String>();
        test1ListResult.add("[20190803, This tool is not as good as I thought..., 3]");
        assertEquals(test1ListResult, test1ReviewList.getFullList());
    }

    @Test
    void testSizeReviewList() {
        test1ReviewList.addReview(test1review);
        test1ReviewList.addReview(test2review);
        assertEquals(2, test1ReviewList.sizeReviewList());
        test1ReviewList.addReview(test3review);
        assertEquals(3, test1ReviewList.sizeReviewList());
    }

//    @Test
//    void testSortReviewByRate() {
//        test1ReviewList.addReview(test1review);
//        test1ReviewList.addReview(test2review);
//        test1ReviewList.addReview(test3review);
//        ArrayList test1ListResult = new ArrayList<String>();
//        test1ListResult.add(test1review);
//        test1ListResult.add(test3review);
//        test1ListResult.add(test2review);
//        assertEquals(test1ListResult, test1ReviewList.sortReviewByRate());
//    }
}
