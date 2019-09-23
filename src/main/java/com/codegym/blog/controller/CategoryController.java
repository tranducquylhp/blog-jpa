package com.codegym.blog.controller;

import com.codegym.blog.model.Category;
import com.codegym.blog.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
public class CategoryController {
    @Autowired
    public Environment env;
    @Autowired
    public CategoryService categoryService;

    @GetMapping("create-category")
    public ModelAndView showCreateForm(){
        ModelAndView modelAndView = new ModelAndView("/category/create");
        modelAndView.addObject("category", new Category());
        return modelAndView;
    }

    @PostMapping("categories")
    public ModelAndView create(@ModelAttribute("category") Category category, Pageable pageable){
        categoryService.save(category);

        Page<Category> categories = categoryService.findAll(pageable);
        ModelAndView modelAndView = new ModelAndView("/category/list");
        modelAndView.addObject("message","Create successfully");
        modelAndView.addObject("categories", categories);
        return modelAndView;
    }

    @GetMapping("/categories")
    public ModelAndView listCategories(@RequestParam("s") Optional<String> s, Pageable pageable){
        Page<Category> categories = categoryService.findAll(pageable);
        ModelAndView modelAndView = new ModelAndView("/category/list");
        modelAndView.addObject("categories", categories);
        return modelAndView;
    }

    @GetMapping("/edit-category/{id}")
    public ModelAndView showEditForm(@PathVariable long id){
        Category category = categoryService.findById(id);
        if(category != null) {
            ModelAndView modelAndView = new ModelAndView("/category/edit");
            modelAndView.addObject("category", category);
            return modelAndView;

        }else {
            ModelAndView modelAndView = new ModelAndView("/error.404");
            return modelAndView;
        }
    }

    @PostMapping("/edit-category")
    public ModelAndView updateCategory(@ModelAttribute("category") Category category){
        categoryService.save(category);
        ModelAndView modelAndView = new ModelAndView("/category/edit");
        modelAndView.addObject("category", category);
        modelAndView.addObject("message", "Category updated successfully");
        return modelAndView;
    }

    @GetMapping("/delete-category/{id}")
    public ModelAndView showDeleteForm(@PathVariable long id){
        Category category = categoryService.findById(id);
        if(category != null) {
            ModelAndView modelAndView = new ModelAndView("/category/delete");
            modelAndView.addObject("category", category);
            return modelAndView;

        }else {
            ModelAndView modelAndView = new ModelAndView("/error.404");
            return modelAndView;
        }
    }

    @PostMapping("/delete-category")
    public String deleteCategory(@ModelAttribute("category") Category category){
        categoryService.delete(category.getId());
        return "redirect:categories";
    }

}
