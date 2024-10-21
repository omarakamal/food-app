package com.food.demo.service;

import com.food.demo.exception.InformationExistsException;
import com.food.demo.exception.InformationNotFoundException;
import com.food.demo.model.Category;
import com.food.demo.model.Recipe;
import com.food.demo.repository.CategoryRepository;
import com.food.demo.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class RecipeService {
    private CategoryRepository categoryRepository;
    private RecipeRepository recipeRepository;

    @Autowired
    public void setCategoryRepository(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }

    @Autowired
    public void setRecipeRepository(RecipeRepository recipeRepository){
        this.recipeRepository = recipeRepository;
    }

    public Recipe createRecipe(Long categoryId, Recipe recipeObject){
        try{
            Optional<Category> category = categoryRepository.findById(categoryId);
            recipeObject.setCategory(category.get());
            return recipeRepository.save(recipeObject);
        } catch (NoSuchElementException e){
            throw new InformationNotFoundException("Category with id " + categoryId + " not found.");
        }
    }

    public List<Recipe> getRecipes(Long categoryId){
        Optional<Category> category = categoryRepository.findById(categoryId);
        if(category.isPresent()){
            return category.get().getRecipeList();
        }
        else {
            throw new InformationNotFoundException("Category with id " + categoryId + " not found");
        }
    }

    public Recipe getRecipe(Long categoryId, Long recipeId){
        Optional<Category> category = categoryRepository.findById(categoryId);
        if(category.isPresent()){
            Optional<Recipe> recipe = recipeRepository.findByCategoryId(categoryId).stream().filter(
                    p -> p.getId().equals(recipeId)).findFirst();
            if(recipe.isEmpty()){
                throw new InformationNotFoundException("Recipe with id " + recipeId + " not found");
            } else {
                return recipe.get();
            }
        } else {
            throw new InformationNotFoundException("Cat gory with id " + categoryId + " not found");
        }
    }
}
