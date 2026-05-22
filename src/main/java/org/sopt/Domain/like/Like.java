package org.sopt.Domain.like;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.sopt.Domain.post.Post;
import org.sopt.Domain.user.entity.User;

//여기서 userID와 PostID를 외래키로 가져와야함!
@Entity
@Getter
@Table(name = "Likes",
        uniqueConstraints  = @UniqueConstraint(
        name = "uk_likes_user_post",
        columnNames = {"user_id", "post_id"}
))  // "Likes"는 SQL 예약어라 테이블명을 변경해요
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Like{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;


    private Like(User user, Post post) {
        this.user = user;
        this.post = post;
    }

    public static Like create(User user, Post post) {
        return new Like(user, post);
    }

}

