package Cookease.com.service;

import Cookease.com.domain.*;
import Cookease.com.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ItemService {

    @Autowired
    private final MemberIngredientRepository memberIngredientRepository;

    @Autowired
    private final MemberJpaRepository memberJpaRepository;

    @Autowired
    private final IngredientRepository ingredientRepository;

    @Transactional
    public MemberIngredient addIngredientToRefrigerator(Long memberId, Long ingredientId, KeepCategory keepCategory, LocalDateTime expirationDate) {
        Member member = memberJpaRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("Member not found with ID: " + memberId));
        Ingredient ingredient = ingredientRepository.findById(ingredientId)
                .orElseThrow(() -> new IllegalArgumentException("Ingredient not found with ID: " + ingredientId));

        MemberIngredient memberIngredient = MemberIngredient.builder()
                .member(member)
                .ingredient(ingredient)
                .keepCategory(keepCategory)
                .expirationDate(expirationDate)
                .build();

        return memberIngredientRepository.save(memberIngredient);
    }

    public List<MemberIngredient> getIngredientsByMemberAndCategory(Long memberId, KeepCategory keepCategory) {
        return memberIngredientRepository.findByMemberIdAndKeepCategory(memberId, keepCategory);
    }

    public Optional<MemberIngredient> getIngredientDetails(Long memberId, Long ingredientId) {
        return memberIngredientRepository.findByMemberIdAndIngredientId(memberId, ingredientId);
    }

    @Transactional
    public Ingredient addNewIngredient(String enName, String name, IngredientCategory category) {
        Ingredient ingredient = Ingredient.builder()
                .en_name(enName)
                .name(name)
                .category(category)
                .build();

        return ingredientRepository.save(ingredient);
    }
}