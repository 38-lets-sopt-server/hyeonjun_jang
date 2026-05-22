package org.sopt.Domain.auth.exception.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.sopt.global.code.ErrorCode;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum AuthErrorCode implements ErrorCode {
    AUTH_TOKEN_NOT_FOUND(HttpStatus.NOT_FOUND, "AUTH_ERROR_001", "토큰 인증 실패했습니다"),
    AUTH_INVALID_TOKEN(HttpStatus.NOT_FOUND, "AUTH_ERROR_002", "토큰이 유효하지 않습니다"),
    AUTH_USER_NOT_FOUND(HttpStatus.NOT_FOUND, "AUTH_ERROR_003", "유저 인증 실패했습니다"),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;

}