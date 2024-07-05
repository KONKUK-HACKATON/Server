package com.KURUSH.KUFOREINER.comment.controller;

import com.KURUSH.KUFOREINER.comment.dto.CommentDTO;
import com.KURUSH.KUFOREINER.comment.dto.CommentResponseDTO;
import com.KURUSH.KUFOREINER.comment.service.CommentService;
import com.KURUSH.KUFOREINER.global.response.HttpResponse;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping
    @Operation(summary = "댓글 등록", description = "댓글을 등록합니다.")
    public HttpResponse<CommentResponseDTO> createComment(@Valid @RequestBody CommentDTO commentDTO) {
        CommentResponseDTO savedComment = commentService.createComment(commentDTO);
        return HttpResponse.okBuild(
                savedComment

        );
    }

}
