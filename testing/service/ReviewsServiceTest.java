package service;

import db.ConnectionManager;
import entities.Reviews;
import org.junit.Assert;
import org.junit.Test;
import services.ReviewsService;
import services.impl.ReviewsServiceImpl;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Yuraga
 */

public class ReviewsServiceTest {

    private ReviewsService reviewsService = ReviewsServiceImpl.getInstance();

    public void initdata() {
    }

    @Test
    public void fulltest() throws SQLException {
        Connection connection = ConnectionManager.getConnection();
        connection.setAutoCommit(false);

        Reviews newReview = new Reviews();
        int beforeSave = reviewsService.getAll().size();
        Reviews newReviewTwo = new Reviews(1,"Test test");
        reviewsService.save(newReviewTwo);
        int afterSave = reviewsService.getAll().size();
        Assert.assertNotSame(beforeSave,afterSave);

        System.out.println(reviewsService.getAll());
        newReviewTwo.setReviews("Test test");
        reviewsService.update(newReviewTwo);

        Reviews updateReviews = reviewsService.get(newReviewTwo.getId());
        Assert.assertEquals("Метод update не работает", "Test test", updateReviews.getReviews());

        reviewsService.delete(newReviewTwo.getId());
        afterSave = reviewsService.getAll().size();
        Assert.assertEquals(beforeSave, afterSave);
    }
}
