package org.sopt.exception;
public record ErrorResponse(String message) {
    private static int status;
    private static String error;
    private static String code;
}