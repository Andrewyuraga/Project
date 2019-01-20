package services;

import entities.Ord;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

/**
 * Interface OrdService
 * <p>
 * Created by Yuraga on 15.10.2018
 */

public interface OrdService {
    Ord createOrder(long userId, long partsId, int quantity, double price);

    Ord get(Serializable id);

    void update(Ord ord);

    int delete(Serializable id);

    List<Ord> getByUserId(long userId);

    List<Ord> getAll() throws SQLException;

    List<Double> getTotalSum(long user_id) throws SQLException;
}
