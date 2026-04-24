package org.sopt.dto.request;

public class CreatePostRequest {
    private String title; //get이 있으니까 접근 제어자를 private로 해도 됨.
    private String content;
    private String author;

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getAuthor() {
        return author;
    }


    public CreatePostRequest(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }
}