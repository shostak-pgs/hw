package app.service.impl;

import app.dao.DAOProvider;
import app.entity.Good;
import app.entity.OrderGoods;
import app.service.ServiceException;
import app.utils.GoodsUtil;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderGoodsServiceImpl {
    private DAOProvider dao;

    /**
     * Returns the list contains goods in order. Sampling by transferred id
     * @param orderId id of order for list building
     * @return the list
     */
    public List<Good> getGoods(Long orderId) throws ServiceException {
        List<Good> orderedGoods;
        try {
            List<OrderGoods> inCurrentOrder = dao.getOrderGoodsDao().getByOrderId(orderId);
            orderedGoods = getGoodsInCurrentOrder(inCurrentOrder);

        } catch (SQLException e) {
            throw new ServiceException("Exception in data base connection,", e);
        }
        return orderedGoods;
    }

    /**
     * Returns the map contains name of item, its price and number of it in order
     * @param orderId id of order for map building
     * @return the map
     */
    public Map<String, Integer> getOrderedGoods(Long orderId) throws ServiceException {
        Map<String, Integer> map = new HashMap<>();
        List<Good> orderedGoods = getGoods(orderId);
        for(Good good : orderedGoods) {
            String item = (good.getName() + " (" + good.getPrice() + " $)");
            int value = 1;
            if (map.containsKey(item)) {
                value = map.get(item) + 1;
                map.remove(item);
            }
            map.put(item, value);
        }
        return map;
    }

    /**
     * Added product by name to the OrderGoods db table
     * @param name name of product to be added
     * @param orderId id of order in which the product included
     */
    public void add(String name, Long orderId) throws ServiceException {
        try {
            Good good = dao.getGoodDao().getGood(GoodsUtil.getName(name));
            dao.getOrderGoodsDao().addToOrderGood(orderId, good.getId());
        } catch (SQLException e) {
            throw new ServiceException("Exception with basket. Try again later", e);
        }
    }


    /**
     * Returns the list contains goods in order. Sampling by transferred OrderGoods list
     * @param goodsInCurrentOrder the OrderGoods list for good list creation
     * @return the list
     */
  private List<Good> getGoodsInCurrentOrder(List<OrderGoods> goodsInCurrentOrder) throws SQLException {
      List<Good> orderedGoods = new ArrayList<>();
      for (OrderGoods current : goodsInCurrentOrder) {
          orderedGoods.add(dao.getGoodDao().getGood(current.getGoodId()));
      }
      return orderedGoods;
  }

    /**
     * Setter for spring bean
     * @param dao the {@link DAOProvider}
     */
    public void setDao(DAOProvider dao) {
        this.dao = dao;
    }
}
