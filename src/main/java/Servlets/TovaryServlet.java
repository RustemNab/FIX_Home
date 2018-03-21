package Servlets;

import dao.TovaryDao;
import dao.impl.TovaryDaoImpl;
import models.Tovar;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;


public class TovaryServlet extends HttpServlet {
    private TovaryDao tovarDao;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);

        String jdbcURL = getServletContext().getInitParameter("jdbcURL");
        String jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
        String jdbcPassword = getServletContext().getInitParameter("jdbcPassword");

        tovarDao = new TovaryDaoImpl(jdbcURL, jdbcUsername, jdbcPassword);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        request.setCharacterEncoding("UTF-8");
        doGet(request, response);
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        super.doPut(request, response);
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        String action = request.getServletPath();

        try {
            switch (action) {
                case "/new":
                    showNewForm(request, response);
                    break;
                case "/tovary":
                    getTovarList(request, response);
                    break;
                case "/add":
                    create(request, response);
                    break;
                default:
                    response.sendRedirect("tovary");
                    break;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void getTovarList(HttpServletRequest request, HttpServletResponse response) throws SQLException,
            IOException, ServletException {
        List<Tovar> tovaryArray = tovarDao.findAll();

        request.setAttribute("tovaryArray", tovaryArray);
        RequestDispatcher dispatcher = request.getRequestDispatcher("ShowTable.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("AddItem.jsp");
        dispatcher.forward(request, response);
    }


    private void create(HttpServletRequest request, HttpServletResponse response) throws SQLException,
            IOException {
        String name = request.getParameter("name");
        int price = Integer.parseInt(request.getParameter("price"));

        Tovar tovar = new Tovar(name, price);
        tovarDao.create(tovar);
        response.sendRedirect("tovary");

    }

}
