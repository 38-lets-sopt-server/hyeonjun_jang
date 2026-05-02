package org.sopt.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;

public record CreateUserRequest(
        @Schema(description = "사용자 이름", example = "장현준")
        String nickname,

        @Schema(description = "이메일", example = "guswnsj0112@naver.com")
        String email
) {
}