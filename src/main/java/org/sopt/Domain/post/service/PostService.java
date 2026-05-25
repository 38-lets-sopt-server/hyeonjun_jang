package org.sopt.Domain.post.service;

import org.sopt.Domain.post.Post;
import org.sopt.Domain.post.repository.PostRepository;
import org.sopt.Domain.user.entity.User;
import org.sopt.Domain.user.exception.UserException;
import org.sopt.Domain.user.exception.code.UserErrorCode;
import org.sopt.Domain.user.repository.UserRepository;
import org.sopt.Domain.post.dto.request.CreatePostRequest;
import org.sopt.Domain.post.dto.request.UpdatePostRequest;
import org.sopt.Domain.post.dto.response.*;
import org.sopt.Domain.post.exception.PostNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public PostService(
            PostRepository postRepository,
            UserRepository userRepository
    ) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    // CREATE
    @Transactional
    public CreatePostResponse createPost(CreatePostRequest request,Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserException(UserErrorCode.USER_NOT_FOUND));
    
        Post post = new Post(request.title(), request.content(), user);

        postRepository.save(post);
        return new CreatePostResponse(post.getId(), "게시글 등록 완료!");
    }


    public List<PostResponse> getAllPosts() {
        return postRepository.findAll()
                .stream()
                .map(PostResponse::from)
                .toList();
    }

    public PostResponse getPost(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(PostNotFoundException::new);

        return PostResponse.from(post);
    }

    @Transactional
    public PostResponse updatePost(Long id, UpdatePostRequest request) {
        Post post = postRepository.findById(id)
                .orElseThrow(PostNotFoundException::new);
        post.update(request.getTitle(), request.getContent());
        // save() 호출 없어도 트랜잭션 커밋 시 UPDATE 쿼리 자동 실행
        return PostResponse.from(post);
    }


    @Transactional
    public void deletePost(Long id) {
        postRepository.deleteById(id);

    }


}