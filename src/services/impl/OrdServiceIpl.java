package services.impl;

import dao.OrdDao;
import dao.impl.OrdDaoImpl;
import entities.Ord;
import org.apache.log4j.Logger;
import services.OrdService;
import services.ServiceException;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

/**
 * Сlass OrdServiceIpl
 * <p>
 * Created by Yuraga
 */

public class OrdServiceIpl extends AbstractService implements OrdService {
    private static volatile OrdService INSTANCE = null;

    private OrdDao ordDao = OrdDaoImpl.getInstance();
    Logger logger = Logger.getLogger(OrdServiceIpl.class);

    @Override
    public List<Double> getTotalSum(long user_id) {
        try {
            return ordDao.getTotalSum(user_id);
        } catch (SQLException e) {
            throw new SecurityException("Ошибка получения общей суммы " + user_id);
        }
    }

    @Override
    public Ord createOrder(long userId, long parts_Id, int quantity, double price) {
        Ord ord = new Ord();
        try {
            startTransaction();
            ord.setUserId(userId);

            if (quantity < 1) {
                quantity = 1;
            }

            ord.setPartsId(parts_Id);
            ord.setQuantity(quantity);
            ord.setTotal(price * quantity);
            ord = ordDao.save(ord);

            commit();
            return ord;
        } catch (SQLException e) {
            rollback();
            throw new SecurityException("Ошибка создания Ord" + ord, e);
        }
    }

    @Override
    public Ord get(Serializable id) {
        try {
            return ordDao.get(id);
        } catch (SQLException e) {
            throw new SecurityException("Ошибка получения Ord по id " + id);
        }
    }

    @Override
    public void update(Ord ord) {
        try {
            ordDao.update(ord);
        } catch (SQLException e) {
            throw new SecurityException("Ошибка обновления по Ord" + ord);
        }
    }

    @Override
    public int delete(Serializable id) {
        try {
            return ordDao.delete(id);
        } catch (SQLException e) {
            throw new SecurityException("Ошибка удаления Ord по id " + id);
        }
    }

    @Override
    public List<Ord> getByUserId(long userId) {
        try {
            return ordDao.getByUserId(userId);
        } catch (SQLException e) {
            logger.error(e);
            rollback();
            throw new SecurityException("Ошибка получения заказов по userId" + userId);
        }
    }

    @Override
    public List<Ord> getAll() {
        try {
            startTransaction();
            List<Ord> list = ordDao.getAll();
            commit();
            return list;
        } catch (SQLException e) {
            rollback();
            throw new ServiceException("Ошибка получения заказов");
        }
    }

    public static OrdService getInstance() {
        OrdService ordService = INSTANCE;
        if (ordService == null) {
            synchronized (OrdServiceIpl.class) {
                ordService = INSTANCE;
                if (ordService == null) {
                    INSTANCE = ordService = new OrdServiceIpl();
                }
            }
        }

        return ordService;
    }
}
