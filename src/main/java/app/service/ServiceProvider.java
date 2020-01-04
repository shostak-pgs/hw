package app.service;

import app.service.impl.GoodsServiceImpl;
import app.service.impl.OrderGoodsServiceImpl;
import app.service.impl.OrderServiceImpl;
import app.service.impl.UserServiceImpl;
import org.springframework.context.ApplicationContext;

public class ServiceProvider {
    static ApplicationContext context = ContextUtil.getContext();

    public static UserServiceImpl userServiceImpl;
    public static OrderServiceImpl orderServiceImpl;
    public static OrderGoodsServiceImpl orderGoodsService;
    public static GoodsServiceImpl goodsService;

    private static ServiceProvider instance = (ServiceProvider) context.getBean("serviceProvider");

    public ServiceProvider(UserServiceImpl userServiceImpl, OrderServiceImpl orderServiceImpl, OrderGoodsServiceImpl orderGoodsService, GoodsServiceImpl goodsService) {
        ServiceProvider.userServiceImpl = userServiceImpl;
        ServiceProvider.orderServiceImpl = orderServiceImpl;
        ServiceProvider.orderGoodsService = orderGoodsService;
        ServiceProvider.goodsService = goodsService;
    }

    /**
     * Return the single provider object
     * @return the {@link ServiceProvider}
     */
    public static ServiceProvider getInstance(){
    return instance;
    }

    /**
     * @return the UserService object
     */
    public UserServiceImpl getUserService(){
        return userServiceImpl;
    }

    /**
     * @return the OrderService object
     */
    public OrderServiceImpl getOrderService(){
        return orderServiceImpl;
    }

    /**
     * @return the OrderGoodsService object
     */
    public OrderGoodsServiceImpl getOrderGoodsService(){
        return orderGoodsService;
    }

    /**
     * @return the GoodsService object
     */
    public GoodsServiceImpl getGoodsService(){
        return goodsService;
    }
}
