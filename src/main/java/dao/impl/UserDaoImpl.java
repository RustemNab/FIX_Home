package dao.impl;

import dao.UserDao;
import models.User;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import res.MyProperties;

import java.sql.*;
import java.util.List;

/**
 * Created by Рустем on 19.03.2018.
 */
public class UserDaoImpl implements UserDao{
    private Connection connection;

    DriverManagerDataSource dataSource = new DriverManagerDataSource();

    //language=SQL
    private final String SQL_SELECT_ALL =
            "SELECT * from users";

    //language=SQL
    private final String SQL_SELECT_BY_ID =
            "SELECT * from users WHERE id = ?";

    //language=SQL
    private final String SQL_UPDATE_BY_ID =
            "UPDATE users set login = ?, password = ?, name = ? WHERE id = ?";

    //language=SQL
    private final String SQL_DELETE_BY_ID =
            "DELETE UNCOMMITTED WHERE id = ?";

    //language=SQL
    private final String SQL_INSERT =
            "INSERT INTO users(login, password, name) VALUES (?, ?, ?)";

    //language=SQL
    private final String SQL_SELECT_BY_LOGIN =
            "SELECT * FROM users WHERE login = ?";

    public UserDaoImpl(MyProperties properties) {
        try {

            String dbUrl = properties.getUrl();
            String dbUsername = properties.getUsername();
            String dbPassword = properties.getPassword();
            String driverClassName = properties.getDriverClassName();

            dataSource.setUsername(dbUsername);
            dataSource.setPassword(dbPassword);
            dataSource.setUrl(dbUrl);
            dataSource.setDriverClassName(driverClassName);

            this.connection = dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List findAll() {
        List<User> users = null;

        try {
            Statement statement = this.connection.createStatement();

            ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL);

            while (resultSet.next())
                users.add(new User(resultSet.getInt("id"),
                        resultSet.getString("login"),
                        resultSet.getString("password"),
                        resultSet.getString("name")));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }

    @Override
    public User find(int id) {
        User user = null;
        try {
            PreparedStatement statement = this.connection.prepareStatement(SQL_SELECT_BY_ID);
            statement.setLong(1, id);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next())
                user = new User(resultSet.getInt("id"),
                        resultSet.getString("login"),
                        resultSet.getString("password"),
                        resultSet.getString("name"));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    @Override
    public void delete(int id) {
        try {
            PreparedStatement statement = this.connection.prepareStatement(SQL_DELETE_BY_ID);
            statement.setLong(1, id);

            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void save(User model) {
        try {
            PreparedStatement statement = this.connection.prepareStatement(SQL_INSERT);
            statement.setString(1, model.getLogin());
            statement.setString(2, BCrypt.hashpw(model.getPassword(),BCrypt.gensalt()));
            statement.setString(3, model.getUsername());

            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(User model, int id) {
        try {
            PreparedStatement statement = this.connection.prepareStatement(SQL_UPDATE_BY_ID);
            statement.setString(1, model.getLogin());
            statement.setString(2,  BCrypt.hashpw(model.getPassword(),BCrypt.gensalt()));
            statement.setString(3, model.getUsername());
            statement.setInt(4, id);

            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean isExist(String login, String password) {
        try {
            PreparedStatement statement = this.connection.prepareStatement(SQL_SELECT_BY_LOGIN);
            statement.setString(1, login);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next())
                if (BCrypt.checkpw(password, resultSet.getString("password")))
                    return true;

            return false;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}
