package org.sopt.Domain.auth.exception;

import org.sopt.Domain.auth.exception.code.AuthErrorCode;
import org.sopt.global.exception.BusinessException;

public class AuthInvalidToken extends BusinessException {

    public AuthInvalidToken(){
        super(AuthErrorCode.AUTH_INVALID_TOKEN);
    }
}
