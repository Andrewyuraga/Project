package service;

import db.ConnectionManager;
import entities.Parts;
import org.junit.Assert;
import org.junit.Test;
import services.PartsService;
import services.impl.PartsServiceImpl;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Yuraga
 */

public class PartsServiceTest {
    private PartsService partsService = PartsServiceImpl.getInstance();

    public void initdata() {
    }

    @Test
    public void fulltest() throws SQLException{
        Connection connection = ConnectionManager.getConnection();
        connection.setAutoCommit(false);

        Parts newParts = new Parts();
        int beforeSave = partsService.getAll().size();
        Parts newPartsTwo = new Parts("Tesssst", "Motor oil", "https://4.imimg.com/data4/FL/CW/ANDROID-45604471/product-500x500.jpeg",
                "Heldsdsw", "16W-40", 24.30);
        partsService.save(newPartsTwo);
        int afterSave = partsService.getAll().size();
        Assert.assertNotSame(beforeSave,afterSave);

        newPartsTwo.setName("Shell");
        partsService.update(newPartsTwo);

        Parts updatePart = partsService.get(newPartsTwo.getId());
        Assert.assertEquals("Метод update не работает", "Shell", updatePart.getName());

        Parts parts2 = partsService.getByProducerAndChatacteristics("Tesssst","16W-40");
        Assert.assertNotNull(parts2);


        partsService.delete(newPartsTwo.getId());
        afterSave = partsService.getAll().size();
        Assert.assertEquals(beforeSave, afterSave);
    }
}
