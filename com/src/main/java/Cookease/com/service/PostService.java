package Cookease.com.service;

import Cookease.com.domain.*;
import Cookease.com.dto.request.PostRequest;
import Cookease.com.dto.response.PostResponse;
import Cookease.com.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final MemberRepository memberRepository;
    private final CommentRepository commentRepository;

    public Long registerPost(PostRequest postRequest) { //게시글 등록 메서드
        Member member = memberRepository.findById(postRequest.getMemberid()).orElse(null); //멤버 id로 멤버 객체 찾기

        Post post = Post.builder() //postRequest DTO를 Entity로 변환 후 저장
                .member(member)
                .title(postRequest.getTitle())
                .content(postRequest.getContent())
                .build();

        Post savedPost = postRepository.save(post); //Repository에 Entity 저장

        return savedPost.getId(); //게시글 id 반환
    }

    public PostResponse getPost(Long postid) { //게시글 조회 + 댓글
        Post post = postRepository.findById(postid).orElse(null); //postid로 post 객체 찾기

        PostResponse postResponse = new PostResponse();
        postResponse.setPostid(postid);
        postResponse.setTitle(post.getTitle());
        postResponse.setContent(post.getContent());
        postResponse.setTitle(post.getTitle());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.d");
        String createdAtString = post.getCreatedAt().format(formatter);
        postResponse.setCreadtat(createdAtString);
        postResponse.setContent(post.getContent());

        //postid로 모든 댓글 찾아서 postResponse.comments에 저장
        postResponse.setComments(commentRepository.findByPost(post));
        return postResponse;
    }
}
