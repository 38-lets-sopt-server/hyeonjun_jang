package org.sopt.Domain.auth.exception;

import org.sopt.Domain.auth.exception.code.AuthErrorCode;
import org.sopt.global.exception.BusinessException;

public class AuthUserException extends BusinessException {
    public AuthUserException() {
        super(AuthErrorCode.AUTH_USER_NOT_FOUND);
    }
}
