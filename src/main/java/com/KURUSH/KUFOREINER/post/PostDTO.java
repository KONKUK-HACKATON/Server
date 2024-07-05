package com.KURUSH.KUFOREINER.post;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostDTO {
    private Long postId;
    private String nickname;
    private String title;
    private String content;
    private Boolean isOpen;
    private Boolean isInfo;
    private String category;
//    private MemberDTO member;
}
