package Cookease.com.repository;

import Cookease.com.domain.Comment;
import Cookease.com.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
}
