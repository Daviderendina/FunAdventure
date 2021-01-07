package com.rendinadavide.funadventure.repository;

import java.util.List;

public interface Repository<T> {

    boolean save(T t);

    T findById(String Id);

    List<T> findAll();

    void update(T t);

    void delete(T t);

}
