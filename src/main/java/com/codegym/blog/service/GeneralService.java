package com.codegym.blog.service;

import java.util.List;

public interface GeneralService<E> {
    List<E> findAll();
    void save(E e);
    void delete(int index);
    E findById(int id);
}
