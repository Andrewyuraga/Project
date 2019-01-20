package services;

import entities.User;

import java.io.Serializable;
import java.util.List;

/**
 * Interface UserService
 * <p>
 * Created by Yuraga on 15.10.2018
 */

public interface UserService {
    User save(User user);

    User get(Serializable id);

    User update(long id, String address);

    int delete(Serializable id);

    User getByEmail(String email);

    List<User> getAll();
}
