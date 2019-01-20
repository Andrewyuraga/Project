package services;

import entities.Reviews;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

/**
 * Interface ReviewsService
 * <p>
 * Created by Yuraga on 26.11.2018
 */

public interface ReviewsService {
    Reviews save(Reviews reviews);

    Reviews get(Serializable id);

    void update(Reviews reviews);

    int delete(Serializable id);

    List<Reviews> getAll() throws SQLException;

    Reviews createReview(long id, String text);
}
