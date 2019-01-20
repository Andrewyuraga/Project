package dao;

import entities.User;

import java.sql.SQLException;
import java.util.List;

/**
 * Class UserDao
 * <p>
 * Created by Yuraga on 14/10/2018.
 */

public interface UserDao extends DAO<User> {
    User getByEmail(String email) throws SQLException;

    List<User> getAll() throws SQLException;
}
