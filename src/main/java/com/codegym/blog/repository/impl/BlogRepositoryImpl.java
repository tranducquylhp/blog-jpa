package com.codegym.blog.repository.impl;

import com.codegym.blog.model.Blog;
import com.codegym.blog.repository.BlogRepository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
public class BlogRepositoryImpl implements BlogRepository {
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Blog> findAll() {
        TypedQuery<Blog> query = em.createQuery("select c from Blog c", Blog.class);
        return query.getResultList();
    }

    @Override
    public void save(Blog blog) {
        if (findById(blog.getId())!=null){
            em.merge(blog);
        } else {
            em.persist(blog);
        }
    }

    @Override
    public void delete(int id) {
        Blog blog = findById(id);
        if (blog !=null){
            em.remove(blog);
        }
    }

    @Override
    public Blog findById(int id) {
        TypedQuery<Blog> query = em.createQuery("select c from Blog c where c.id = :id", Blog.class);
        query.setParameter("id", id);
        try {
            return query.getSingleResult();
        } catch (NoResultException e){
            return null;
        }
    }
}
