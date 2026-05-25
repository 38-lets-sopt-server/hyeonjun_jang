package org.sopt.global.response;


import org.sopt.global.code.ErrorCode;
import org.sopt.global.code.SuccessCode;
import org.sopt.global.code.TempErrorCode;
import org.springframework.http.HttpStatus;


public record BaseResponse<T>(
        int status,
        String code,
        String message,
        T data
) {

    public static <T> BaseResponse<T> success(final SuccessCode successCode, final T data) {
        return new BaseResponse<>(200, successCode.name(), successCode.getMessage(), data);
    }

    public static BaseResponse<Void> success(final SuccessCode successCode) {
        return success(successCode, null);
    }

    public static BaseResponse<Void> error(final ErrorCode errorCode) {
        return new BaseResponse<>(errorCode.getStatus().value(), errorCode.name(), errorCode.getMessage(), null);
    }

    public static BaseResponse<Void> error(final Exception e) {
        TempErrorCode errorMessage = TempErrorCode.INTERNAL_SERVER_ERROR;
        String consoleMessage = e.getClass().getSimpleName() + ": " + e.getMessage();
        return new BaseResponse<>(errorMessage.getStatus().value(), consoleMessage, errorMessage.getMessage(), null);
    }

    public static BaseResponse<Void> validationError(final String validationErrorMessage) {
        return new BaseResponse<>(HttpStatus.BAD_REQUEST.value(), "VALIDATION_ERROR", validationErrorMessage, null);
    }
}
