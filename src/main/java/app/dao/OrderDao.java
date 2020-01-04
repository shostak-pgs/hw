package app.dao;

import app.entity.Order;
import java.sql.SQLException;

public interface OrderDao {

    /**
     * Returns the order by the transferred user's id
     * @param id user's id
     * @return the {@link Order}
     * @throws SQLException An exception that provides information on a database access
     * error or other errors.
     */
    Order getOrderByUserId(Long id) throws SQLException;

    /**
     * Add to Order table by the transferred user's id
     * @param userId id user's id
     * @throws SQLException An exception that provides information on a database access
     * error or other errors.
     */
    void addToOrder(Long userId) throws SQLException;

    /**
     * Update user's Order with transferred price
     * @param totalPrice price to be assigned to the order
     * @param userId id of user to update
     */
    boolean updateOrderById(double totalPrice, long userId) throws SQLException;
}
