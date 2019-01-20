package dao;

import entities.User;

import java.io.Serializable;
import java.sql.SQLException;

/**
 * Interface DAO
 * <p>
 * Created by Yuraga on 15/10/2018
 */

public interface DAO<T> {
    T save(T t) throws SQLException;

    T get(Serializable id) throws SQLException;

    User update(T t) throws SQLException;

    int delete(Serializable id) throws SQLException;
}