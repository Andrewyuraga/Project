package services.impl;

import dao.UserDao;
import dao.impl.UserDaoImpl;
import entities.User;
import services.UserService;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

/**
 * Class UserServiceIplm
 * <p>
 * Created by Yuraga on 17.10.2018
 */

public class UserServiceIplm extends AbstractService implements UserService {
    private static volatile UserService INSTANCE = null;

    private UserDao userDao = UserDaoImpl.getInstance();

    @Override
    public User save(User user) {
        try {
            user = userDao.save(user);
        } catch (SQLException e) {
            throw new SecurityException("Ошибка создания User " + user);
        }
        return user;
    }

    @Override
    public User get(Serializable id) {
        try {
            return userDao.get(id);
        } catch (SQLException e) {
            throw new SecurityException("Ошибка получения User по id " + id);
        }
    }

    @Override
    public User update(long id, String address) {
        try {
            User user = userDao.get(id);
            user.setAddress(address);
            return userDao.update(user);
        } catch (SQLException e) {
            throw new SecurityException("Error getting User by email ");
        }
    }

    @Override
    public int delete(Serializable id) {
        try {
            return userDao.delete(id);
        } catch (SQLException e) {
            throw new SecurityException("Ошибка удаления User по id " + id);
        }
    }

    @Override
    public User getByEmail(String email) {
        try {
            return userDao.getByEmail(email);
        } catch (SQLException e) {
            throw new SecurityException("Error getting User by email " + email);
        }
    }

    @Override
    public List<User> getAll() {
        try {
            startTransaction();
            List<User> list = userDao.getAll();
            commit();
            return list;
        } catch (SQLException e) {
            rollback();
            throw new SecurityException("Ошибка получения всех Users ");
        }
    }

    public static UserService getInstance() {
        UserService userService = INSTANCE;
        if (userService == null) {
            synchronized (UserServiceIplm.class) {
                userService = INSTANCE;
                if (userService == null) {
                    INSTANCE = userService = new UserServiceIplm();
                }
            }
        }

        return userService;
    }
}
