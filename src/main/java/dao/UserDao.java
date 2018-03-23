package dao;

import models.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDao {
    List<User> findAll() throws SQLException;
    boolean create(User user) throws SQLException;
    boolean isExist(String login, String password) throws SQLException;
}
