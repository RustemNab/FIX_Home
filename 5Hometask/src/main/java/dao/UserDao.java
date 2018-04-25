package dao;

import models.User;

public interface UserDao extends CrudDao<User> {
    boolean isExist(String login, String password);
}
