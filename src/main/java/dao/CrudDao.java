package dao;

import java.util.List;
import java.util.Optional;

public interface CrudDao<T> {
    T find(int id);
    void save(T model);
    void update(T model, int id);
    void delete(int id);

    List<T> findAll();
}