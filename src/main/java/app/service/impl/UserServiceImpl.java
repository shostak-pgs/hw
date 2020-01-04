package app.service.impl;

import app.dao.DAOProvider;
import app.dao.UserDao;
import app.dao.impl.UserDaoImpl;
import app.entity.User;
import app.service.ServiceException;
import java.sql.SQLException;

public class UserServiceImpl {
    private UserDao userDao;
    private User user;

    public UserServiceImpl() {
        userDao = DAOProvider.getInstance().getUserDao();
    }

    /**
     * Return the User by name. If he not exist, create new user and the return
     * @param name user's name
     * @return the User
     */
    public Object getUser(String name) throws ServiceException {

        if (!isExist(name)) {
            user = create(name);
        }
        return user;
    }

    /**
     * Check if user exist in base. Return boolean result of checking
     * @param name user's name for checking
     * @return the result
     */
    private boolean isExist(String name) throws ServiceException {
        boolean isPresent = true;

        try {
            user = userDao.getUserByName(name);
            if (user == null) {
                return false;
            }
        } catch (SQLException e) {
            isPresent = false;
            throw new ServiceException("Checking if user exist");
        }
        return isPresent;
    }

    /**
     * Create a new user by the transferred name
     * @param name the user's name
     * @return created user
     */
    private User create(String name) throws ServiceException {
        User user;

        try {
            userDao.addUser(name, name);
            user = userDao.getUserByName(name);

        } catch (SQLException e) {
            throw new ServiceException("Unsuccessful user creation. Check out data base connection", e);
        }
        return user;
    }

    public void setUserDao(UserDaoImpl userDao) {
        this.userDao = userDao;
    }
}
