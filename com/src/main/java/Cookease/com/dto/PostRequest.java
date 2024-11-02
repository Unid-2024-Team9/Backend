package Cookease.com.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class PostRequest {

    private Long memberid; //작성자
    private String title; //게시글 제목
    private String content; //게시글 내용

}
