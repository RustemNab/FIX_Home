package servlets;

import dao.ProductsDao;
import dao.ProductsDaoHibernate;
import models.Product;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet("/products")
public class ProductsServlet extends HttpServlet {

    ProductsDao productsDao;
    ProductsDaoHibernate productDaoHibernate;

    @Override
    public void init() throws ServletException {
        //Jdbc
        ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
        productsDao = (ProductsDao) context.getBean("productDaoJdbcTemplate");

//        //Hibernate
//        try {
//            Properties properties = new Properties();
//            properties.load(new FileInputStream(getServletContext().getRealPath("/WEB-INF/classes/db.properties")));
//
//            productDaoHibernate = new ProductsDaoHibernateImpl(properties);
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Jdbc
        List<Product> productList = productsDao.findAll();

        //Hibernate
        //List<models.hibernate.Product> productList = productDaoHibernate.findAll();

        req.setAttribute("productsArray", productList);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/ShowTable.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
