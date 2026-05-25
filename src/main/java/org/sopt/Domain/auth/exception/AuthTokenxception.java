package org.sopt.Domain.auth.exception;

import org.sopt.Domain.auth.exception.code.AuthErrorCode;
import org.sopt.global.exception.BusinessException;

public class AuthTokenxception extends BusinessException {
    public AuthTokenxception() {
        super(AuthErrorCode.AUTH_TOKEN_NOT_FOUND);
    }
}
