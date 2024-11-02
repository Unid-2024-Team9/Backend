package Cookease.com.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class CommentRequest {

    private Long memberid; //댓글 작성자 id
    private Long postid; //댓글을 달 게시글 id
    private String content; //게시글 내용

}
