package org.sopt.dto.response;

import org.sopt.Domain.Post;

import java.util.Optional;

public class PostResponse {
    private Long id;          // 게시글 상세 화면 — 특정 게시글 식별용
    private String title;     // 목록, 상세, 글쓰기 화면 — 제목
    private String content;   // 목록(미리보기), 상세(전체) 화면 — 내용
    private String author;    // 목록, 상세 화면 — 글쓴이
    private String createdAt; // 목록, 상세 화면 — 작성 시각

    public PostResponse(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.author = post.getAuthor();
        this.createdAt = post.getCreatedAt();
    }

    public static PostResponse From(Optional<Post> post) {
        return new PostResponse(post.get());
    }


    //getter가 없으니까 PostList가 반환되지 않았었다..
    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getAuthor() {
        return author;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String toString() {
        return "[" + id + "] " + title + " - " + author + " (" + createdAt + ")\n" + content;
    }
}