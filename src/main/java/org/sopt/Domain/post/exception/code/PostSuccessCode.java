package org.sopt.Domain.post.exception.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.sopt.global.code.SuccessCode;
import org.springframework.http.HttpStatus;


@Getter
@AllArgsConstructor
public enum PostSuccessCode implements SuccessCode {
    POST_CREATE_SUCCESS(HttpStatus.CREATED, "SUCCESS_POST_001", "게시글을 등록합니다."),
    POST_FIND_SUCCESS(HttpStatus.OK, "SUCCESS_POST_002", "게시글을 조회 성공."),
    POST_UPDATE_SUCCESS(HttpStatus.OK, "SUCCESS_POST_003", "게시글 수정 성공.");


    private final HttpStatus status;
    private final String code;
    private final String message;
}
