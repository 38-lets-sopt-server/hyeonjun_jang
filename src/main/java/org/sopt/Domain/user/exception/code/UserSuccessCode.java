package org.sopt.Domain.user.exception.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.sopt.global.code.SuccessCode;
import org.springframework.http.HttpStatus;


@Getter
@AllArgsConstructor
public enum UserSuccessCode implements SuccessCode {
    USER_CREATE_SUCCESS(HttpStatus.CREATED, "SUCCESS_USER_001", "회원가입 되었습니다."),
    USER_GET_ME_SUCCESS(HttpStatus.OK, "SUCCESS_USER_002", "조회 성공했습니다."),
    USER_LOGIN_SUCCESS(HttpStatus.OK,"SUCCESS_USER_003", "로그인 성공"),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
