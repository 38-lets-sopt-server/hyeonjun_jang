package org.sopt.service;

import org.sopt.Domain.Post;
import org.sopt.Repository.PostRepository;
import org.sopt.dto.request.CreatePostRequest;
import org.sopt.dto.request.UpdatePostRequest;
import org.sopt.dto.response.*;
import org.sopt.exception.ApiResponse;
import org.sopt.exception.PostNotFoundException;
import org.sopt.exception.Validator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//과제
// @Service + Spring DI, 반환 타입이 void → DTO

@Service
public class PostService {
    private final PostRepository postRepository; // Spring이 주입해준다

    //세미나에서 lombok 이용하지 마라고 하셔서...
    public PostService (PostRepository postRepository){
        this.postRepository = postRepository;
    }

    // CREATE
    public CreatePostResponse createPost(CreatePostRequest request) {


        Validator.TitleValidator(request.getTitle());
        Validator.ContentValidator(request.getContent());


        LocalDateTime createdAt = java.time.LocalDateTime.now();
        Post post = new Post(postRepository.generateId(), request.getTitle(), request.getContent(), request.getAuthor(), createdAt);

        postRepository.save(post);
        return new CreatePostResponse(post.getId(), "게시글 등록 완료!");
    }


    // 자유게시판 목록 화면에서 호출돼요
    public List<PostResponse> getAllPosts() {
        List<PostResponse> responses = new ArrayList<>();
        List<Post> getPostList = postRepository.findAll();
        if (getPostList.isEmpty()) {
             ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.error("게시글을 찾을 수 없습니다.", HttpStatus.NOT_FOUND));
        } else {
            for (Post post: getPostList){
                responses.add(new PostResponse(post));
            }
        }
        return responses;
    }


    public PostResponse getPost(Long id) {
        Optional<Post> post = Optional.of(postRepository.findById(id)
                .orElseThrow(() -> new PostNotFoundException(id)));
        return PostResponse.From(post);
    }

    public UpdatePostResponse updatePost(Long id, UpdatePostRequest request){


        Optional<Post> findPost = postRepository.findById(id);


        Post post = findPost.orElseThrow(() ->  new PostNotFoundException(id));

        Validator.TitleValidator(request.getTitle());
        Validator.ContentValidator(request.getContent());

        post.update(request.getTitle(), request.getContent());

        return new UpdatePostResponse(post.getId(), "게시글 수정 완료!");
    }

    public void deletePost(Long id)  {
        boolean isDelted = postRepository.deleteById(id);
        if (!isDelted) {
            throw new PostNotFoundException(id);
        }
    }

}