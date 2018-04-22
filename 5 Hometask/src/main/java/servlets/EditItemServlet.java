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
import java.util.ArrayList;
import java.util.List;

@WebServlet("/edit")
public class EditItemServlet extends HttpServlet {

    ProductsDao productsDao;
    ProductsDaoHibernate productDaoHibernate;

    @Override
    public void init() throws ServletException {

        //Jdbc
        ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");

        productsDao= (ProductsDao) context.getBean("productDaoJdbcTemplate");

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
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idString = req.getParameter("id_");
        Integer id = Integer.valueOf(idString);

        //Jdbc
        List<Product> productList = new ArrayList<>();
        Product product = this.productsDao.find(id);

        //Hiberenate
//        List<models.hibernate.Product> productList = new ArrayList<>();
//        models.hibernate.Product product = this.productDaoHibernate.find(id);

        productList.add(product);

        req.setAttribute("editItem", productList);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/EditItem.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id_");
        String name = req.getParameter("name_");
        String price = req.getParameter("price_");

        //Jdbc
        productsDao.update(new Product(name, Integer.valueOf(price)), Integer.valueOf(id));

        //Hibernate
//        productDaoHibernate.update(new models.hibernate.Product(name, Integer.valueOf(price))
//                , Integer.valueOf(id));

        req.getServletContext().getRequestDispatcher("/products").forward(req, resp);
    }
}