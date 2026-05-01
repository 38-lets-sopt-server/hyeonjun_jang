package org.sopt.service;

import org.sopt.Domain.Post;
import org.sopt.Domain.User;
import org.sopt.Repository.PostRepository;
import org.sopt.Repository.UserRepository;
import org.sopt.dto.request.CreatePostRequest;
import org.sopt.dto.request.UpdatePostRequest;
import org.sopt.dto.response.*;
import org.sopt.global.api.exception.NotFoundException;
import org.sopt.global.api.code.ErrorCode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
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
    @Transactional  // 저장 → DB 변경 발생 → 트랜잭션 커밋 시 반영
    public CreatePostResponse createPost(CreatePostRequest request) {

        User user = userRepository.findById(request.userId())
                .orElseThrow(() -> new NotFoundException(ErrorCode.USER_NOT_FOUND));
        Post post = new Post(request.title(), request.content(), user);

        postRepository.save(post);  // 영속성 컨텍스트에 올라감 → 커밋 시 INSERT
        return new CreatePostResponse(post.getId(), "게시글 등록 완료!");
    }

    @Transactional(readOnly = true)
    public List<PostResponse> getAllPosts() {
        return postRepository.findAll()
                .stream()
                .map(PostResponse::from)
                .toList();
    }

    @Transactional(readOnly = true) // 조회 전용 → 더티 체킹 안 함 → 성능 최적화
    public PostResponse getPost(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ErrorCode.POST_NOT_FOUND));

        return PostResponse.from(post);
    }

    @Transactional  // 변경 → 더티 체킹으로 save() 없이 자동 UPDATE
    public PostResponse updatePost(Long id, UpdatePostRequest request) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ErrorCode.POST_NOT_FOUND));
        post.update(request.getTitle(), request.getContent());
        // save() 호출 없어도 트랜잭션 커밋 시 UPDATE 쿼리 자동 실행
        return PostResponse.from(post);
    }

    // DELETE 📝 과제
    @Transactional
    public void deletePost(Long id) {
        postRepository.deleteById(id);

    }


}