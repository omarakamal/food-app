package com.food.demo.controller;

import com.food.demo.exception.InformationExistsException;
import com.food.demo.model.Category;
import com.food.demo.repository.CategoryRepository;
import com.food.demo.service.CategoryService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api")
public class CategoryController {

    private CategoryService categoryService;

    @Autowired
    public void setCategoryService(CategoryService categoryService){
        this.categoryService = categoryService;
    }

    // CRUD - C - HTTP POST - To create a record (Category)
    @PostMapping("/categories")
    public Category createCategory(@RequestBody Category categoryObject){
        System.out.println("Calling createCategory ==>");
        return categoryService.createCategory(categoryObject);
    }

    // CRUD - R - HTTP GET - To read all record (Categories)
    @GetMapping("/categories")
    public List<Category> getCategories(){
        System.out.println("Calling getcategories ==>");
        return categoryService.getCategories();
    }

    // CRUD - R - HTTP GET - To read a record (Category)
    @GetMapping("/categories/{categoryId}")
    public Optional<Category> getCategory(@PathVariable Long categoryId){
        System.out.println("Calling getCategory");
        return categoryService.getCategory(categoryId);
    }

    // CRUD - U - HTTP PUT/POST - To update a record (Category)
    @PutMapping("/categories/{categoryId}")
    public Category updateCategory(@PathVariable(value = "categoryId") Long categoryId, @RequestBody Category categoryObject) {
        System.out.println("calling updateCategory ==>");
        return categoryService.updateCategory(categoryId, categoryObject);
    }

    // CRUD - D - HTTP DELETE - To delete a record (Category)
    @DeleteMapping("/categories/{categoryId}")
    public Optional<Category> deleteCategory(@PathVariable(value = "categoryId") Long categoryId) {
        System.out.println("calling deleteCategory ==>");
        return categoryService.deleteCategory(categoryId);
    }
}
