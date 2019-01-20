import dao.UserDao;
import dao.impl.UserDaoImpl;
import db.ConnectionManager;
import entities.User;
import org.junit.Assert;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Class UserTestDao
 *
 * Created by Yuraga on 20/10/2018.
 */

public class UserDaoTest {
    private UserDao userDao = UserDaoImpl.getInstance();

    public void initdata() {
    }
    @Test
    public void fulltest() throws SQLException {
        Connection connection = ConnectionManager.getConnection();
        connection.setAutoCommit(true);
        int beforeSave = userDao.getAll().size();
        User newUser = userDao.save(new User("Lexa Pupkin", "mclexa@tut.by","qwerasd", "Borisov "));
        int afterSave = userDao.getAll().size();
        Assert.assertNotSame(beforeSave,afterSave);
        Assert.assertNotNull(newUser);

        newUser.setName("Alex Pupkin");
        userDao.update(newUser);

        User updateUser = userDao.get(newUser.getId());
        Assert.assertEquals("Метод update не работает", "Alex Pupkin", updateUser.getName());

        System.out.println(userDao.getAll());

        User user2 = userDao.getByEmail("mclexa@tut.by");
        Assert.assertNotNull(user2);

        userDao.delete(newUser.getId());
        Assert.assertNotSame(beforeSave,afterSave);

    }
}
