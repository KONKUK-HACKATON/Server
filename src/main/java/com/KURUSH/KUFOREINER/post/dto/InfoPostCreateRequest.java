package com.KURUSH.KUFOREINER.post.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InfoPostCreateRequest {
    private String nickname;
    private String title;
    private String content;
}
