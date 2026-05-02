package org.sopt.global.api.response;

import ch.qos.logback.core.spi.ErrorCodes;
import org.sopt.global.api.code.ErrorCode;

public record BaseResponse<T>(
        boolean success,
        String message,
        T data
) {

    public static <T> BaseResponse<T> success(String message, T data) {
        return new BaseResponse<>(true, message, data);
    }

    public static <T> BaseResponse<T> success(T data) {
        return new BaseResponse<>(true, "요청이 성공했습니다.", data);
    }

    public static BaseResponse<Void> error(ErrorCode errorCode) {
        return new BaseResponse<>(false, errorCode.getMessage(), null);
    }

    public static BaseResponse<Void> error(String message) {
        return new BaseResponse<>(false, message, null);
    }
}