package org.sopt.Domain.user.exception.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.sopt.global.code.ErrorCode;
import org.springframework.http.HttpStatus;


@Getter
@AllArgsConstructor
public enum UserErrorCode implements ErrorCode {
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "USER_001", "사용자를 찾을 수 없습니다."),
    USER_EMAIL_DUPLICATED(HttpStatus.CONFLICT, "USER_002", "이메일이 사용 중입니다."),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;

}
