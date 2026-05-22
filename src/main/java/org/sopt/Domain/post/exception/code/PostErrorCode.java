package org.sopt.Domain.post.exception.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.sopt.global.code.ErrorCode;
import org.springframework.http.HttpStatus;


@Getter
@AllArgsConstructor
public enum PostErrorCode implements ErrorCode {
    POST_NOT_FOUND(HttpStatus.NOT_FOUND, "POST_001", "게시글을 찾을 수 없습니다."),
    POST_INVALID_TITLE(HttpStatus.BAD_REQUEST, "POST_002", "게시글을 제목은 1~50자 이하입니다."),
    POST_INVALID_CONTENT(HttpStatus.BAD_REQUEST, "POST_003", "게시글 내용은 필수 입니다."),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
