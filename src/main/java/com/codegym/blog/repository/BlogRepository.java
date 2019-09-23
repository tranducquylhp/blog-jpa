package com.codegym.blog.repository;

import com.codegym.blog.model.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface BlogRepository extends PagingAndSortingRepository<Blog, Long> {
    @Query("SELECT c FROM Blog c ORDER BY c.dateCreate DESC")
    Page<Blog> sort(Pageable pageable);
}
