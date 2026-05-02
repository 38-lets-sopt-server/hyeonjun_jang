package org.sopt.Domain;
import jakarta.persistence.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.sopt.dto.BaseTimeEntity;

import java.time.LocalDateTime;

/*
postRepository.delete(post)를 호출해도 실제 DELETE 쿼리가 나가지 않고 deleted_at이 채워져요.
이후 조회 시 @Where 조건 덕분에 삭제된 게시글은 자동으로 제외돼요.
 */

@SQLDelete(sql = "UPDATE post SET deleted_at = NOW() WHERE id = ?")
@Where(clause = "deleted_at IS NULL")
@Entity
public class Post extends BaseTimeEntity {
    @Id //PK를 의미하는 어노테이션
    @GeneratedValue(strategy = GenerationType.IDENTITY) //전략이 4가지 정도 된다.
    private Long id;
    private String title;
    private String content;
    private LocalDateTime deletedAt;

    //Post만 먼저 조회하고, User 정보는 실제로 접근하는 시점에 별도 쿼리로 가져와요.
    @ManyToOne(fetch = FetchType.LAZY)  // User : Post = 1 : N
    @JoinColumn(name = "user_id")       // post 테이블에 user_id FK 컬럼이 생겨요 (외래 키 )
    // 스네일 케이스로 작성됨 DB에 실제로 들어가는 컬럼 이름을 적는 게 관례


    private User user;

    public User getUser() {
        return user;
    }

    protected Post() {} // JPA 기본 생성자
    //빈 껍데기를 만들려고 함

    public Post(String title, String content, User user) {
        this.title = title;
        this.content = content;
        this.user = user;
    }


    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public Long getId() {
        return this.id;
    }

    public String getTitle() {
        return this.title;
    }

    public String getContent() {
        return this.content;
    }
}