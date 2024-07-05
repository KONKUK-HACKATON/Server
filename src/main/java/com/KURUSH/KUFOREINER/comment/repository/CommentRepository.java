package com.KURUSH.KUFOREINER.comment.repository;

import com.KURUSH.KUFOREINER.comment.Comment;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByPostPostId(Long postId);
}
