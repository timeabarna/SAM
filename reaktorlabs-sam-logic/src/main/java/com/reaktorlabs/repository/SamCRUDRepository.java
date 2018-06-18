package com.reaktorlabs.repository;

import java.util.List;

public interface SamCRUDRepository<K, T> {
    void create(T entity);
    T readById(K id);
    List<T> readAll();
    void update(T entity);
    void updateById(K id);
    void deleteById(K id);
    void delete(T entity);
}
