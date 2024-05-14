package com.ll.topcastingbe.domain.member.controller;

import com.ll.topcastingbe.domain.member.dto.JoinRequestDto;
import com.ll.topcastingbe.domain.member.dto.MemberInfoResponseDto;
import com.ll.topcastingbe.domain.member.repository.RefreshTokenService;
import com.ll.topcastingbe.domain.member.service.MemberService;
import jakarta.validation.Valid;
import java.security.Principal;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class MemberController {

    private final MemberService memberService;
    private final RefreshTokenService refreshTokenService;

    @PostMapping("/join")
    public ResponseEntity<?> join(@RequestBody @Valid JoinRequestDto joinRequestDto) {
        return memberService.join(joinRequestDto);
    }


    @PreAuthorize("isAuthenticated()")
    @GetMapping("/info")
    public MemberInfoResponseDto info(Principal principal) {
        return memberService.findMemberInfo(principal.getName());
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/logout")
    public ResponseEntity<?> logout(@CookieValue(name = "RefreshToken") String refreshToken) {
        refreshTokenService.expireToken(refreshToken);
        return ResponseEntity.status(HttpStatus.OK).build();
    }


}
