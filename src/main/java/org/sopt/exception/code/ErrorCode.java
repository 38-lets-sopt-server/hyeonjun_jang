package org.sopt.exception.code;

import org.springframework.http.HttpStatus;
// HTTP StatusCode와 절대 혼동되어서는 안돼서 "T-001"과 같은 문자열 코드를 추가 에러 코드를 사용한다.

public enum ErrorCode {
    //포스트 에러
    POST_NOT_FOUND(HttpStatus.NOT_FOUND, "POST_001","게시글을 찾을 수 없습니다"),

    //멤버
    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "MEMBER-001", "회원 정보가 없습니다."),
    ACCESS_TOKEN_EXPIRED(HttpStatus.UNAUTHORIZED, "MEMBER-002", "만료된 access token 입니다."),
    REFRESH_TOKEN_EXPIRED(HttpStatus.UNAUTHORIZED, "MEMBER-003", "만료된 refresh token 입니다.")

    ;

    ErrorCode(HttpStatus status, String code, String errorMessage) {
        this.status = status;
        this.code = code;
        this.errorMessage = errorMessage;
    }

    private final HttpStatus status;
    private final String code;
    private final String errorMessage;

}
