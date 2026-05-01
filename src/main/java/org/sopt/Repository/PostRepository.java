package org.sopt.Repository;

import org.sopt.Domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


//여기에 레포지토리 어노테이션 안적어도 됨
//왜냐면 상속해주면 Spring이 알아서 Bean에 올려줌
public interface PostRepository extends JpaRepository<Post, Long> {
    // save, findById, findAll, delete... 자동으로 제공돼요
    @Query("SELECT p FROM Post p JOIN FETCH p.user WHERE p.deletedAt IS NULL")
    List<Post> findAllWithUser();
}