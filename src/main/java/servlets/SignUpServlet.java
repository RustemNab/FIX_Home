package servlets;

import dao.UserDao;
import dao.UserDaoHibernate;
import dao.impl.UserDaoHibernateImpl;
import dao.impl.UserDaoImpl;
import models.User;
import org.mindrot.jbcrypt.BCrypt;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

@WebServlet("/signup")
public class SignUpServlet extends HttpServlet{
    UserDao userDao;
    UserDaoHibernate userDaoHibernate;

    @Override
    public void init() throws ServletException {
        Properties properties = new Properties();

        try {
            properties.load(new FileInputStream(getServletContext().getRealPath("/WEB-INF/classes/db.properties")));

            //Jdbc
            //userDao = new UserDaoImpl(properties);

            //Hibernate
            userDaoHibernate = new UserDaoHibernateImpl(properties);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getServletContext().getRequestDispatcher("/jsp/SignUp.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("name_");
        String login = req.getParameter("login_");
        String password = req.getParameter("password_");

      //JDBC
//        if (userDao.isExist(login, BCrypt.hashpw(password,BCrypt.gensalt())))
//            doGet(req, resp);
//        else {
//            userDao.save(new models.User(login, BCrypt.hashpw(password,BCrypt.gensalt()), username));
//            HttpSession session = req.getSession();
//            session.setAttribute("user", login);
//
//            req.getServletContext().getRequestDispatcher("/products").forward(req, resp);
//        }

        //Hibernate
        if (userDaoHibernate.isExist(login, password))
            doGet(req, resp);
        else {
            userDaoHibernate.save(new models.hibernate.User(login, BCrypt.hashpw(password,BCrypt.gensalt()), username));

            HttpSession session = req.getSession();
            session.setAttribute("user", login);

            req.getServletContext().getRequestDispatcher("/products").forward(req, resp);
        }
    }
}
