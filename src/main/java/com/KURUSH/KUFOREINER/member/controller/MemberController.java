package com.KURUSH.KUFOREINER.member.controller;

import com.KURUSH.KUFOREINER.global.response.HttpResponse;
import com.KURUSH.KUFOREINER.global.security.JWTUtil;
import com.KURUSH.KUFOREINER.member.MemberRepository;
import com.KURUSH.KUFOREINER.member.MemberService;
import com.KURUSH.KUFOREINER.member.domain.Member;
import com.KURUSH.KUFOREINER.member.dto.MemberInitialSettingsDTO;
import com.KURUSH.KUFOREINER.member.dto.MemberInitialSettingsResponse;
import com.KURUSH.KUFOREINER.member.dto.MemberJoinRequest;
import com.KURUSH.KUFOREINER.member.dto.MemberJoinResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/members")
@Tag(name = "MemberController", description = "멤버에 대한 레포지토리입니다.")

@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;
    private final MemberRepository memberRepository;
    private final JWTUtil jwtUtil;

    @PostMapping("/join")
    @Operation(summary = "회원가입", description = "회원가입을 실행합니다.")

    public HttpResponse<MemberJoinResponse> join(@Valid @RequestBody MemberJoinRequest memberJoinRequest) {
        Member savedmember = memberService.save(memberJoinRequest);
        return HttpResponse.okBuild(
                MemberJoinResponse.of(savedmember)
        );
    }

    @PostMapping("/initial-settings")
    @Operation(summary = "초기설정", description = "회원 초기설정을 실행합니다.")
    public HttpResponse<MemberInitialSettingsResponse> setInitialSettings(@Valid @RequestBody MemberInitialSettingsDTO initialSettingsDTO) {
        MemberInitialSettingsDTO savedSettings = memberService.updateInitialSettings(initialSettingsDTO);
        return HttpResponse.okBuild(
                MemberInitialSettingsResponse.of(savedSettings, "초기설정이 성공적으로 마무리되었습니다.")
        );
    }


}
