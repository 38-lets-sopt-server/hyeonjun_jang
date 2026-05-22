package org.sopt.Domain.auth.exception.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.sopt.global.code.SuccessCode;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum AuthSuccessCode implements SuccessCode {
    AUTH_GET_USER(HttpStatus.OK, "AUTH_SUCCESS_001", "유저 인증 성공"),
    AUTH_REISSUE_SUCCESS(HttpStatus.OK, "AUTH_SUCCESS_002", "토큰 재발급 성공"),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;

}
