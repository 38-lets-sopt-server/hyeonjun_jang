package org.sopt.Domain.user.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.sopt.Domain.user.dto.request.CreateUserRequest;
import org.sopt.Domain.user.dto.response.CreateUserResponse;
import org.sopt.Domain.user.dto.response.UserResponse;
import org.sopt.global.response.BaseResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

public interface UserApi {


    @Operation(summary = "내 정보 조회", description = "Access Token으로 인증된 유저의 정보를 반환합니다.")
    @SecurityRequirement(name = "bearerAuth")
        // Swagger에서 자물쇠 아이콘 활성화
    ResponseEntity<BaseResponse<UserResponse>> getMe(Authentication authentication);


    @Operation(summary = "회원가입", description = "회원가입 합니다.")
    ResponseEntity<BaseResponse<CreateUserResponse>> join(
            @Valid @RequestBody CreateUserRequest request
    );
}
