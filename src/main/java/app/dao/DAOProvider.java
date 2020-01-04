package app.dao;

import app.service.ContextUtil;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DAOProvider {
    private static final String CREATE_DB_URL = "jdbc:h2:mem:testdb;INIT=RUNSCRIPT FROM 'classpath:create_tables.sql'\\;RUNSCRIPT FROM 'classpath:populate.sql';DB_CLOSE_DELAY=-1";

    public static DAOProvider instance = (DAOProvider) ContextUtil.getContext().getBean("daoProvider");

    public static UserDao userDao;
    public static GoodDao goodDao;
    public static OrderDao orderDao;
    public static OrderGoodsDao orderGoods;

    /**
     * Return the single provider object
     * @return the {@link DAOProvider}
     */
    public static DAOProvider getInstance() {
        return instance;
    }

    /**
     * The constructor establishes a connection and creates the provider object
     */
    private DAOProvider(){
       initBase();
    }

    /**
     * Creates a connection, creates a database, and initializes its original data.
     */
    private void initBase() {
        try {
            Connection connection = DriverManager.getConnection(CREATE_DB_URL);
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException("Unable init base", e);
        }
    }

    /**
     * @return the GoodDao object
     */
    public UserDao getUserDao(){
       return userDao;
    }

    /**
     * @return the GoodDao object
     */
    public GoodDao getGoodDao(){
        return goodDao;
    }

    /**
     * @return the OrderDao object
     */
    public OrderDao getOrderDao(){
        return orderDao;
    }

    /**
     * @return the OrderGoodsDao object
     */
    public OrderGoodsDao getOrderGoodsDao(){
        return orderGoods;
    }

    public void setOrderDao(OrderDao orderDao) {
        DAOProvider.orderDao = orderDao;
    }

    public void setUserDao(UserDao userDao) {
        DAOProvider.userDao = userDao;
    }

    public void setGoodDao(GoodDao goodDao) {
        DAOProvider.goodDao = goodDao;
    }

    public void setOrderGoodsDao(OrderGoodsDao orderGoodsGoodsDao) {
        DAOProvider.orderGoods = orderGoodsGoodsDao;
    }
}
