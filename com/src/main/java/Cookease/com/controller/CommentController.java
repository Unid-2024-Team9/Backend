package Cookease.com.controller;

import Cookease.com.dto.request.CommentRequest;
import Cookease.com.dto.response.PostResponse;
import Cookease.com.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/comment")
public class CommentController {
    private final CommentService commentService;

    @PostMapping("") //댓글 게시
    public ResponseEntity<PostResponse> register(@RequestBody CommentRequest commentRequest) {
        PostResponse postResponse = commentService.register(commentRequest);
        return ResponseEntity.ok(postResponse);
    }
}
