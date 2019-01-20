package dao;

import entities.Reviews;

import java.sql.SQLException;
import java.util.List;

/**
 * Class ReviewsDao
 * <p>
 * Created by Yuraga on 14/10/2018.
 */

public interface ReviewsDao extends DAO<Reviews> {
    List<Reviews> getAll() throws SQLException;
}
