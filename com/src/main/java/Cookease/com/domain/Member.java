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

    @Column(name = "kakao_id")
    private Long kakaoId;

    @Column
    private String username;
    private String profile;
    private String sido;
    private String sigungu;
    private String bname;

    @Builder
    public Member(Long kakaoId, String username, String profile, String sido, String sigungu, String bname) {
        this.kakaoId = kakaoId;
        this.username = username;
        this.profile = profile;
        this.sido = sido;
        this.sigungu = sigungu;
        this.bname = bname;
    }
}
