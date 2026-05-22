package org.sopt.Domain.user.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "users")  // "user"는 SQL 예약어라 테이블명을 변경해요
public class User{

    @Id //id가 PK가 있어서 동명인 식별 가능하다
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nickname;

    @Column(nullable = false, unique = true)
    private String email;


    @Column(nullable = false)
    private String password;

    protected User() {}

    public User(String nickname, String email, String password) {
        this.nickname = nickname;
        this.email = email;
        this.password = password;
    }

    public Long getId() { // Post ID 설정 안해도 됨
        return this.id;
    }

    public String getNickname() {
        return this.nickname;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPassword() {
        return password;
    }
}