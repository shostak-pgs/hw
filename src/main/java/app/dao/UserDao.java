package app.dao;

import app.entity.User;
import java.sql.SQLException;
import java.util.List;

public interface UserDao {

    /**
     * Returns the user by the transferred name
     * @param userName user's name
     * @return the {@link User}
     * @throws SQLException an exception that provides information on a database access
     * error or other errors.
     */
    User getUserByName(String userName) throws SQLException;

    /**
     * Return list contains all users in base
     * @return the list with all users
     * @throws SQLException an exception that provides information on a database access
     * error or other errors.
     */
    List<User> getAllUsers() throws SQLException;

    /**
     * Add user in base by the transferred name
     * @param userName user's name
     * @param password user's password
     * @throws SQLException an exception that provides information on a database access
     * error or other errors.
     */
    void addUser(String userName, String password) throws SQLException;
}
