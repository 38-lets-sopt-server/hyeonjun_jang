package org.sopt.global.api.exception;

import org.sopt.global.api.code.ErrorCode;

public class NotFoundException extends RuntimeException {
    private final ErrorCode errorCode;

    public NotFoundException(ErrorCode errorCode) {
        super(errorCode.getMessage()); // ErrorCode에 메시지를 정의해두면 깔끔
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }
}
