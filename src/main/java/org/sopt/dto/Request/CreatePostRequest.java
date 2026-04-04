package org.sopt.dto.Request;

class CreatePostRequest {
    String title;
    String content;
    String author;

    public CreatePostRequest(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }
}