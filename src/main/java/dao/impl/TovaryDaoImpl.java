package dao.impl;

import dao.TovaryDao;
import models.Tovar;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Рустем on 14.03.2018.
 */
public class TovaryDaoImpl implements TovaryDao {
    private static String SELECT_ALL_FROM_TOVARY_SQL = "SELECT * FROM Tovary ORDER BY id";

    private String jdbcURL;
    private String jdbcUsername;
    private String jdbcPassword;
    private Connection jdbcConnection;

    public TovaryDaoImpl(String jdbcURL, String jdbcUsername, String jdbcPassword) {
        this.jdbcURL = jdbcURL;
        this.jdbcUsername = jdbcUsername;
        this.jdbcPassword = jdbcPassword;
    }

    private void connect() throws SQLException {
        if (jdbcConnection == null || jdbcConnection.isClosed()) {
            try {
                Class.forName("org.postgresql.Driver");
            } catch (ClassNotFoundException e) {
                throw new SQLException();
            }
            jdbcConnection = DriverManager.getConnection(
                    jdbcURL, jdbcUsername, jdbcPassword);
        }
    }

    private void disconnect() throws SQLException {
        if (jdbcConnection != null && !jdbcConnection.isClosed()) {
            jdbcConnection.close();
        }
    }

    public List<Tovar> findAll() throws SQLException {
        List<Tovar> listTovar = new ArrayList();

        connect();

        Statement statement = jdbcConnection.createStatement();
        ResultSet resultSet = statement.executeQuery(SELECT_ALL_FROM_TOVARY_SQL);

        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            int price = resultSet.getInt("price");

            Tovar tovar = new Tovar(id, name, price);

            listTovar.add(tovar);
        }

        resultSet.close();
        statement.close();

        disconnect();

        return listTovar;
    }


}
