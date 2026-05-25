package org.sopt.Domain.like.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

public record LikeResponse(
        @Schema(description = "좋아요 토글", example = "true")
        Boolean isLiked
) {
    public static LikeResponse create(Boolean isLiked){
        return new LikeResponse(isLiked);
    }
}
