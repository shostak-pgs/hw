package app.dao.impl;

import app.dao.UserDao;
import app.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {
    private static final String SELECT_USER_SQL_STATEMENT = "SELECT id, userName, password FROM Users WHERE userName LIKE ?";
    private static final String SELECT_ALL_USERS_SQL_STATEMENT = "SELECT * FROM Users";
    private static final String INSERT_USER_SQL_STATEMENT = "INSERT INTO Users (userName, password) VALUES (?,?)";

    private DataSource provider;

    /**
     * Returns the user by the transferred name
     * @param userName user's name
     * @return the {@link User}
     * @throws SQLException an exception that provides information on a database access
     * error or other errors.
     */
    @Override
    public User getUserByName(String userName) throws SQLException {
        User user = null;
        try (PreparedStatement st = provider.getConnection().prepareStatement(SELECT_USER_SQL_STATEMENT )){
            st.setString(1, userName);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                user = new User(rs.getLong("id"), rs.getString("userName"), rs.getString("password"));////
            }
            rs.close();
        }
        return user;
    }

    /**
     * Return list contains all users in base
     * @return the list with all users
     * @throws SQLException an exception that provides information on a database access
     * error or other errors.
     */
    @Override
    public List<User> getAllUsers() throws SQLException {
        List<User> userList = new ArrayList<>();
        try (PreparedStatement st = provider.getConnection().prepareStatement(SELECT_ALL_USERS_SQL_STATEMENT );
             ResultSet rs = st.executeQuery()) {
            while (rs.next()) {
                userList.add(new User(rs.getLong("id"), rs.getString("userName"),
                        rs.getString("password")));////
            }
        }
        return userList;
    }

    /**
     * Add user in base by the transferred name
     * @param userName user's name
     * @param password user's password
     * @throws SQLException an exception that provides information on a database access
     * error or other errors.
     */
    @Override
    public void addUser(String userName, String password) throws SQLException {
        try (PreparedStatement st = provider.getConnection().prepareStatement(INSERT_USER_SQL_STATEMENT)) {
            st.setString(1, userName);
            st.setString(2, password);
            st.executeUpdate();
        }
    }

    @Autowired
    public void setProvider(DataSource provider) {
        this.provider = provider;
    }
}
