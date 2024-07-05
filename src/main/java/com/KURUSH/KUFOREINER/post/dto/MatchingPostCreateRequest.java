package com.KURUSH.KUFOREINER.post.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MatchingPostCreateRequest {
    private String nickname;
    private String title;
    private String content;
    private Boolean isOpen;
    private Boolean isInfo;
    private String category;
}
