package org.sopt.Domain.user.exception;

import org.sopt.global.code.ErrorCode;
import org.sopt.global.exception.BusinessException;

public class UserException extends BusinessException {
    public UserException(ErrorCode errorCode) {
        super(errorCode);
    }
}
