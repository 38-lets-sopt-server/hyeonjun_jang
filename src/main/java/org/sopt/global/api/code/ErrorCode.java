package org.sopt.global.api.code;

import org.springframework.http.HttpStatus;


public enum ErrorCode {
    POST_NOT_FOUND(HttpStatus.NOT_FOUND, "POST_001", "게시글을 찾을 수 없습니다."),

    POST_INVALID_TITLE(HttpStatus.BAD_REQUEST, "POST_002", "게시글을 제목은 1~50자 이하입니다."),
    POST_INVALID_CONTENT(HttpStatus.BAD_REQUEST, "POST_003", "게시글 내용은 필수 입니다."),

    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "USER_001", "사용자를 찾을 수 없습니다."),
    LIKE_ALREADY_EXISTS(HttpStatus.CONFLICT, "LIKE_001", "이미 좋아요를 눌렀습니다."),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "SYS_001", "서버 내부 오류가 발생했습니다.")
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;

    ErrorCode(HttpStatus status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}

// 사용 예시
//throw new NotFoundException(ErrorCode.POST_NOT_FOUND);