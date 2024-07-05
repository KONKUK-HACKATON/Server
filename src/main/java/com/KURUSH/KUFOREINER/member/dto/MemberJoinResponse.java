package com.KURUSH.KUFOREINER.member.dto;

import com.KURUSH.KUFOREINER.member.domain.Member;
import lombok.Builder;

@Builder
public record MemberJoinResponse(
        Long memberId,String message


) {

    public static MemberJoinResponse of(Member member) {
        return MemberJoinResponse.builder()
                .memberId(member.getMemberId())
                .message(member.getNickname()+"님 회원가입을 축하합니다 !")
                .build();

    }
}
