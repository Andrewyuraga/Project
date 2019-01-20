package service;

import db.ConnectionManager;
import entities.User;
import org.junit.Assert;
import org.junit.Test;
import services.UserService;
import services.impl.UserServiceIplm;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Yuraga
 */

public class UserServiceTest {
    private UserService userService = UserServiceIplm.getInstance();

    public void initdata() {
    }

    @Test
    public void fulltest() throws SQLException {
        Connection connection = ConnectionManager.getConnection();
        connection.setAutoCommit(false);
        int beforeSave = userService.getAll().size();
        User newUserTwo = userService.save(new User("Lexa Pupkin", "mclexa@tut.by", "qwerasd", "Borisov"));
        int afterSave = userService.getAll().size();
        Assert.assertNotSame(beforeSave,afterSave);
        Assert.assertNotNull(newUserTwo);

        newUserTwo.setName("Alex Pupkin");
//        userService.update(newUserTwo);

        User updateUser = userService.get(newUserTwo.getId());
        Assert.assertEquals("Метод update не работает", "Alex Pupkin", updateUser.getName());

        User user2 = userService.getByEmail("mclexa@tut.by");
        Assert.assertNotNull(user2);

        userService.delete(newUserTwo.getId()); //НАДО найти причину не работасти
        Assert.assertNotSame(beforeSave,afterSave);

    }
}
