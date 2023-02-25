package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReviewTest {

    private Review test1review;
    private Review test2review;
    private Review test3review;

    @BeforeEach
    void runBefore() {
        test1review = new Review(new Date(20200416),"This product has very good quality! " +
                "I love it!", 5);
        test2review = new Review(new Date(20190803),"This tool is not as good as I thought...", 3);
        test3review = new Review(new Date(20180519),"Nice and clean but the delivery was a bit slow :(",
                4);
    }

    @Test
    void testConstructor() {
        assertEquals(new Date(20200416), test1review.getDate());
        assertEquals("This tool is not as good as I thought...", test2review.getComment());
        assertEquals(4, test3review.getRate());
    }

    @Test
    void testToString() {
        assertEquals("[20200416, This product has very good quality! I love it!, 5]", test1review.toString());
    }
}
