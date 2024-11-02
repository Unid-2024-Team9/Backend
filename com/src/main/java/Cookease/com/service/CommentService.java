package Cookease.com.service;

import Cookease.com.domain.Comment;
import Cookease.com.domain.Member;
import Cookease.com.domain.Post;
import Cookease.com.dto.request.CommentRequest;
import Cookease.com.dto.response.PostResponse;
import Cookease.com.repository.CommentRepository;
import Cookease.com.repository.MemberRepository;
import Cookease.com.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final PostRepository postRepository;
    private final MemberRepository memberRepository;
    private final PostService postService;
    private final CommentRepository commentRepository;

    public PostResponse register(CommentRequest commentRequest) { //댓글 등록
        Member member = memberRepository.findById(commentRequest.getMemberid()).orElse(null); //멤버 id로 멤버 객체 찾기
        Post post = postRepository.findById(commentRequest.getPostid()).orElse(null); //포스트 id로 post 객체 찾기

        Comment comment = Comment.builder() //CommentRequest DTO를 Entity로 반환 후 저장
                .member(member)
                .post(post)
                .content(commentRequest.getContent())
                .build();

        commentRepository.save(comment); //repository에 entity 저장

        return postService.getPost(commentRequest.getPostid()); //게시글 다시 조회하여 반환
    }
}
