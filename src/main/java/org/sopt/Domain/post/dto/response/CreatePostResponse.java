package org.sopt.Domain.post.dto.response;

public class CreatePostResponse {
    public Long id;
    public String message;

    public CreatePostResponse(Long id, String message) {
        this.id = id;
        this.message = message;
    }
}