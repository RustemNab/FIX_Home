package dao.impl;

import dao.UserDao;
import models.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Рустем on 19.03.2018.
 */
public class UserDaoImpl implements UserDao{
    private static String SELECT_ALL_FROM_USERS_SQL = "SELECT * FROM users ORDER BY id";
    private static String INSERT_USER_SQL = "INSERT INTO users (login, password, username) VALUES (?, ?, ?)";

    private String jdbcURL;
    private String jdbcUsername;
    private String jdbcPassword;
    private Connection jdbcConnection;

    public UserDaoImpl(String jdbcURL, String jdbcUsername, String jdbcPassword) {
        this.jdbcURL = jdbcURL;
        this.jdbcUsername = jdbcUsername;
        this.jdbcPassword = jdbcPassword;
    }

    private void connect() throws SQLException {
        if (jdbcConnection == null || jdbcConnection.isClosed()) {
            try {
                Class.forName("org.postgresql.Driver");
            } catch (ClassNotFoundException e) {
                throw new SQLException();
            }
            jdbcConnection = DriverManager.getConnection(
                    jdbcURL, jdbcUsername, jdbcPassword);
        }
    }

    private void disconnect() throws SQLException {
        if (jdbcConnection != null && !jdbcConnection.isClosed()) {
            jdbcConnection.close();
        }
    }

    public List<User> findAll() throws SQLException {
        List<User> res = new ArrayList();
        this.connect();
        Statement statement = this.jdbcConnection.createStatement();
        ResultSet resultSet = statement.executeQuery(SELECT_ALL_FROM_USERS_SQL);

        while(resultSet.next()) {
            int id = resultSet.getInt("id");
            String login = resultSet.getString("login");
            String password = resultSet.getString("password");
            String username = resultSet.getString("username");
            User user = new User(id, login, password, username);
            res.add(user);
        }

        resultSet.close();
        statement.close();
        this.disconnect();
        return res;
    }

    @Override
    public boolean isExist(String login, String password) throws SQLException {
        for (User user : findAll()) {
            if (user.getLogin().equals(login) &&
                    user.getPassword().equals(password)) {
                return true;
            }
        }

        return false;
    }

    public boolean create(User u) throws SQLException {
        this.connect();
        PreparedStatement statement = this.jdbcConnection.prepareStatement(INSERT_USER_SQL);
        statement.setString(1, u.getLogin());
        statement.setString(2, u.getPassword());
        statement.setString(3, u.getUsername());
        boolean res = statement.executeUpdate() > 0;
        statement.close();
        this.disconnect();
        return res;
    }

}
