package Cookease.com.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class Post extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id; //게시글 id

    @OnDelete(action = OnDeleteAction.CASCADE)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")  // `Member` 엔티티와의 연관 관계 설정
    private Member member; //작성자

    @Column
    private String title; //게시글 제목

    @Column(name = "content", nullable = false)
    private String content; //게시글 내용

    @Builder
    public Post(Member member, String content, String title) {
        this.member = member;
        this.content = content;
        this.title = title;
    }
}
