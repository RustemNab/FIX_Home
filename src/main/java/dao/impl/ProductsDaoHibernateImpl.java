package dao.impl;

import dao.ProductsDaoHibernate;
import models.hibernate.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.annotations.OrderBy;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.springframework.core.annotation.Order;

import javax.persistence.OrderColumn;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ProductsDaoHibernateImpl implements ProductsDaoHibernate {

    private SessionFactory sessionFactory;

    public ProductsDaoHibernateImpl(Properties properties) {
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
        configuration.addResource("Products.hbm.xml");
        configuration.addAnnotatedClass(Product.class);
        configuration.setProperty("hibernate.show_sql", "true");
        this.sessionFactory = configuration.buildSessionFactory();
    }

    @Override
    public List<Product> findAll() {
        List<Product> products = new ArrayList<Product>();
        Transaction trns = null;
        Session session = this.sessionFactory.openSession();
        try {
            trns = session.beginTransaction();
            products = session.createQuery("from Product p order by p.id").list();
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }
        return products;
    }

    @Override
    public Product find(int id) {
        Product product = null;
        Transaction trns = null;
        Session session = this.sessionFactory.openSession();
        try {
            trns = session.beginTransaction();
            String queryString = "from Product where id = :id";
            Query query = session.createQuery(queryString);
            query.setInteger("id", (int) id);
            product = (Product) query.uniqueResult();
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }
        return product;
    }

    @Override
    public void delete(int id) {
        Transaction trns = null;
        Session session = this.sessionFactory.openSession();
        try {
            trns = session.beginTransaction();
            Product product = (Product) session.load(Product.class, id);
            session.delete(product);
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            if (trns != null) {
                trns.rollback();
            }
            e.printStackTrace();
        } finally {
            //session.flush();
            session.close();
        }
    }

    @Override
    public void save(Product model) {
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
            session.close();
        }
    }

    @Override
    public void update(Product model, int id) {
        Transaction trns = null;
        model.setId(id);
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
            //session.flush();
            session.close();
        }
    }
}
