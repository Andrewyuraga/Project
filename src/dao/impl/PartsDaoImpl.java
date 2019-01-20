package dao.impl;

import dao.PartsDao;
import db.ConnectionManager;
import entities.Parts;
import entities.User;
import org.apache.log4j.Logger;

import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Class PartsDaoImpl
 * <p>
 * Created by AYuraga on 18/10/2018.
 */

public class PartsDaoImpl extends AbstractDao implements PartsDao {
    private static volatile PartsDao INSTANCE = null;
    Logger logger = Logger.getLogger(PartsDaoImpl.class);

    private static final String savePartsQuery = "INSERT INTO PARTS (PRODUCER, CATEGORY, IMG, NAME_PARTS, CHATACTERISTICS, PRICE) VALUE (?, ?, ?, ?, ?, ?)";
    private static final String updatePartsQuery = "UPDATE PARTS SET PRODUCER=?, CATEGORY=?, IMG=?, NAME_PARTS=?, CHATACTERISTICS=?, PRICE=? WHERE PARTS_ID=?";
    private static final String getPartsQuery = "SELECT * FROM PARTS WHERE PARTS_ID=?";
    private static final String deletePartsQuery = "DELETE FROM PARTS WHERE PARTS_ID=?";
    private static final String getAllPartQuery = "SELECT * FROM PARTS";
    private static final String getByProducerAndChatacteristics = "SELECT * FROM PARTS WHERE PRODUCER=? AND CHATACTERISTICS=?";
    private static final String getByCategory = "SELECT * FROM PARTS WHERE CATEGORY=?";

    private PreparedStatement psSave;
    private PreparedStatement psUpdate;
    private PreparedStatement psGet;
    private PreparedStatement psDelete;
    private PreparedStatement psGetAll;
    private PreparedStatement psGetByProducerAndChatacteristecs;
    private static PreparedStatement psGetByCategory;

    {
        try {
            psGetByProducerAndChatacteristecs = ConnectionManager.getConnection().prepareStatement(getByProducerAndChatacteristics);
            psSave = ConnectionManager.getConnection().prepareStatement(savePartsQuery, Statement.RETURN_GENERATED_KEYS);
            psUpdate = ConnectionManager.getConnection().prepareStatement(updatePartsQuery);
            psGet = ConnectionManager.getConnection().prepareStatement(getPartsQuery);
            psGetAll = ConnectionManager.getConnection().prepareStatement(getAllPartQuery);
            psDelete = ConnectionManager.getConnection().prepareStatement(deletePartsQuery);
            psGetByCategory = ConnectionManager.getConnection().prepareStatement(getByCategory);
        } catch (SQLException e) {
            logger.error(e);
        } finally {
        }
    }

    @Override
    public Parts save(Parts parts) throws SQLException {
        psSave.setString(1, parts.getProducer());
        psSave.setString(2, parts.getCategory());
        psSave.setString(3, parts.getImg());
        psSave.setString(4, parts.getName());
        psSave.setString(5, parts.getChatacteristics());
        psSave.setDouble(6, parts.getPrice());
        psSave.executeUpdate();
        ResultSet rs = psSave.getGeneratedKeys();
        if (rs.next()) {
            parts.setId(rs.getLong(1));
        }
        close(rs);

        return parts;
    }

    @Override
    public List<Parts> getByCategory(String category) throws SQLException {
        psGetByCategory.setString(1, category);
        psGetByCategory.execute();
        ResultSet rs = psGetByCategory.getResultSet();
        List<Parts> list = new ArrayList<>();
        while (rs.next()) {
            list.add(populateParts(rs));
        }
        close(rs);

        return list;
    }

    @Override
    public Parts get(Serializable id) throws SQLException {
        psGet.setLong(1, (long) id);
        psGet.executeQuery();
        ResultSet rs = psGet.getResultSet();
        if (rs.next()) {
            return populateParts(rs);
        }
        close(rs);

        return null;
    }

    @Override
    public User update(Parts parts) throws SQLException {
        psUpdate.setLong(7, parts.getId());
        psUpdate.setString(1, parts.getProducer());
        psUpdate.setString(2, parts.getCategory());
        psUpdate.setString(3, parts.getImg());
        psUpdate.setString(4, parts.getName());
        psUpdate.setString(5, parts.getChatacteristics());
        psUpdate.setDouble(6, parts.getPrice());
        psUpdate.executeUpdate();

        return null;
    }

    @Override
    public int delete(Serializable id) throws SQLException {
        psDelete.setLong(1, (long) id);

        return psDelete.executeUpdate();
    }

    @Override
    public Parts getByProducerAndChatacteristics(String producer, String chatacteristics) throws SQLException {
        psGetByProducerAndChatacteristecs.setString(1, producer);
        psGetByProducerAndChatacteristecs.setString(2, chatacteristics);
        psGetByProducerAndChatacteristecs.execute();
        ResultSet rs = psGetByProducerAndChatacteristecs.getResultSet();
        while (rs.next()) {
            return populateParts(rs);
        }
        close(rs);

        return null;
    }

    @Override
    public List<Parts> getAll() throws SQLException {
        psGetAll.execute();
        ResultSet rs = psGetAll.getResultSet();
        List<Parts> list = new ArrayList<>();
        while (rs.next()) {
            list.add(populateParts(rs));
        }
        close(rs);

        return list;
    }

    private Parts populateParts(ResultSet rs) throws SQLException {
        Parts parts = new Parts();
        parts.setId(rs.getLong(1));
        parts.setProducer(rs.getString(2));
        parts.setCategory(rs.getString(3));
        parts.setImg(rs.getString(4));
        parts.setName(rs.getString(5));
        parts.setChatacteristics(rs.getString(6));
        parts.setPrice(rs.getDouble(7));

        return parts;
    }

    public static PartsDao getInstance() {
        PartsDao partsDao = INSTANCE;
        if (partsDao == null) {
            synchronized (PartsDaoImpl.class) {
                partsDao = INSTANCE;
                if (partsDao == null) {
                    INSTANCE = partsDao = new PartsDaoImpl();
                }
            }
        }

        return partsDao;
    }
}




