package com.KURUSH.KUFOREINER.comment.repository;

import com.KURUSH.KUFOREINER.comment.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
