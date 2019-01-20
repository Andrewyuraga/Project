package services;

import entities.Parts;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

/**
 * Interface PartsService
 * <p>
 * Created by Yuraga on 15.10.2018
 */

public interface PartsService {
    Parts save(Parts parts);

    Parts get(Serializable id);

    void update(Parts parts);

    int delete(Serializable id);

    Parts getByProducerAndChatacteristics(String producer, String chatacteristics) throws SQLException;

    List<Parts> getAll() throws SQLException;

    List<Parts> getByCategory(String category) throws SQLException;
}
