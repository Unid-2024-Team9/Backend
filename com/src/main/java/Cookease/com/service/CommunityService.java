package Cookease.com.service;

import Cookease.com.domain.Comment;
import Cookease.com.domain.Member;
import Cookease.com.domain.Post;
import Cookease.com.repository.CommentRepository;
import Cookease.com.repository.MemberJpaRepository;
import Cookease.com.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommunityService {

    @Autowired
    private final MemberJpaRepository memberJpaRepository;

    @Autowired
    private final PostRepository postRepository;

    @Autowired
    private final CommentRepository commentRepository;

    @Autowired
    private final FcmService fcmService;  // Use FcmService for FCM notifications

    @Transactional
    public Post createPost(Long memberId, String content) {
        Member member = memberJpaRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("Member not found with ID: " + memberId));

        Post post = Post.builder()
                .member(member)
                .content(content)
                .build();

        return postRepository.save(post);
    }

    @Transactional
    public Comment createComment(Long memberId, Long postId, String content) {
        Member member = memberJpaRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("Member not found with ID: " + memberId));
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("Post not found with ID: " + postId));

        Comment comment = Comment.builder()
                .member(member)
                .post(post)
                .content(content)
                .build();

        Comment savedComment = commentRepository.save(comment);

        // Send FCM notification to the post author
        if (!post.getMember().getId().equals(memberId)) {  // Ensure the comment is not from the author themselves
            String token = post.getMember().getProfile();  // Assuming 'profile' field holds FCM token
            fcmService.sendNotification(token, "New Comment on Your Post", "You have a new comment on your post: " + content);
        }

        return savedComment;
    }

    // 모든 글을 조회하는 메서드
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    // 특정 게시물의 모든 댓글을 조회하는 메서드
    public List<Comment> getCommentsByPostId(Long postId) {
        return commentRepository.findByPostId(postId);
    }
}
