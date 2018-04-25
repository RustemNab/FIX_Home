package servlets;

import dao.ProductsDao;
import dao.ProductsDaoHibernate;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/delete")
public class DeleteItemServlet extends HttpServlet {

    ProductsDao productsDao;
    ProductsDaoHibernate productDaoHibernate;

    @Override
    public void init() throws ServletException {

        //Jdbc
        ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");

        productsDao = (ProductsDao) context.getBean("productDaoJdbcTemplate");

        //Hibernate
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
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String id = req.getParameter("id_");

        //Jdbc
        productsDao.delete(Integer.valueOf(id));

        //Hibernate
        //productDaoHibernate.delete(Long.valueOf(id));

        req.getServletContext().getRequestDispatcher("/products").forward(req, resp);
    }
}
