package Cookease.com.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

@Entity
@Getter
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ingredient_id")
    private Long id;

    @Column(name = "ingredient_name", nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    private IngredientCategory category;

    @Builder
    public Ingredient(String name, IngredientCategory category) {
        this.name = name;
        this.category = category;
    }
}
