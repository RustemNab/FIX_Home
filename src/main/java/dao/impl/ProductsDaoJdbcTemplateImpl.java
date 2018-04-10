package dao.impl;

import dao.ProductsDao;
import models.Product;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

/**
 * Created by Рустем on 09.04.2018.
 */
public class ProductsDaoJdbcTemplateImpl implements ProductsDao{
    private JdbcTemplate template;
    DriverManagerDataSource dataSource = new DriverManagerDataSource();

    //language=SQL
    private final String SQL_SELECT_ALL =
            "SELECT * from products ORDER BY id";

    //language=SQL
    private final String SQL_SELECT_BY_ID =
            "SELECT * from products WHERE id = ?";

    //language=SQL
    private final String SQL_UPDATE_BY_ID =
            "UPDATE products SET name = ?, price = ? WHERE id = ?";

    //language=SQL
    private final String SQL_DELETE_BY_ID =
            "DELETE FROM  products WHERE id = ?";

    //language=SQL
    private final String SQL_INSERT =
            "INSERT INTO products(name, price) VALUES (?, ?)";

    public ProductsDaoJdbcTemplateImpl(Properties properties) {
        String dbUrl = properties.getProperty("db.url");
        String dbUsername = properties.getProperty("db.username");
        String dbPassword = properties.getProperty("db.password");
        String driverClassName = properties.getProperty("db.driverClassName");

        dataSource.setUsername(dbUsername);
        dataSource.setPassword(dbPassword);
        dataSource.setUrl(dbUrl);
        dataSource.setDriverClassName(driverClassName);

        this.template = new JdbcTemplate(dataSource);
    }

    private RowMapper<Product> productRowMapper = new RowMapper<Product>() {
        @Override
        public Product mapRow(ResultSet resultSet, int i) throws SQLException {
            return new Product(resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getInt("price"));
        }
    };

    @Override
    public List<Product> findAll() {
        return template.query(SQL_SELECT_ALL, productRowMapper);
    }

    @Override
    public Product find(int id) {
        Product product = null;
        product = this.template.queryForObject(SQL_SELECT_BY_ID, new Object[]{id}, productRowMapper);
        return product;
    }

    @Override
    public void delete(int id) {
        this.template.update(SQL_DELETE_BY_ID, id);
    }

    @Override
    public void save(Product model) {
        this.template.update(SQL_INSERT, model.getName(), model.getPrice());
    }

    @Override
    public void update(Product model, int id) {
        this.template.update(SQL_UPDATE_BY_ID, model.getName(), model.getPrice(), id);
    }
}
