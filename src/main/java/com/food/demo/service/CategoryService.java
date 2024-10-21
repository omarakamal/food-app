package com.food.demo.service;

import com.food.demo.exception.InformationExistsException;
import com.food.demo.exception.InformationNotFoundException;
import com.food.demo.model.Category;
import com.food.demo.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    private CategoryRepository categoryRepository;

    @Autowired
    public void setCategoryRepository(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }

    public Category createCategory(Category categoryObject){
        System.out.println("Service Calling createCategory ==>");

        Category category = categoryRepository.findByName(categoryObject.getName());
        if(category != null){
            throw new InformationExistsException("Category with name" + category.getName() + " already exists");
        }
        else
        {
            return categoryRepository.save(categoryObject);
        }
//        return "Creating a category " + body;
    }

    public List<Category> getCategories() {
        System.out.println("Service Calling getCategories ==>");
        return categoryRepository.findAll();
    }

    public Optional<Category> getCategory(Long categoryId){
        System.out.println("Service calling getcategory ==> ");
        Optional<Category> category = categoryRepository.findById(categoryId);
        if(category.isPresent()){
            return category;
        }
        else {
            throw new InformationNotFoundException("Category with id " + categoryId + " not found");
        }
    }

    public Category updateCategory(Long categoryId, Category categoryObject) {
        System.out.println("service calling updateCategory ==>");
        Optional<Category> category = categoryRepository.findById(categoryId);
        if (category.isPresent()) {
            if (categoryObject.getName().equals(category.get().getName())) {
                System.out.println("Same");
                throw new InformationExistsException("category " + category.get().getName() + " is already exists");
            } else {
                Category updateCategory = categoryRepository.findById(categoryId).get();
                updateCategory.setName(categoryObject.getName());
                updateCategory.setDescription(categoryObject.getDescription());
                return categoryRepository.save(updateCategory);
            }
        } else {
            throw new InformationNotFoundException("category with id " + categoryId + " not found");
        }
    }

    public Optional<Category> deleteCategory(Long categoryId) {
        System.out.println("service calling deleteCategory ==>");
        Optional<Category> category = categoryRepository.findById(categoryId);

        if (category.isPresent()) {
            categoryRepository.deleteById(categoryId);
            return category;
        } else {
            throw new InformationNotFoundException("category with id " + categoryId + " not found");
        }
    }
}
