package app.service.impl;

import app.entity.User;
import app.service.ServiceException;
import app.service.ServiceProvider;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.lang.reflect.Field;
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class OrderServiceImplTest {

    @Mock
    private ServiceProvider serviceProviderMock;

    @Mock
    private OrderServiceImpl orderServiceMock;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        setMock(serviceProviderMock);
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

    @Test
    public void testGet() throws ServiceException {
        //Given
        User expected = new User(1, "3", "23.0");
        when(ServiceProvider.getInstance().getOrderService()).thenReturn(orderServiceMock);
        when(orderServiceMock.get("3")).thenReturn(expected);
        //When
        User actual = (User)ServiceProvider.getInstance().getOrderService().get("3");
        //Then
        assertEquals(expected, actual);
    }

    @Test
    public void testUpdateByUserId() throws SQLException {
        //Given
        Boolean expected = false;
        when(ServiceProvider.getInstance().getOrderService()).thenReturn(orderServiceMock);
        when(orderServiceMock.updateByUserId(2.5, 2L)).thenReturn(true);
        //When
        Boolean actual = ServiceProvider.getInstance().getOrderService().updateByUserId(5.0, 3L);
        //Then
        assertEquals(expected, actual);
    }

}
