package app.service.impl;

import app.dao.DAOProvider;
import app.dao.GoodDao;
import app.dao.OrderGoodsDao;
import app.entity.Good;
import app.entity.OrderGoods;
import app.service.ServiceException;
import app.service.ServiceProvider;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.*;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class OrderGoodsServiceImplTest {
    @Mock
    private DAOProvider daoMock;
    @Mock
    private GoodDao goodDaoMock;
    @Mock
    private OrderGoodsDao orGoodsDaoMock;
    @Mock
    private OrderGoodsServiceImpl orGoodsServiceMock;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getGoods() throws SQLException, ServiceException {
        //Given
        List<OrderGoods> inCurrentOrder = Arrays.asList(new OrderGoods(1L, 2L, 2L),
                                                        new OrderGoods(2L, 2L, 3L));
        List<Good> expected = Arrays.asList(new Good(2L, "Toy", 2.0),
                                         new Good(3L, "Car", 80.0));

        when(daoMock.getOrderGoodsDao()).thenReturn(orGoodsDaoMock);
        when(orGoodsDaoMock.getByOrderId(2L)).thenReturn(inCurrentOrder);

        when(daoMock.getGoodDao()).thenReturn(goodDaoMock);
        when(goodDaoMock.getGood(2L)).thenReturn( new Good(2L, "Toy", 2.0));
        when(goodDaoMock.getGood(3L)).thenReturn( new Good(3L, "Car", 80.0));

        ServiceProvider.getInstance().getOrderGoodsService().setDao(daoMock);
        //When
        List<Good> actual = ServiceProvider.getInstance().getOrderGoodsService().getGoods(2L);
        //Then
        assertEquals(expected, actual);
    }

            @Test
    public void add() {
    }

    @Test
    public void getOrderedGoods() throws ServiceException {
        List<Good> goodsList = Arrays.asList(new Good(1L, "Toy", 2.0),
                                            new Good(2L, "Toy", 2.0),
                                            new Good(3L, "House", 255.0),
                                            new Good(4L, "Car", 80.0));

        Map<String, Integer> expectedMap = new HashMap<>();
        expectedMap.put("Toy (2.0 $)", 2);
        expectedMap.put("Toy (255.0 $)", 1);
        expectedMap.put("Toy (80.0 $)", 1);

        ServiceProvider.getInstance().getOrderGoodsService().setDao(daoMock);//setDao - внедрение бина через сеттер
        ServiceProvider serviceProviderMock = mock(ServiceProvider.class);
        setMock(serviceProviderMock);

        when(serviceProviderMock.getOrderGoodsService()).thenReturn(orGoodsServiceMock);
        when(orGoodsServiceMock.getGoods(2L)).thenReturn(goodsList);
        when(orGoodsServiceMock.getOrderedGoods(2L)).thenReturn(expectedMap);
        //When
        Map<String, Integer> actual = ServiceProvider.getInstance().getOrderGoodsService(). getOrderedGoods(2L);
        //Then
        assertEquals(expectedMap, actual);
    }

    private void setMock(ServiceProvider mock) {
        try {
            Field instance = ServiceProvider.class.getDeclaredField("instance");
            instance.setAccessible(true);
            instance.set(instance, mock);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }



}