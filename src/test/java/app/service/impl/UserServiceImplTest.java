package app.service.impl;

import app.entity.User;
import app.service.ServiceException;
import app.service.ServiceProvider;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.lang.reflect.Field;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class UserServiceImplTest {

    @Mock
    private ServiceProvider serviceProviderMock;

    @Mock
    private UserServiceImpl userServiceMock;

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
    public void testGetUser() throws ServiceException {
        //Given
        User expected = new User(1, "Archi", "112");
        when(ServiceProvider.getInstance().getUserService()).thenReturn(userServiceMock);
        when(serviceProviderMock.getUserService()).thenReturn(userServiceMock);
        when(userServiceMock.getUser("Archi")).thenReturn(expected);
        //When
        User actual = (User)ServiceProvider.getInstance().getUserService().getUser("Archi");
        //Then
        assertEquals(expected, actual);
    }
}