package Cookease.com.dto.response;

import Cookease.com.domain.Comment;
import lombok.*;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
public class PostResponse {
    private Long postid; //게시물 id
    private Long memberid; //작성자
    private String username; //작성자 username
    private String usersidosigungu; //작성자 지역
    private String title; //게시글 제목
    private String creadtat; //작성일시
    private String content; //게시글 내용
    private List<Comment> comments; //댓글 목록
}
