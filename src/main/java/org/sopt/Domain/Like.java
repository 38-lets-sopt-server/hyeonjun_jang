package org.sopt.Domain;

import jakarta.persistence.*;
//여기서 userID와 PostID를 외래키로 가져와야함!
@Entity
@Table(name = "Likes")  // "Likes"는 SQL 예약어라 테이블명을 변경해요
public class Like{
    @Id
    private Long id;

    protected Like() {}
    // post 테이블에 user_id FK 컬럼이 생겨요 (외래 키 )

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}

