package org.sopt.global.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;


@Getter
@AllArgsConstructor
public enum TempErrorCode  implements ErrorCode {

    LIKE_ALREADY_EXISTS(HttpStatus.CONFLICT, "LIKE_001", "이미 좋아요를 눌렀습니다."),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "SYS_001", "서버 내부 오류가 발생했습니다.")
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}

// 사용 예시
//throw new NotFoundException(ErrorCode.POST_NOT_FOUND);