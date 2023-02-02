package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReviewListTest {
    private Review test1review;
    private Review test2review;
    private Review test3review;

    private ReviewList test1List;

    @BeforeEach
    void runBefore() {
        test1review = new Review(new Date(20200416),"This product has very good quality! " +
                "I love it!", 5);
        test2review = new Review(new Date(20190803),"This tool is not as good as I thought...", 3);
        test3review = new Review(new Date(20180519),"Nice and clean but the delivery was a bit slow :(",
                4);
    }

    @Test
    void testAddReview() {
        test1List.addReview(test1review);
        test1List.addReview(test2review);
        test1List.addReview(test3review);
        assertEquals(3,test1List.sizeReviewList());
    }

    @Test
    void testRemoveProduct() {
        test1List.addReview(test1review);
        test1List.addReview(test2review);
        test1List.addReview(test3review);
        test1List.removeReview(test1review);
        test1List.removeReview(test3review);
        assertEquals(1,test1List.sizeReviewList());
    }

    @Test
    void testSizeProductList() {
        test1List.addReview(test1review);
        test1List.addReview(test2review);
        assertEquals(2,test1List.sizeReviewList());
        test1List.addReview(test3review);
        assertEquals(3,test1List.sizeReviewList());
    }

    // @Test
    // Write tests for sorting method in ReviewList Class
}
