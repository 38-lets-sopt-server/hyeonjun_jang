package org.sopt.Domain.user.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record CreateUserRequest(
        @Schema(description = "사용자 이름", example = "장현준")
        String nickname,

        @NotBlank(message = "이메일은 필수 입력 값입니다.")
        @Email(message = "이메일 형식에 맞지 않습니다.")
        @Schema(description = "이메일", example = "guswnsj0112@naver.com")
        String email,

        @NotBlank(message = "비밀번호는 필수 입력 값입니다.")
        @Schema(description = "패스워드", example = "비밀")
        String password
) {
}