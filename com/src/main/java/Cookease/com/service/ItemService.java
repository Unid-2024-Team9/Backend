package Cookease.com.service;

import Cookease.com.domain.*;
import Cookease.com.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
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

    @Autowired
    private final FcmService fcmService;

    @Transactional
    public MemberIngredient addIngredientToRefrigerator(Long memberId, Long ingredientId, KeepCategory keepCategory, LocalDateTime expirationDate) {
        Member member = memberJpaRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("Member not found with ID: " + memberId));
        Ingredient ingredient = ingredientRepository.findById(ingredientId)
                .orElseThrow(() -> new IllegalArgumentException("Ingredient not found with ID: " + ingredientId));

        // 디폴트 expirationDate 설정
        if (expirationDate == null) {
            switch (keepCategory) {
                case ICE:
                    expirationDate = LocalDateTime.now().plusYears(1);
                    break;
                case COLD:
                    expirationDate = LocalDateTime.now().plusDays(5);
                    break;
                case NORMAL:
                    expirationDate = LocalDateTime.now().plusWeeks(2);
                    break;
            }
        }

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

    // 유통기한 임박한 재료를 확인하고 알림을 발송하는 메서드
    @Scheduled(cron = "0 0 8 * * ?") // 매일 오전 8시에 실행
    public void notifyExpiringIngredients() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime oneDayLater = now.plusDays(1);

        List<MemberIngredient> expiringIngredients = memberIngredientRepository.findAllByExpirationDateBetween(now, oneDayLater);

        for (MemberIngredient ingredient : expiringIngredients) {
            String token = ingredient.getMember().getProfile(); // 프로필 필드가 FCM 토큰이라고 가정
            String ingredientName = ingredient.getIngredient().getName();
            String message = "유통기한이 임박한 재료: " + ingredientName + " (유통기한: " + ingredient.getExpirationDate().toLocalDate() + ")";

            fcmService.sendNotification(token, "유통기한 알림", message);
        }
    }
}