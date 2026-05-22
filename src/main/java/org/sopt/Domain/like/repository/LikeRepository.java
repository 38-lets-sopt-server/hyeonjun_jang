package org.sopt.Domain.like.repository;

import java.util.Optional;
import org.sopt.Domain.like.Like;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<Like, Long> {

    Optional<Like> findByUser_IdAndPost_Id(Long userId, Long postId);

    boolean existsByUser_IdAndPost_Id(Long userId, Long postId);

}
