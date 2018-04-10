package dao;

import models.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDao extends CrudDao<User> {
    boolean isExist(String login, String password);
}
