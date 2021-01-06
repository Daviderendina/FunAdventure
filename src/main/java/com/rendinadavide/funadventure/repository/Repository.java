package com.rendinadavide.funadventure.repository;

import java.util.List;

public interface Repository<T> {

    boolean save(T t);

    T findById(String Id);

    List<T> findAll();

    // update ?

    void delete(T t);

}
