package org.sopt.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.sopt.dto.request.CreateUserRequest;
import org.sopt.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "User", description = " 사용자 관련 API")
@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Operation(summary = "유저 생성", description = "새로운 유저를 생성합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "유저 생성 성공"),
    })
    @PostMapping
    public void createUser(@Valid @RequestBody CreateUserRequest createUser) {
        userService.createUser(createUser);
    }
}