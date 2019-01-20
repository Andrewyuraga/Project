package dao.impl;

import dao.ReviewsDao;
import db.ConnectionManager;
import entities.Reviews;
import entities.User;
import lombok.NoArgsConstructor;
import org.apache.log4j.Logger;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Class ReviewsDaoImpl
 * <p>
 * Created by Yuraga on 26/11/2018
 */
@NoArgsConstructor
public class ReviewsDaoImpl extends AbstractDao implements ReviewsDao {
    private static volatile ReviewsDao INSTANCE = null;
    Logger logger = Logger.getLogger(ReviewsDaoImpl.class);

    private static final String saveReviewsQuery = "INSERT INTO REVIEWS (USER_ID, REVIEW) VALUE (?, ?)";
    private static final String updateReviewsQuery = "UPDATE REVIEWS SET REVIEW=? WHERE REVIEW_ID=?";
    private static final String getReviewsQuery = "SELECT * FROM REVIEWS WHERE REVIEW_ID=?";
    private static final String deleteReviewsQuery = "DELETE FROM REVIEWS WHERE REVIEW_ID=?";
    private static final String getAllReviewsQuery = "SELECT * FROM REVIEWS";

    private PreparedStatement psSave;
    private PreparedStatement psUpdate;
    private PreparedStatement psGet;
    private PreparedStatement psDelete;
    private PreparedStatement psGetAll;

    {
        try {
            psSave = ConnectionManager.getConnection().prepareStatement(saveReviewsQuery, Statement.RETURN_GENERATED_KEYS);
            psUpdate = ConnectionManager.getConnection().prepareStatement(updateReviewsQuery);
            psGet = ConnectionManager.getConnection().prepareStatement(getReviewsQuery);
            psGetAll = ConnectionManager.getConnection().prepareStatement(getAllReviewsQuery);
            psDelete = ConnectionManager.getConnection().prepareStatement(deleteReviewsQuery);
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error(e);
        } finally {

        }
    }


    @Override
    public List<Reviews> getAll() throws SQLException {
        psGetAll.execute();
        ResultSet rs = psGetAll.getResultSet();
        List<Reviews> list = new ArrayList<>();
        while (rs.next()) {
            list.add(populateReviews(rs));
        }
        close(rs);

        return list;
    }

    @Override
    public Reviews save(Reviews reviews) throws SQLException {
        psSave.setLong(1, reviews.getUserId());
        psSave.setString(2, reviews.getReviews());
        psSave.executeUpdate();
        ResultSet rs = psSave.getGeneratedKeys();
        if (rs.next()) {
            reviews.setId(rs.getLong(1));
        }
        close(rs);

        return reviews;
    }

    @Override
    public Reviews get(Serializable id) throws SQLException {
        psGet.setLong(1, (long) id);
        psGet.executeQuery();
        ResultSet rs = psGet.getResultSet();
        if (rs.next()) {
            return populateReviews(rs);
        }
        close(rs);

        return null;
    }

    @Override
    public User update(Reviews reviews) throws SQLException {
        psUpdate.setLong(2, reviews.getId());
        psUpdate.setString(1, reviews.getReviews());
        psUpdate.executeUpdate();

        return null;
    }

    @Override
    public int delete(Serializable id) throws SQLException {
        psDelete.setLong(1, (long) id);

        return psDelete.executeUpdate();
    }

    private Reviews populateReviews(ResultSet rs) throws SQLException {
        Reviews reviews = new Reviews();
        reviews.setId(rs.getLong(1));
        reviews.setUserId(rs.getLong(2));
        reviews.setReviews(rs.getString(3));

        return reviews;
    }

    public static ReviewsDao getInstance() {
        ReviewsDao reviewDao = INSTANCE;
        if (reviewDao == null) {
            synchronized (ReviewsDaoImpl.class) {
                reviewDao = INSTANCE;
                if (reviewDao == null) {
                    INSTANCE = reviewDao = new ReviewsDaoImpl();
                }
            }
        }

        return reviewDao;
    }
}
