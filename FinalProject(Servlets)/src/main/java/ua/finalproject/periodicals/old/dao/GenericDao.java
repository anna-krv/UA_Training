package ua.finalproject.periodicals.old.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface GenericDao<T, Key> extends AutoCloseable {
    void create (T entity) throws SQLException;
    Optional<T> findById(Key id);
    List<T> findAll();
    void update(T entity) throws SQLException;
    void delete(Key id);
}
