package Cookease.com.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CommentDTO {
    private Long id;
    private String content;
    private String authorName; // Member의 이름 등 필요한 데이터
    private Long postId;
}
