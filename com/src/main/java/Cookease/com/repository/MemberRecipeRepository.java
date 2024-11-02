package Cookease.com.repository;

import Cookease.com.domain.MemberRecipe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRecipeRepository extends JpaRepository<MemberRecipe, Long> {
    boolean existsByMemberIdAndRecipeId(Long memberId, Long recipeId);
}