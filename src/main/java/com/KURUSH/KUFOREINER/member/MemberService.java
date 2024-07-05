package com.KURUSH.KUFOREINER.member;

import com.KURUSH.KUFOREINER.global.exception.HttpExceptionCode;
import com.KURUSH.KUFOREINER.global.security.JWTUtil;
import com.KURUSH.KUFOREINER.member.domain.Member;
import com.KURUSH.KUFOREINER.member.dto.MemberJoinRequest;
import com.KURUSH.KUFOREINER.member.exception.MemberExistException;
import com.KURUSH.KUFOREINER.member.exception.NickNameAlreadyExistException;
import com.KURUSH.KUFOREINER.member.exception.UserIdAlreadyExistException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private static final String AUTH_CODE_PREFIX = "AuthCode ";


    private final JWTUtil jwtUtil;


    public Member save(MemberJoinRequest memberJoinRequest) {
        Member member = memberJoinRequest.toEntity();

        memberRepository.findByUserId(member.getUserId())
                .ifPresent(m -> {
                    throw new UserIdAlreadyExistException();
                });

        memberRepository.findByNickname(member.getNickname())
                .ifPresent(m -> {
                    throw new NickNameAlreadyExistException();
                });


        Member savedmember = memberRepository.save(member);

        return savedmember;
    }

}