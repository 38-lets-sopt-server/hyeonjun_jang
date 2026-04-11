package org.sopt.dto.response;

import org.sopt.Domain.Post;

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


    public String toString() {
        return "[" + id + "] " + title + " - " + author + " (" + createdAt + ")\n" + content;
    }
}