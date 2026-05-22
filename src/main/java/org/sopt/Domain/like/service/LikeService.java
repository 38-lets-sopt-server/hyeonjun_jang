package org.sopt.Domain.like.service;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.sopt.Domain.like.Like;
import org.sopt.Domain.like.repository.LikeRepository;
import org.sopt.Domain.post.Post;
import org.sopt.Domain.post.exception.PostNotFoundException;
import org.sopt.Domain.post.repository.PostRepository;
import org.sopt.Domain.user.entity.User;
import org.sopt.Domain.user.exception.UserException;
import org.sopt.Domain.user.exception.code.UserErrorCode;
import org.sopt.Domain.user.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LikeService {

    private final LikeRepository likeRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    public boolean isLiked(Long userId, Long postId) {
        return likeRepository.existsByUser_IdAndPost_Id(userId, postId);
    }

    @Transactional
    public boolean likeToggle(Long userId, Long postId) {
        Optional<Like> existingLike =
                likeRepository.findByUser_IdAndPost_Id(userId, postId);

        if (existingLike.isPresent()) {
            likeRepository.delete(existingLike.get());
            return false;
        }

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserException(UserErrorCode.USER_NOT_FOUND));

        Post post = postRepository.findById(postId)
                .orElseThrow(PostNotFoundException::new);

        Like like = Like.create(user, post);
        likeRepository.save(like);

        return true;
    }
}