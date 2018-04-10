package servlets;

import dao.ProductsDao;
import dao.ProductsDaoHibernate;
import dao.impl.ProductsDaoHibernateImpl;
import dao.impl.ProductsDaoJdbcTemplateImpl;
import models.Product;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;


@WebServlet("/products")
public class ProductsServlet extends HttpServlet {

    ProductsDao productsDao;
    ProductsDaoHibernate productDaoHibernate;

    @Override
    public void init() throws ServletException {
        Properties properties = new Properties();

        try {
            properties.load(new FileInputStream(getServletContext().getRealPath("/WEB-INF/classes/db.properties")));

            //Jdbc
            //productsDao = new ProductsDaoJdbcTemplateImpl(properties);

            //Hiberenate
            productDaoHibernate = new ProductsDaoHibernateImpl(properties);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Jdbc
        //List<Product> productList = productsDao.findAll();

        //Hibernate
        List<models.hibernate.Product> productList = productDaoHibernate.findAll();
        req.setAttribute("productsArray", productList);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/ShowTable.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
