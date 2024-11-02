package Cookease.com.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class MemberIngredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "refrigerator_ingredient_id")
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="member_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="ingredient_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Ingredient ingredient;

    @Enumerated(EnumType.STRING)
    @Column(name = "keep_category")
    private KeepCategory keepCategory;

    @Column(name = "expiration_date")
    private LocalDateTime expirationDate;

    @Builder
    public MemberIngredient(Member member, Ingredient ingredient, KeepCategory keepCategory, LocalDateTime expirationDate) {
        this.member = member;
        this.ingredient = ingredient;
        this.keepCategory = keepCategory;
        this.expirationDate = expirationDate;
    }
}
