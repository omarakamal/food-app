package com.food.demo.controller;

import com.food.demo.model.Recipe;
import com.food.demo.repository.CategoryRepository;
import com.food.demo.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RecipeController {
    private RecipeService recipeService;
    private final CategoryRepository categoryRepository;

    public RecipeController(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Autowired
    public void setRecipeService(RecipeService recipeService){
        this.recipeService = recipeService;
    }

    @PostMapping("/categories/{categoryId}/recipes")
    public Recipe createRecipe(
            @PathVariable(value = "categoryId") Long categoryId,
            @RequestBody Recipe recipeObject){
        return recipeService.createRecipe(categoryId, recipeObject);
    }

    @GetMapping("/categories/{categoryId}/recipes")
    public List<Recipe> getRecipes(@PathVariable(value = "categoryId") Long categoryId){
        return recipeService.getRecipes(categoryId);
    }

    @GetMapping("/categories/{categoryId}/recipes/{recipeId}")
    public Recipe getRecipe(@PathVariable(value="categoryId") Long categoryId,
                            @PathVariable(value="recipeId") Long recipeId){
        return recipeService.getRecipe(categoryId, recipeId);
    }
}
