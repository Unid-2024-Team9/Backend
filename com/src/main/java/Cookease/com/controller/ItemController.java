package Cookease.com.controller;

import Cookease.com.domain.Ingredient;
import Cookease.com.domain.IngredientCategory;
import Cookease.com.domain.MemberIngredient;
import Cookease.com.domain.KeepCategory;
import Cookease.com.service.ItemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/items")
@Tag(name = "Item Controller", description = "APIs for managing refrigerator items")
public class ItemController {

    @Autowired
    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @Operation(summary = "Add an ingredient to the refrigerator", description = "Add an ingredient to a member's refrigerator with specified category and expiration date")
    @PostMapping("/add")
    public ResponseEntity<MemberIngredient> addIngredientToRefrigerator(@RequestParam Long memberId,
                                                                        @RequestParam Long ingredientId,
                                                                        @RequestParam KeepCategory keepCategory,
                                                                        @RequestParam LocalDateTime expirationDate) {
        MemberIngredient memberIngredient = itemService.addIngredientToRefrigerator(memberId, ingredientId, keepCategory, expirationDate);
        return ResponseEntity.ok(memberIngredient);
    }

    @Operation(summary = "Get ingredients by category", description = "Retrieve all ingredients in the refrigerator for a specific member and category")
    @GetMapping("/by-category")
    public ResponseEntity<List<MemberIngredient>> getIngredientsByMemberAndCategory(@RequestParam Long memberId,
                                                                                    @RequestParam KeepCategory keepCategory) {
        List<MemberIngredient> ingredients = itemService.getIngredientsByMemberAndCategory(memberId, keepCategory);
        return ResponseEntity.ok(ingredients);
    }

    @Operation(summary = "Get ingredient details", description = "Get details of a specific ingredient for a member")
    @GetMapping("/details")
    public ResponseEntity<MemberIngredient> getIngredientDetails(@RequestParam Long memberId,
                                                                 @RequestParam Long ingredientId) {
        Optional<MemberIngredient> ingredientDetails = itemService.getIngredientDetails(memberId, ingredientId);
        return ingredientDetails.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Add a new ingredient", description = "Add a new ingredient to the system with its details and category")
    @PostMapping("/add-new-ingredient")
    public ResponseEntity<Ingredient> addNewIngredient(@RequestParam String enName,
                                                       @RequestParam String name,
                                                       @RequestParam IngredientCategory category) {
        Ingredient ingredient = itemService.addNewIngredient(enName, name, category);
        return ResponseEntity.ok(ingredient);
    }
}
