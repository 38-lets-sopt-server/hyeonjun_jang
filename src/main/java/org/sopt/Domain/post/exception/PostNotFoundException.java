package org.sopt.Domain.post.exception;

import org.sopt.Domain.post.exception.code.PostErrorCode;
import org.sopt.global.exception.BusinessException;



public class PostNotFoundException extends BusinessException {

    public PostNotFoundException() {
        super(PostErrorCode.POST_NOT_FOUND);
    }

}