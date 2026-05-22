package org.sopt.Domain.like.exception.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.sopt.global.code.SuccessCode;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum LikeSuccessCode implements SuccessCode {
    LIKE_TOGGLE_SUCCEED(HttpStatus.CREATED,"LIKE_SUCCESS_001","좋아요를 눌렀어요."),
    LIKE_TOGGLE_CANCELED(HttpStatus.OK,"LIKE_SUCCESS_002","좋아요를 취소했어요."),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}