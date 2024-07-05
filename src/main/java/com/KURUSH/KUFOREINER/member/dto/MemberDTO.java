package com.KURUSH.KUFOREINER.member.dto;

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
public class MemberDTO {
    private Long memberId;
    private String userId;
    private String nickname;
    private String colleage;
    private String major;
    private String studentnumber;
    private String nation;
    private String language;
    private String singularity;
}