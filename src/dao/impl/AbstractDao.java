package dao.impl;


import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Class AbstractDao
 * <p>
 * Created by Yuraga on 15/10/2018.
 */

public abstract class AbstractDao {

    protected void close(ResultSet rs) {
        try {
            if (rs != null)
                rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
