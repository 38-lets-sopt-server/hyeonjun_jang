package org.sopt.dto.response;

import org.sopt.Domain.Post;

import java.time.LocalDateTime;
import java.util.Optional;

public record PostResponse(
        String title,
        String author,
        String content,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
){

    public static PostResponse from(Post post) {
        return new PostResponse(
                post.getTitle(),
                post.getUser().getNickname(),
                post.getContent(),
                post.getCreatedAt(),
                post.getUpdatedAt()
        );
    }}