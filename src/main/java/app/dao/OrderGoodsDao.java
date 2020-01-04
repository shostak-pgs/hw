package app.dao;

import app.entity.OrderGoods;
import java.sql.SQLException;
import java.util.List;

public interface OrderGoodsDao {

    /**
     * Return list contains all {@link OrderGoods} of the transferred orderId
     * @return the list with all {@link OrderGoods} of this order
     * @throws SQLException an exception that provides information on a database access
     * error or other errors.
     */
    List<OrderGoods> getByOrderId(Long orderId) throws SQLException;

    /**
     * Add to OrderGoods table units with transferred goodId and orderId
     * @param orderId the order id
     * @param goodId the good id
     * @throws SQLException an exception that provides information on a database access
     *      * error or other errors.
     */
    void addToOrderGood(Long orderId, Long goodId) throws SQLException;
}
