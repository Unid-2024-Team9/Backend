package Cookease.com.controller;

import Cookease.com.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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

    @GetMapping("/parseDummyData")
    public ResponseEntity<Map<String, Object>> parseDummyData(@RequestParam String recipeCode) {
        Map<String, Object> parsedData = recipeService.parseDummyData(recipeCode);
        return ResponseEntity.ok(parsedData);
    }

    @GetMapping("/findByIngredients")
    public ResponseEntity<List<Map<String, Object>>> getRecipesByIngredients(@RequestParam List<String> ingredients,
                                                                             @RequestParam(defaultValue = "10") int number,
                                                                             @RequestParam(defaultValue = "1") int ranking,
                                                                             @RequestParam(defaultValue = "true") boolean ignorePantry) {
        List<Map<String, Object>> recipes = recipeService.getRecipesByIngredients(ingredients, number, ranking, ignorePantry);
        return ResponseEntity.ok(recipes);
    }
}