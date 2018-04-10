package servlets;


import dao.UserDao;
import dao.UserDaoHibernate;
import dao.impl.UserDaoHibernateImpl;
import dao.impl.UserDaoImpl;
import org.mindrot.jbcrypt.BCrypt;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by Рустем on 19.03.2018.
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    UserDao userDao;
    UserDaoHibernate userDaoHibernate;

    @Override
    public void init() throws ServletException {
        Properties properties = new Properties();

        try {
            properties.load(new FileInputStream(getServletContext().getRealPath("/WEB-INF/classes/db.properties")));

            //JDBC
            //userDao = new UserDaoImpl(properties);

            //Hibernate
            userDaoHibernate = new UserDaoHibernateImpl(properties);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getServletContext().getRequestDispatcher("/jsp/Login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login_");
        String password = req.getParameter("password");

        //JDBC
//        if (userDao.isExist(login, password)) {
//            HttpSession session = req.getSession();
//            session.setAttribute("user", login);
//
//            req.getServletContext().getRequestDispatcher("/products").forward(req, resp);
//        } else
//            req.getServletContext().getRequestDispatcher("/jsp/SignUp.jsp").forward(req, resp);

        if (userDaoHibernate.isExist(login, password)) {
            HttpSession session = req.getSession();
            session.setAttribute("user", login);

            req.getServletContext().getRequestDispatcher("/products").forward(req, resp);
        } else
            req.getServletContext().getRequestDispatcher("/jsp/SignUp.jsp").forward(req, resp);

    }
}