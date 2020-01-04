package app.dao;

import app.entity.Good;
import java.sql.SQLException;
import java.util.List;

public interface GoodDao{

    /**
     * Returns the good by the transferred name
     * @param name good's name
     * @return the {@link Good}
     * @throws SQLException an exception that provides information on a database access
     * error or other errors.
     */
    Good getGood(String name) throws SQLException;

    /**
     * Returns the good by the transferred id
     * @param goodId good's id
     * @return the {@link Good}
     * @throws SQLException an exception that provides information on a database access
     * error or other errors.
     */
    Good getGood(Long goodId) throws SQLException;

    /**
     * Return list contains all Goods in base
     * @return the list
     * @throws SQLException an exception that provides information on a database access
     * error or other errors.
     */
    List<Good> getAllGoods() throws SQLException;
}
