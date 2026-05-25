package org.sopt.global.code;

import org.springframework.http.HttpStatus;

public interface SuccessCode {

    String name();

    HttpStatus getStatus();

    String getMessage();
}