package dao;

import models.Tovar;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Рустем on 14.03.2018.
 */
public interface TovaryDao {
    List<Tovar> findAll() throws SQLException;
    boolean create(Tovar tovar) throws SQLException;
    boolean update(int i, String n, int pr) throws SQLException;
}
