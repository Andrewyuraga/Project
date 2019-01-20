package dao.impl;

import dao.OrdDao;
import db.ConnectionManager;
import entities.Ord;
import entities.User;
import org.apache.log4j.Logger;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Class OrdDaoImpl
 * <p>
 * Created by Yuraga on 19/10/2018.
 */

public class OrdDaoImpl extends AbstractDao implements OrdDao {
    private static volatile OrdDao INSTANCE = null;
    Logger logger = Logger.getLogger(OrdDaoImpl.class);

    private static final String saveOrdQuery = "INSERT INTO ORD (USER_ID, PARTS_ID, QUANTITY, TOTAL) VALUE ( ?, ?, ?, ?)";
    private static final String updateOrdQuery = "UPDATE ORD SET QUANTITY=? WHERE ORD_ID=?";
    private static final String getOrdQuery = "SELECT * FROM ORD WHERE ORD_ID=?";
    private static final String deleteOrdQuery = "DELETE FROM ORD WHERE ORD_ID=?";
    private static final String getAllOrdQuery = "SELECT ORD_ID, PARTS_ID, QUANTITY, TOTAL, USER_ID FROM ORD";
    private static final String getByUserId = "SELECT ORD_ID, PARTS_ID, QUANTITY, TOTAL, USER_ID FROM ORD WHERE USER_ID=? ORDER BY ORD_ID DESC";
    private static final String getTotalSum = "SELECT SUM(TOTAL) FROM ORD WHERE USER_ID=?";

    private static PreparedStatement psSave;
    private static PreparedStatement psUpdate;
    private static PreparedStatement psGet;
    private static PreparedStatement psDelete;
    private static PreparedStatement psGetAll;
    private static PreparedStatement psGetByUserId;
    private static PreparedStatement psGetTotal;

    private OrdDaoImpl() {
    }

    {
        try {
            psSave = ConnectionManager.getConnection().prepareStatement(saveOrdQuery, Statement.RETURN_GENERATED_KEYS);
            psGetByUserId = ConnectionManager.getConnection().prepareStatement(getByUserId);
            psUpdate = ConnectionManager.getConnection().prepareStatement(updateOrdQuery);
            psGet = ConnectionManager.getConnection().prepareStatement(getOrdQuery);
            psGetAll = ConnectionManager.getConnection().prepareStatement(getAllOrdQuery);
            psDelete = ConnectionManager.getConnection().prepareStatement(deleteOrdQuery);
            psGetTotal = ConnectionManager.getConnection().prepareStatement(getTotalSum);
        } catch (SQLException e) {
            logger.error(e);
        }
    }

    @Override
    public List<Double> getTotalSum(long user_id) throws SQLException {
        psGetByUserId.setLong(1, user_id);
        psGetTotal.execute();
        ResultSet rs = psGetTotal.getResultSet();
        List<Double> list = new ArrayList<>();
        while (rs.next()) {
            list.add(rs.getDouble(1));
        }
        close(rs);

        return list;
    }

    @Override
    public List<Ord> getByUserId(long user_id) throws SQLException {
        psGetByUserId.setLong(1, user_id);
        psGetByUserId.execute();
        ResultSet rs = psGetByUserId.getResultSet();
        List<Ord> list = new ArrayList<>();
        while (rs.next()) {
            list.add(populateOrd(rs));
        }
        close(rs);

        return list;
    }

    @Override
    public List<Ord> getAll() throws SQLException {
        psGetAll.executeQuery();
        ResultSet rs = psGetAll.getResultSet();
        List<Ord> list = new ArrayList<>();
        while (rs.next()) {
            list.add(populateOrd(rs));
        }
        close(rs);

        return list;
    }

    @Override
    public Ord save(Ord ord) throws SQLException {
        psSave.setLong(1, ord.getUserId());
        psSave.setLong(2, ord.getPartsId());
        psSave.setInt(3, ord.getQuantity());
        psSave.setDouble(4, ord.getTotal());
        psSave.executeUpdate();
        ResultSet rs = psSave.getGeneratedKeys();
        if (rs.next()) {
            ord.setId(rs.getLong(1));
        }
        close(rs);

        return ord;
    }


    @Override
    public Ord get(Serializable id) throws SQLException {
        psGet.setLong(1, (long) id);
        psGet.executeQuery();
        ResultSet rs = psGet.getResultSet();
        if (rs.next()) {
            return populateOrd(rs);
        }
        close(rs);

        return null;
    }

    @Override
    public User update(Ord ord) throws SQLException {
        psUpdate.setLong(2, ord.getId());
        psUpdate.setInt(1, ord.getQuantity());
        psUpdate.executeUpdate();

        return null;
    }

    @Override
    public int delete(Serializable id) throws SQLException {
        psDelete.setLong(1, (long) id);

        return psDelete.executeUpdate();
    }

    public static OrdDao getInstance() {
        OrdDao ordDao = INSTANCE;
        if (ordDao == null) {
            synchronized (OrdDaoImpl.class) {
                ordDao = INSTANCE;
                if (ordDao == null) {
                    INSTANCE = ordDao = new OrdDaoImpl();
                }
            }
        }

        return ordDao;
    }


    private Ord populateOrd(ResultSet rs) throws SQLException {
        Ord ord = new Ord();
        ord.setId(rs.getLong(1));
        ord.setPartsId(rs.getLong(2));
        ord.setQuantity(rs.getInt(3));
        ord.setTotal(rs.getDouble(4));
        ord.setUserId(rs.getLong(5));

        return ord;
    }

    private Ord ordTotalSum(ResultSet rs) throws SQLException {
        Ord ord = new Ord();
        ord.setUserId(rs.getLong(1));
        ord.setTotal(rs.getDouble(2));

        return ord;
    }
}
