package Cookease.com.controller;

import Cookease.com.domain.Comment;
import Cookease.com.domain.Post;
import Cookease.com.dto.CommentDTO;
import Cookease.com.dto.PostDTO;
import Cookease.com.service.CommunityService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/community")
@Tag(name = "Community Controller", description = "APIs for managing posts and comments")
public class CommunityController {

    @Autowired
    private final CommunityService communityService;

    public CommunityController(CommunityService communityService) {
        this.communityService = communityService;
    }

    @Operation(summary = "Create a post", description = "Create a new post for a member with the provided content")
    @PostMapping("/post")
    public ResponseEntity<Post> createPost(@RequestParam Long memberId, @RequestParam String content, @RequestParam String title) {
        Post post = communityService.createPost(memberId, content, title);
        return ResponseEntity.ok(post);
    }

    @Operation(summary = "Create a comment", description = "Create a new comment on a post by a member, and notify the post author if applicable")
    @PostMapping("/comment")
    public ResponseEntity<Comment> createComment(@RequestParam Long memberId, @RequestParam Long postId, @RequestParam String content) {
        Comment comment = communityService.createComment(memberId, postId, content);
        return ResponseEntity.ok(comment);
    }

    @Operation(summary = "View all posts", description = "Retrieve all community posts")
    @GetMapping("/posts")
    public ResponseEntity<List<PostDTO>> getAllPosts() {
        List<PostDTO> posts = communityService.getAllPosts();
        return ResponseEntity.ok(posts);
    }

    @Operation(summary = "View comments for a post", description = "Retrieve all comments for a specific post")
    @GetMapping("/comments")
    public ResponseEntity<List<CommentDTO>> getCommentsByPostId(@RequestParam Long postId) {
        List<CommentDTO> comments = communityService.getCommentsByPostId(postId);
        return ResponseEntity.ok(comments);
    }
}
