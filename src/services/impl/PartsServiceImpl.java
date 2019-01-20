package services.impl;

import dao.PartsDao;
import dao.impl.PartsDaoImpl;
import entities.Parts;
import services.PartsService;
import services.ServiceException;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

/**
 * Сlass PartsServiceImpl
 * <p>
 * Created by Yuraga
 */

public class PartsServiceImpl extends AbstractService implements PartsService {
    private static volatile PartsService INSTANCE = null;

    private PartsDao partsDao = PartsDaoImpl.getInstance();

    @Override
    public Parts save(Parts parts) {
        try {
            parts = partsDao.save(parts);
        } catch (SQLException e) {
            throw new ServiceException("Ошибка создания Parts " + parts);
        }
        return parts;
    }

    @Override
    public List<Parts> getByCategory(String category) throws SQLException {
        try {
            return partsDao.getByCategory(category);
        } catch (SQLException e) {
            rollback();
            throw new SecurityException("Ошибка получения запчастей по Category" + category);
        }
    }

    @Override
    public Parts get(Serializable id) {
        try {
            return partsDao.get(id);
        } catch (SQLException e) {
            throw new ServiceException("Ошибка получения Parts по id " + id);
        }
    }

    @Override
    public void update(Parts parts) {
        try {
            partsDao.update(parts);
        } catch (SQLException e) {
            throw new ServiceException("Ошибка обновления Parts " + parts);
        }
    }

    @Override
    public int delete(Serializable id) {
        try {
            return partsDao.delete(id);
        } catch (SQLException e) {
            throw new ServiceException("Ошибка удаления Parts по id " + id);
        }
    }

    @Override
    public Parts getByProducerAndChatacteristics(String producer, String chatacteristics) throws SQLException {
        try {
            return partsDao.getByProducerAndChatacteristics(producer, chatacteristics);
        } catch (SQLException e) {
            throw new ServiceException("Ошибка получение запчасти по производителю: " + producer + " и и характеристик товара " + chatacteristics);
        }
    }

    @Override
    public List<Parts> getAll() {
        try {
            startTransaction();
            List<Parts> list = partsDao.getAll();
            commit();
            return list;
        } catch (SQLException e) {
            rollback();
            throw new ServiceException("Ошибка получения запчасти");
        }
    }

    public static PartsService getInstance() {
        PartsService partsService = INSTANCE;
        if (partsService == null) {
            synchronized (PartsServiceImpl.class) {
                partsService = INSTANCE;
                if (partsService == null) {
                    INSTANCE = partsService = new PartsServiceImpl();
                }
            }
        }

        return partsService;
    }

}
