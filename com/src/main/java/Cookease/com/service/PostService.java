package Cookease.com.service;

import Cookease.com.domain.Member;
import Cookease.com.domain.Post;
import Cookease.com.dto.PostRequest;
import Cookease.com.repository.MemberRepository;
import Cookease.com.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final MemberRepository memberRepository;

    public long registerPost(PostRequest postRequest) { //게시글 등록 메서드
        Member member = memberRepository.findById(postRequest.getMemberid()).orElse(null); //멤버 id로 멤버 객체 찾기

        Post post = Post.builder() //postRequest DTO를 Entity로 반환 후 저장
                .member(member)
                .title(postRequest.getTitle())
                .content(postRequest.getContent())
                .build();

        return postRepository.save(post).getId(); //일단 게시글 id 반환하는 걸로...
    }
}
