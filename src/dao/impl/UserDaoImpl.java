package dao.impl;


import dao.UserDao;
import db.ConnectionManager;
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
 * Class UserDaoIpml
 * <p>
 * Created by Yuraga on 15/10/2018.
 */

public class UserDaoImpl extends AbstractDao implements UserDao {
    private static volatile UserDao INSTANCE = null;
    Logger logger = Logger.getLogger(UserDaoImpl.class);

    private static final String saveUserQuery = "INSERT INTO USER (FULL_NAME, EMAIL, PASSWORD, ADDRESS) VALUES (?, ?, ?, ?)";
    private static final String updateUserQuery = "UPDATE USER SET ADDRESS=? WHERE USER_ID=?";
    private static final String getAllUserQuery = "SELECT * FROM USER";
    private static final String getUserQuery = "SELECT * FROM USER WHERE USER_ID=?";
    private static final String deleteUserQuery = "DELETE FROM USER WHERE USER_ID=?";
    private static final String getUserByEmail = "SELECT * FROM USER WHERE EMAIL=?";

    private PreparedStatement psSave;
    private PreparedStatement psUpdate;
    private PreparedStatement psGet;
    private PreparedStatement psGetAll;
    private PreparedStatement psDelete;
    private PreparedStatement psGetByUserEmail;

    {
        try {
            psGetByUserEmail = ConnectionManager.getConnection().prepareStatement(getUserByEmail);
            psSave = ConnectionManager.getConnection().prepareStatement(saveUserQuery, Statement.RETURN_GENERATED_KEYS);
            psUpdate = ConnectionManager.getConnection().prepareStatement(updateUserQuery);
            psGet = ConnectionManager.getConnection().prepareStatement(getUserQuery);
            psGetAll = ConnectionManager.getConnection().prepareStatement(getAllUserQuery);
            psDelete = ConnectionManager.getConnection().prepareStatement(deleteUserQuery);
        } catch (SQLException e) {
            logger.error(e);
        } finally {

        }
    }


    @Override
    public User getByEmail(String email) throws SQLException {
        psGetByUserEmail.setString(1, email);
        ResultSet rs = psGetByUserEmail.executeQuery();
        if (rs.next()) {
            return populateEntities(rs);
        }
        rs.close();

        return null;
    }

    @Override
    public User save(User user) throws SQLException {
        psSave.setString(1, user.getName());
        psSave.setString(2, user.getEmail());
        psSave.setString(3, user.getPassword());
        psSave.setString(4, user.getAddress());
        psSave.executeUpdate();
        ResultSet rs = psSave.getGeneratedKeys();
        if (rs.next()) {
            user.setId(rs.getLong(1));
        }
        close(rs);

        return user;
    }

    @Override
    public User get(Serializable id) throws SQLException {
        psGet.setLong(1, (long) id);
        psGet.executeQuery();
        ResultSet rs = psGet.getResultSet();
        if (rs.next()) {
            return populateEntities(rs);
        }
        close(rs);

        return null;
    }

    @Override
    public List<User> getAll() throws SQLException {
        psGetAll.executeQuery();
        ResultSet rs = psGetAll.getResultSet();
        List<User> list = new ArrayList<>();
        while (rs.next()) {
            list.add(populateEntities(rs));
        }

        return list;
    }

    @Override
    public User update(User user) throws SQLException {
        psUpdate.setLong(2, user.getId());
        psUpdate.setString(1, user.getAddress());
        psUpdate.executeUpdate();

        return user;
    }

    @Override
    public int delete(Serializable id) throws SQLException {
        psDelete.setLong(1, (long) id);

        return psDelete.executeUpdate();
    }

    private User populateEntities(ResultSet rs) throws SQLException {
        User entities = new User();
        entities.setId(rs.getLong(1));
        entities.setName(rs.getString(2));
        entities.setEmail(rs.getString(3));
        entities.setPassword(rs.getString(4));
        entities.setAddress(rs.getString(5));
        entities.setBirthday(rs.getDate(6));
        entities.setStatus(rs.getString(7));
        entities.setRights(rs.getString(8));

        return entities;
    }

    public static UserDao getInstance() {
        UserDao userDao = INSTANCE;
        if (userDao == null) {
            synchronized (UserDaoImpl.class) {
                if (userDao == null) {
                    INSTANCE = userDao = new UserDaoImpl();
                }
            }
        }

        return userDao;
    }


}
