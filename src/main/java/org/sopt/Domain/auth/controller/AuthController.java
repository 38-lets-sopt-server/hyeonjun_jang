package org.sopt.Domain.auth.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.sopt.Domain.auth.dto.request.ReissueRequest;
import org.sopt.Domain.auth.exception.code.AuthSuccessCode;
import org.sopt.Domain.user.exception.code.UserSuccessCode;
import org.sopt.Domain.auth.dto.response.TokenResponse;
import org.sopt.Domain.user.dto.response.UserResponse;
import org.sopt.global.response.BaseResponse;
import org.sopt.Domain.auth.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;


@Tag(name = "Auth", description = "인증 관련 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;

    @Operation(summary = "로그인 (Access Token + Refresh Token 발급)")
    @PostMapping("/login")
    public ResponseEntity<BaseResponse<TokenResponse>> login(
            @RequestParam("email") String email,
            @RequestParam("password") String password
    ) {
        TokenResponse tokens = authService.login(email, password);

        return ResponseEntity.ok(BaseResponse.success(UserSuccessCode.USER_LOGIN_SUCCESS, tokens));
    }


    @Operation(
            summary = "토큰 재발급",
            description = "만료된 Access Token을 Refresh Token으로 재발급"
    )
    @PostMapping("/reissue")
    public ResponseEntity<BaseResponse<TokenResponse>> reissue(
            @Valid @RequestBody ReissueRequest request
    ) {
        TokenResponse tokens = authService.reissue(request.refreshToken());
        return ResponseEntity.ok(BaseResponse.success(AuthSuccessCode.AUTH_REISSUE_SUCCESS, tokens));
    }



    @Operation(summary = "내 정보 조회 (Access Token 검증)")
    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/me")
    public ResponseEntity<BaseResponse<UserResponse>> me(Authentication authentication) {

        if (authentication == null || authentication.getPrincipal() == null) {
            throw new IllegalArgumentException("인증되지 않았습니다.");
        }

        Long userId = Long.parseLong(authentication.getName());
        UserResponse user = authService.getMemberById(userId);

        return ResponseEntity.ok(BaseResponse.success(AuthSuccessCode.AUTH_GET_USER, user));
    }
}
