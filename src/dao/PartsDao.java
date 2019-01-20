package dao;

import entities.Parts;

import java.sql.SQLException;
import java.util.List;

/**
 * Class PartsDao
 * <p>
 * Created by Yuraga on 14/10/2018.
 */

public interface PartsDao extends DAO<Parts> {
    Parts getByProducerAndChatacteristics(String producer, String chatacteristics) throws SQLException;

    List<Parts> getAll() throws SQLException;

    List<Parts> getByCategory(String category) throws SQLException;
}
