package org.sopt.dto.response;

public class UpdatePostResponse {
    public Long id;
    public String message;

    public UpdatePostResponse(Long id, String message) {
        this.id = id;
        this.message = message;
    }

    public Long getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }
}
