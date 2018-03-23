package Servlets;

import dao.UserDao;
import dao.impl.UserDaoImpl;
import models.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Рустем on 19.03.2018.
 */

@WebServlet("/signup")
public class SignUpServlet extends HttpServlet {
    private UserDao userDao;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);

        String jdbcURL = getServletContext().getInitParameter("jdbcURL");
        String jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
        String jdbcPassword = getServletContext().getInitParameter("jdbcPassword");

        userDao = new UserDaoImpl(jdbcURL, jdbcUsername, jdbcPassword);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<User> users = null;
        try {
            users = userDao.findAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        request.setAttribute("usersFromServer", users);
        RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/SignUp.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login_");
        String password = request.getParameter("password_");
        String name = request.getParameter("name_");
        User user = new User(login, password, name);
        try {
            userDao.create(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        doGet(request, response);
    }
}
