package persistence.repository;

import java.util.List;

public interface CrudRepository<T, ID> {
    <S extends T> S save(S entity);
    T findById(ID id);
    List<T> findAll();
    void update(T entity);
    void deleteById(ID id);
}