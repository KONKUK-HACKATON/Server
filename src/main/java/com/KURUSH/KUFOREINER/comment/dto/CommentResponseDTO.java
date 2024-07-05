package com.KURUSH.KUFOREINER.comment.dto;

import com.KURUSH.KUFOREINER.comment.Comment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentResponseDTO {

    private Long commentId;
    private String nickname;
    private String content;
    private Long memberId;
    private Long postId;

    public static CommentResponseDTO fromEntity(Comment comment) {
        return CommentResponseDTO.builder()
                .commentId(comment.getCommentId())
                .nickname(comment.getNickname())
                .content(comment.getContent())
                .memberId(comment.getMember().getMemberId())
                .postId(comment.getPost().getPostId())
                .build();
    }
}