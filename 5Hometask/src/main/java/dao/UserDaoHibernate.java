package dao;

import models.hibernate.User;

public interface UserDaoHibernate extends CrudDao<User>{
    boolean isExist(String login, String password);
}
