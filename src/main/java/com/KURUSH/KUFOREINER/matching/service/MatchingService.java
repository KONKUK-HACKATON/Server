package com.KURUSH.KUFOREINER.matching.service;

import com.KURUSH.KUFOREINER.matching.MatchingRepository;
import com.KURUSH.KUFOREINER.matching.domain.Matching;
import com.KURUSH.KUFOREINER.matching.dto.MatchingAddRequest;
import com.KURUSH.KUFOREINER.matching.dto.MatchingReadResponse;
import com.KURUSH.KUFOREINER.member.MemberRepository;
import com.KURUSH.KUFOREINER.member.domain.Member;
import com.KURUSH.KUFOREINER.member.exception.MemberNotExistException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MatchingService {
    private final MatchingRepository matchingRepository;
    private final MemberRepository memberRepository;

    public List<MatchingReadResponse> getMyMatchingList() {
        String userId = getUsernameBySecurityContext();

        // Member를 조회
        Member member = memberRepository.findByUserId(userId)
                .orElseThrow(() -> new MemberNotExistException());

        List<Matching> matchings = matchingRepository.findByMember(member);
        return matchings.stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    public void addMatching(MatchingAddRequest request) {
        String userId = getUsernameBySecurityContext();

        // Member를 조회
        Member member = memberRepository.findByUserId(userId)
                .orElseThrow(() -> new MemberNotExistException());

        Matching matching = Matching.builder()
                .nickname(request.getNickname())
                .nation(request.getNation())
                .gender(request.getGender())
                .college(request.getCollege())
                .major(request.getMajor())
                .member(member)
                .build();
        matchingRepository.save(matching);
    }

    private MatchingReadResponse convertToResponse(Matching matching) {
        return MatchingReadResponse.builder()
                .matchingId(matching.getMatchingId())
                .nickname(matching.getNickname())
                .nation(matching.getNation())
                .gender(matching.getGender())
                .college(matching.getCollege())
                .major(matching.getMajor())
                .memberId(matching.getMember().getMemberId())
                .build();
    }



    public String getUsernameBySecurityContext() {
        return SecurityContextHolder.getContext().getAuthentication()
                .getName();
    }
}
