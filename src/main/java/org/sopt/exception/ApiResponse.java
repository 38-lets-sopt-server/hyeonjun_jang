package org.sopt.exception;

import org.springframework.http.HttpStatus;

public record ApiResponse<T>(
        boolean success,
        String message,
        T data,
        int statusCode
) {
    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(true, "요청이 성공했습니다.", data, HttpStatus.OK.value());
    }

    public static <T> ApiResponse<T> success(String message, T data) {
        return new ApiResponse<>(true, message, data, HttpStatus.OK.value());
    }

    public static <T> ApiResponse<T> error(String message, HttpStatus status) {
        return new ApiResponse<>(false, message, null, status.value());
    }
}

//NullPointerException -> 사실 404에러지만 잡지 않으면 500에러
//RuntimeException 계열로 많이 잡지만 성공 응답과 에러 응답의 형식이 달라지기 때문에 공통 응답 객체를 설계