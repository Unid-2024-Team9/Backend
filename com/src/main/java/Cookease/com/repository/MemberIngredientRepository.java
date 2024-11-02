package Cookease.com.repository;

import Cookease.com.domain.MemberIngredient;
import Cookease.com.domain.KeepCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberIngredientRepository extends JpaRepository<MemberIngredient, Integer> {
    List<MemberIngredient> findByMemberIdAndKeepCategory(Long memberId, KeepCategory keepCategory);
    Optional<MemberIngredient> findByMemberIdAndIngredientId(Long memberId, Long ingredientId);
}