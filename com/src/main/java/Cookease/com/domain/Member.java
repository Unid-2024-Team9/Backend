package Cookease.com.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseTimeEntity{
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column
    private String profile;

    @Column
    private String sido;

    @Column
    private String sigungu;

    @Column
    private String bname;

    @Builder
    public Member(String username, String profile, String sido, String sigungu, String bname) {
        this.username = username;
        this.profile = profile;
        this.sido = sido;
        this.sigungu = sigungu;
        this.bname = bname;
    }
}
