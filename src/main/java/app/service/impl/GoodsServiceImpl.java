package app.service.impl;

import app.dao.GoodDao;
import app.entity.Good;
import java.sql.SQLException;
import java.util.List;

public class GoodsServiceImpl {
    private GoodDao goodDao;

    /**
     * @return list contains all Goods
     * @throws SQLException an exception that provides information on a database access
     * error or other errors.
     */
    public List<Good> getGoods() throws SQLException {
        return goodDao.getAllGoods();
    }

    public void setGoodDao(GoodDao goodDao) {
        this.goodDao = goodDao;
    }
}
