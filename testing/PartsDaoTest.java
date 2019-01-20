import dao.PartsDao;
import dao.impl.PartsDaoImpl;
import db.ConnectionManager;
import entities.Parts;
import org.junit.Assert;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;


/**
 * Class PartsDaoTest
 *
 * Created by Yuraga on 19/10/2018.
 */

public class PartsDaoTest {
    private PartsDao partsDao = PartsDaoImpl.getInstance();

    public void initdata() {
    }

    @Test
    public void fulltest() throws SQLException {
        Connection connection = ConnectionManager.getConnection();
        connection.setAutoCommit(true);
        int beforeSave = partsDao.getAll().size();
        Parts newParts = new Parts("Tesssst", "Motor oil", "https://4.imimg.com/data4/FL/CW/ANDROID-45604471/product-500x500.jpeg",
                "Heldsdsw", "16W-40", 24.30);
        partsDao.save(newParts);
        int afterSave = partsDao.getAll().size();
        Assert.assertNotSame(beforeSave,afterSave);

        newParts.setName("Shell");
        partsDao.update(newParts);

        Parts updatePart = partsDao.get(newParts.getId());
        Assert.assertEquals("Метод update не работает", "Shell", updatePart.getName());

        Parts parts2 = partsDao.getByProducerAndChatacteristics("Tesssst","16W-40");
        Assert.assertNotNull(parts2);


        int c = partsDao.delete(newParts.getId());
        System.out.println(c);
        afterSave = partsDao.getAll().size();
        Assert.assertEquals(beforeSave, afterSave);
    }


}
