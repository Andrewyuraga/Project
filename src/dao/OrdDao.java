package dao;

import entities.Ord;

import java.sql.SQLException;
import java.util.List;

/**
 * Class OrdDao
 * <p>
 * Created by Yuraga on 14/10/2018.
 */

public interface OrdDao extends DAO<Ord> {
    List<Ord> getByUserId(long user_id) throws SQLException;

    List<Ord> getAll() throws SQLException;

    List<Double> getTotalSum(long user_id) throws SQLException;
}
