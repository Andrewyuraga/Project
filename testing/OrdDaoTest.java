import dao.OrdDao;
import dao.impl.OrdDaoImpl;
import db.ConnectionManager;
import entities.Ord;
import entities.User;
import org.junit.Assert;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collections;

/**
 * Class OrdDaoTest
 * <p>
 * Created by Yuraga on 21/10/2018.
 */

public class OrdDaoTest {
    private OrdDao ordDao = OrdDaoImpl.getInstance();

    public void initdata() {
    }

    @Test
    public void fulltest() throws SQLException {
        Connection connection = ConnectionManager.getConnection();
        connection.setAutoCommit(true);
        int beforeSave = ordDao.getAll().size();
        Ord newOrd = ordDao.save(new Ord(1, 1, 10, 44.3));
        int afterSave = ordDao.getAll().size();
        Assert.assertNotSame(beforeSave,afterSave);

        newOrd.setQuantity(20);
        ordDao.update(newOrd);

        Ord updateOrd = ordDao.get(newOrd.getId());
        Assert.assertEquals("Метод update не работает", 20, newOrd.getQuantity());

        ordDao.delete(newOrd.getId());
        afterSave = ordDao.getAll().size();
        Assert.assertEquals(beforeSave, afterSave);
    }
}
