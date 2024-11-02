package Cookease.com.controller;

import Cookease.com.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/recipes")
public class RecipeController {

    @Autowired
    private RecipeService recipeService;

    @GetMapping("/{recipeCode}")
    public ResponseEntity<Map<String, Object>> getRecipe(@PathVariable("recipeCode") String recipeCode) {
        Map<String, Object> recipeDetails = recipeService.getRecipeDetails(recipeCode);
        return ResponseEntity.ok(recipeDetails);
    }
}
