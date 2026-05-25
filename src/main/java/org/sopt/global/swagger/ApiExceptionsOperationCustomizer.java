package org.sopt.global.swagger;

import io.swagger.v3.oas.models.Operation;
import io.swagger.v3.oas.models.examples.Example;
import io.swagger.v3.oas.models.media.Content;
import io.swagger.v3.oas.models.media.MediaType;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.responses.ApiResponses;

import org.sopt.global.code.ErrorCode;
import org.sopt.global.exception.BusinessException;
import org.sopt.global.response.BaseResponse;
import org.springdoc.core.customizers.OperationCustomizer;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;

import java.util.LinkedHashMap;
import java.util.Map;


@Component
public class ApiExceptionsOperationCustomizer implements OperationCustomizer {

    private static final String MEDIA_TYPE_JSON = "application/json";

    @Override
    public Operation customize(final Operation operation, final HandlerMethod handlerMethod) {
        ApiExceptions apiExceptions = handlerMethod.getMethodAnnotation(ApiExceptions.class);
        if (apiExceptions == null) {
            return operation;
        }

        if (operation.getResponses() == null) {
            operation.setResponses(new ApiResponses());
        }
        ApiResponses responses = operation.getResponses();

        Map<HttpStatus, Map<String, Example>> groupedExamples = new LinkedHashMap<>();

        for (Class<? extends BusinessException> exceptionClass : apiExceptions.value()) {
            ErrorCode errorCode = resolveErrorCode(exceptionClass);
            Example example = new Example()
                    .summary(errorCode.name())
                    .description(errorCode.getMessage())
                    .value(BaseResponse.error(errorCode));

            groupedExamples
                    .computeIfAbsent(errorCode.getStatus(), k -> new LinkedHashMap<>())
                    .put(errorCode.name(), example);
        }

        groupedExamples.forEach((status, examples) -> mergeIntoResponses(responses, status, examples));

        return operation;
    }

    private void mergeIntoResponses(final ApiResponses responses, final HttpStatus status, final Map<String, Example> examples) {
        String statusKey = String.valueOf(status.value());

        ApiResponse apiResponse = responses.get(statusKey);
        if (apiResponse == null) {
            apiResponse = new ApiResponse().description(status.getReasonPhrase());
            responses.addApiResponse(statusKey, apiResponse);
        }

        Content content = apiResponse.getContent();
        if (content == null) {
            content = new Content();
            apiResponse.setContent(content);
        }

        MediaType mediaType = content.get(MEDIA_TYPE_JSON);
        if (mediaType == null) {
            mediaType = new MediaType();
            content.addMediaType(MEDIA_TYPE_JSON, mediaType);
        }

        Map<String, Example> merged = mediaType.getExamples();
        if (merged == null) {
            merged = new LinkedHashMap<>();
            mediaType.setExamples(merged);
        }
        merged.putAll(examples);
    }

    private ErrorCode resolveErrorCode(final Class<? extends BusinessException> exceptionClass) {
        try {
            BusinessException instance = exceptionClass.getDeclaredConstructor().newInstance();
            return instance.getErrorCode();
        } catch (ReflectiveOperationException e) {
            throw new IllegalStateException(
                    "@ApiExceptions에 등록된 " + exceptionClass.getName()
                            + " 클래스는 ErrorCode를 주입하는 no-args 생성자를 가져야 합니다.",
                    e);
        }
    }
}