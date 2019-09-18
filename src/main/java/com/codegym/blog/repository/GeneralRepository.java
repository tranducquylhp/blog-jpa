package com.codegym.blog.repository;

import java.util.List;

public interface GeneralRepository<E> {
    List<E> findAll();
    void save(E e);
    void delete(int index);
    E findById(int id);
}
