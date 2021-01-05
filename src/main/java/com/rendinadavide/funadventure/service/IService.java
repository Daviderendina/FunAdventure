package com.rendinadavide.funadventure.service;

import java.util.List;

public interface IService <T> {

    T findById(String Id);

    List<T> findAll();

    // update ?

    void delete(T t);

}
