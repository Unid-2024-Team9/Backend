package Cookease.com.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ingredient_id")
    private Long id;

    @Column(name = "ingredient_name", nullable = false)
    private String name;
    @Column(name = "ingredient_en_name", nullable = false)
    private String en_name;

    @Enumerated(EnumType.STRING)
    private IngredientCategory category;

    @Builder
    public Ingredient(String name, String en_name, IngredientCategory category) {
        this.name = name;
        this.en_name = en_name;
        this.category = category;
    }
}
