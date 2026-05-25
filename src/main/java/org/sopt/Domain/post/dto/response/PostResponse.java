package org.sopt.Domain.post.dto.response;

import org.sopt.Domain.post.Post;

import java.time.LocalDateTime;

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