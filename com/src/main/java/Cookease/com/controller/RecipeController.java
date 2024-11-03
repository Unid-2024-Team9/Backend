package Cookease.com.controller;

import Cookease.com.service.RecipeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/recipes")
@Tag(name = "Recipe Controller", description = "APIs for managing recipes and related actions")
public class RecipeController {

    @Autowired
    private RecipeService recipeService;

    @Operation(summary = "Get recipe details", description = "Retrieve detailed information about a recipe by recipe code, including if it is scrapped by a member")
    @GetMapping("/{recipeCode}")
    public ResponseEntity<Map<String, Object>> getRecipe(@RequestParam Long memberId, @PathVariable("recipeCode") String recipeCode) {
        Map<String, Object> recipeDetails = recipeService.getRecipeDetails(memberId, recipeCode);
        return ResponseEntity.ok(recipeDetails);
    }

    @Operation(summary = "Parse dummy recipe data", description = "Parse dummy data for testing purposes by providing a recipe code")
    @GetMapping("/parseDummyData")
    public ResponseEntity<Map<String, Object>> parseDummyData(@RequestParam String recipeCode) {
        Map<String, Object> parsedData = recipeService.parseDummyData(recipeCode);
        return ResponseEntity.ok(parsedData);
    }

    @Operation(summary = "Find recipes by ingredients", description = "Find recipes based on a list of ingredients for a specific member, with options for result size, ranking, and pantry item exclusion")
    @GetMapping("/findByIngredients")
    public ResponseEntity<List<Map<String, Object>>> getRecipesByIngredients(@RequestParam Long memberId,
                                                                             @RequestParam List<String> ingredients,
                                                                             @RequestParam(defaultValue = "100") int number,
                                                                             @RequestParam(defaultValue = "1") int ranking,
                                                                             @RequestParam(defaultValue = "true") boolean ignorePantry) {
        List<Map<String, Object>> recipes = recipeService.getRecipesByIngredients(memberId, ingredients, number, ranking, ignorePantry);
        return ResponseEntity.ok(recipes);
    }

    @Operation(summary = "Scrap a recipe", description = "Mark a recipe as scrapped by a member")
    @PostMapping("/scrap")
    public ResponseEntity<String> scrapRecipe(@RequestParam Long memberId, @RequestParam Long recipeId) {
        recipeService.scrapRecipe(memberId, recipeId);
        return ResponseEntity.ok("Recipe has been scrapped successfully");
    }
}
