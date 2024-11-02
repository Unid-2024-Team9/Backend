package Cookease.com.domain;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class Refrigerator {
    @Id
    @GeneratedValue
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="member_id")
    private Member member;

}
