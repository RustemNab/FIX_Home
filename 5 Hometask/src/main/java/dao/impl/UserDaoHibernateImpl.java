package dao.impl;

import dao.UserDaoHibernate;
import models.hibernate.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import org.hibernate.query.Query;
import org.mindrot.jbcrypt.BCrypt;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class UserDaoHibernateImpl implements UserDaoHibernate {

    private SessionFactory sessionFactory;

    public UserDaoHibernateImpl(Properties properties) {

        String dbUrl = properties.getProperty("db.url");
        String dbUsername = properties.getProperty("db.username");
        String dbPassword = properties.getProperty("db.password");
        String driverClassName = properties.getProperty("db.driverClassName");

        Configuration configuration = new Configuration();
        configuration.setProperty("hibernate.connection.url", dbUrl);
        configuration.setProperty("hibernate.connection.username", dbUsername);
        configuration.setProperty("hibernate.connection.password", dbPassword);
        configuration.setProperty("hibernate.connection.driver_class", driverClassName);
        configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQL95Dialect");
        configuration.setProperty("hibernate.hbm2ddl.auto", "update");
        configuration.addAnnotatedClass(User.class);
        configuration.setProperty("hibernate.show_sql", "true");
        this.sessionFactory = configuration.buildSessionFactory();
    }

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<User>();
        Transaction trns = null;
        Session session = this.sessionFactory.openSession();
        try {
            trns = session.beginTransaction();
            users = session.createQuery("from users").list();
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            session.flush();
        }
        return users;
    }

    @Override
    public User find(int id) {
        User user = null;
        Transaction trns = null;
        Session session = this.sessionFactory.openSession();
        try {
            trns = session.beginTransaction();
            String queryString = "from users where id = :id";
            Query query = session.createQuery(queryString);
            query.setInteger("id", (int)id);
            user = (User) query.uniqueResult();
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            session.flush();
        }
        return user;
    }

    @Override
    public void delete(int id) {
        Transaction trns = null;
        Session session = this.sessionFactory.openSession();
        try {
            trns = session.beginTransaction();
            User user = (User) session.load(User.class, id);
            session.delete(user);
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            if (trns != null) {
                trns.rollback();
            }
            e.printStackTrace();
        } finally {
            session.flush();
        }

    }

    @Override
    public void save(User model) {
        Transaction trns = null;
        Session session = this.sessionFactory.openSession();
        try {
            trns = session.beginTransaction();
            session.save(model);
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            if (trns != null) {
                trns.rollback();
            }
            e.printStackTrace();
        } finally {
            //session.flush();
        }
    }

    @Override
    public void update(User model, int id) {
        Transaction trns = null;
        Session session = this.sessionFactory.openSession();
        try {
            trns = session.beginTransaction();
            session.update(model);
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            if (trns != null) {
                trns.rollback();
            }
            e.printStackTrace();
        } finally {
            session.flush();
        }
    }

    @Override
    public boolean isExist(String login, String password) {
        for (User user : findAll()) {
            if (user.getLogin().equals(login) && BCrypt.checkpw(password, user.getPassword()))
                return true;
        }

        return false;
    }
}