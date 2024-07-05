package com.KURUSH.KUFOREINER.comment.service;

import com.KURUSH.KUFOREINER.comment.Comment;
import com.KURUSH.KUFOREINER.comment.dto.CommentDTO;
import com.KURUSH.KUFOREINER.comment.dto.CommentResponseDTO;
import com.KURUSH.KUFOREINER.comment.repository.CommentRepository;
import com.KURUSH.KUFOREINER.member.MemberRepository;
import com.KURUSH.KUFOREINER.member.domain.Member;
import com.KURUSH.KUFOREINER.member.exception.MemberNotExistException;
import com.KURUSH.KUFOREINER.post.PostRepository;
import com.KURUSH.KUFOREINER.post.domain.Post;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private PostRepository postRepository;


    public CommentResponseDTO createComment(CommentDTO commentDTO) {

        String userId = getUsernameBySecurityContext();
        Member member = memberRepository.findByUserId(userId)
                .orElseThrow(() -> new MemberNotExistException());


        Post post = postRepository.findById(commentDTO.getPostId())
                .orElseThrow(() -> new IllegalArgumentException("Post not found"));

        Comment comment = Comment.builder()
                .nickname(commentDTO.getNickname())
                .content(commentDTO.getContent())
                .member(member)
                .post(post)
                .build();

        Comment savedComment = commentRepository.save(comment);
        return CommentResponseDTO.fromEntity(savedComment);
    }
    public String getUsernameBySecurityContext() {
        return SecurityContextHolder.getContext().getAuthentication()
                .getName();
    }

}
