package Servlets;


import dao.UserDao;
import dao.impl.UserDaoImpl;
import models.User;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Рустем on 19.03.2018.
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
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
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getServletContext().getRequestDispatcher("/Login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // вытаскиваем из запроса имя пользователя и его пароль
        String name = request.getParameter("name");
        String password = request.getParameter("password");

        // если пользователь есть в системе
        try {
            if (userDao.isExist(name, password)) {
                // создаем для него сессию
                HttpSession session = request.getSession();
                // кладем в атрибуты сессии атрибут user с именем пользователя
                session.setAttribute("user", name);
                // перенаправляем на страницу home
                request.getServletContext().getRequestDispatcher("/tovary").forward(request, response);
            } else {
                response.sendRedirect(request.getContextPath() + "/signup");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
