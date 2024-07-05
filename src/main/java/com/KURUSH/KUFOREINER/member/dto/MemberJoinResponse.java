package com.KURUSH.KUFOREINER.member.dto;

import com.KURUSH.KUFOREINER.member.domain.Member;
import lombok.Builder;

@Builder
public record MemberJoinResponse(
        String surveyUrl,String message


) {

    public static MemberJoinResponse of(Member member) {
        return MemberJoinResponse.builder()
                .message(member.getNickname()+"님 회원가입을 축하합니다 !")
                .build();

    }
}
