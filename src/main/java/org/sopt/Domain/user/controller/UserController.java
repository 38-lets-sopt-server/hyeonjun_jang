package org.sopt.Domain.user.controller;

import org.springframework.web.bind.annotation.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import org.sopt.Domain.user.dto.request.CreateUserRequest;
import org.sopt.Domain.user.dto.response.CreateUserResponse;
import org.sopt.Domain.user.dto.response.UserResponse;
import org.sopt.Domain.user.exception.code.UserErrorCode;
import org.sopt.Domain.user.exception.UserException;
import org.sopt.Domain.user.exception.code.UserSuccessCode;
import org.sopt.Domain.user.service.UserService;
import org.sopt.global.response.BaseResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "User", description = "유저 관련 API")
@RestController
@RequestMapping("/api/v1/users")
public class UserController implements UserApi {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping
    public ResponseEntity<BaseResponse<CreateUserResponse>> join(
            @Valid @RequestBody CreateUserRequest request
    ) {
        CreateUserResponse response = userService.createUser(request);
        return ResponseEntity
                .status(UserSuccessCode.USER_CREATE_SUCCESS.getStatus())
                .body(BaseResponse.success(UserSuccessCode.USER_CREATE_SUCCESS, response));
    }


    @GetMapping("/me")
    public ResponseEntity<BaseResponse<UserResponse>> getMe(Authentication authentication) {
        // SecurityConfig에서 anyRequest().authenticated()로 보호하지만
        // 혹시 필터가 누락될 경우를 대비한 방어 코드
        if (authentication == null || authentication.getPrincipal() == null) {
            throw new UserException(UserErrorCode.USER_NOT_FOUND);
        }

        // JwtAuthFilter에서 principal에 userId(String)를 담아뒀으므로 파싱해서 사용
        Long userId = Long.parseLong(authentication.getName());
        UserResponse response = userService.findUserById(userId);
        return ResponseEntity
                .status(UserSuccessCode.USER_GET_ME_SUCCESS.getStatus())
                .body(BaseResponse.success(UserSuccessCode.USER_GET_ME_SUCCESS, response));


    }
}