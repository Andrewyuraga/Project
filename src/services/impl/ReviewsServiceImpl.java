package services.impl;

import dao.ReviewsDao;
import dao.impl.ReviewsDaoImpl;
import entities.Reviews;
import services.ReviewsService;
import services.ServiceException;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

/**
 * Class ReviewsServiceImpl
 * <p>
 * Created by Yuraga on 26.11.2018
 */

public class ReviewsServiceImpl extends AbstractService implements ReviewsService {
    private static volatile ReviewsService INSTANCE = null;

    private ReviewsDao reviewsDao = ReviewsDaoImpl.getInstance();

    @Override
    public Reviews save(Reviews reviews) {
        try {
            reviews = reviewsDao.save(reviews);
        } catch (SQLException e) {
            throw new ServiceException("Ошибка создания Reviews " + reviews);
        }
        return reviews;
    }

    @Override
    public Reviews get(Serializable id) {
        try {
            return reviewsDao.get(id);
        } catch (SQLException e) {
            throw new ServiceException("Ошибка получения Reviews по id " + id);
        }
    }

    @Override
    public void update(Reviews reviews) {
        try {
            reviewsDao.update(reviews);
        } catch (SQLException e) {
            throw new ServiceException("Ошибка обновления Reviews " + reviews);
        }
    }

    @Override
    public int delete(Serializable id) {
        try {
            return reviewsDao.delete(id);
        } catch (SQLException e) {
            throw new ServiceException("Ошибка удаления Reviews по id " + id);
        }
    }

    @Override
    public Reviews createReview(long id, String text) {
        Reviews reviews = new Reviews();
        try {
            startTransaction();
            reviews.setUserId(id);
            reviews.setReviews(text);
            reviewsDao.save(reviews);
            commit();
            return reviews;
        } catch (SQLException e) {
            rollback();
            throw new SecurityException("Ошибка создания Review" + reviews, e);
        }
    }

    @Override
    public List<Reviews> getAll() throws SQLException {
        try {
            startTransaction();
            List<Reviews> list = reviewsDao.getAll();
            commit();
            return list;
        } catch (SQLException e) {
            rollback();
            throw new ServiceException("Ошибка получения отзывов");
        }
    }

    public static ReviewsService getInstance() {
        ReviewsService reviewsService = INSTANCE;
        if (reviewsService == null) {
            synchronized (ReviewsServiceImpl.class) {
                reviewsService = INSTANCE;
                if (reviewsService == null) {
                    INSTANCE = reviewsService = new ReviewsServiceImpl();
                }
            }
        }

        return reviewsService;
    }
}
