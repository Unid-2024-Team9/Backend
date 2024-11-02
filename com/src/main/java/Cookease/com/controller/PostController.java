package Cookease.com.controller;

import Cookease.com.dto.request.PostRequest;
import Cookease.com.dto.response.PostResponse;
import Cookease.com.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/post")
public class PostController {
    private final PostService postService;

    @PostMapping("") //게시
    public ResponseEntity<Long> register(@RequestBody PostRequest postRequest) {
        Long postid = postService.registerPost(postRequest);
        return ResponseEntity.ok(postid);
    }

    @GetMapping("") //postid로 게시글 조회
    public ResponseEntity<PostResponse> get(@RequestParam Long postid) {
        PostResponse postResponse = postService.getPost(postid);

        return ResponseEntity.ok(postResponse);
    }
}
