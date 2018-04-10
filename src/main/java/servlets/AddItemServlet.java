package servlets;

import dao.ProductsDao;
import dao.ProductsDaoHibernate;
import dao.impl.ProductsDaoHibernateImpl;
import dao.impl.ProductsDaoJdbcTemplateImpl;
import models.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

@WebServlet("/add")
public class AddItemServlet extends HttpServlet{
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
        req.getServletContext().getRequestDispatcher("/jsp/AddItem.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String price = req.getParameter("price");

        //Jdbc
        //productsDao.save(new Product(name, Integer.valueOf(price)));

        //Hiberenate
        productDaoHibernate.save(new models.hibernate.Product(name, Integer.valueOf(price)));

        req.getServletContext().getRequestDispatcher("/products").forward(req, resp);
    }
}
