package org.sopt.Domain.post.controller;


import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import org.sopt.Domain.like.dto.response.LikeResponse;
import org.sopt.Domain.like.exception.code.LikeSuccessCode;
import org.sopt.Domain.like.service.LikeService;
import org.sopt.Domain.post.exception.code.PostSuccessCode;
import org.sopt.Domain.post.dto.request.CreatePostRequest;
import org.sopt.Domain.post.dto.request.UpdatePostRequest;

import org.sopt.Domain.post.dto.response.CreatePostResponse;
import org.sopt.Domain.post.dto.response.PostResponse;
import org.sopt.global.response.BaseResponse;
import org.sopt.Domain.post.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/posts")
public class PostController implements PostApi {
    private final PostService postService;
    private final LikeService likeService;

    public PostController(PostService postService, LikeService likeService) {
        this.postService = postService;
        this.likeService = likeService;
    }

    @PostMapping("/{postId}/like")
    public ResponseEntity<BaseResponse<LikeResponse>> LikePost(
            Authentication authentication,
            @PathVariable("postId") Long postId
    ){
        Long userId = Long.parseLong(authentication.getName());
        Boolean isLiked = likeService.likeToggle(userId, postId);
        LikeSuccessCode successCode = isLiked
                ? LikeSuccessCode.LIKE_TOGGLE_SUCCEED
                : LikeSuccessCode.LIKE_TOGGLE_CANCELED;

        return ResponseEntity.status(successCode.getStatus())
                .body(BaseResponse.success(successCode, LikeResponse.create(isLiked)));
    }


    @PostMapping
    public ResponseEntity<BaseResponse<CreatePostResponse>> createPost(
            Authentication authentication,
            @Valid @RequestBody CreatePostRequest request
    ) {
        Long userId = Long.parseLong(authentication.getName());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(BaseResponse.success(PostSuccessCode.POST_CREATE_SUCCESS, postService.createPost(request, userId)));
    }

    @GetMapping
    public ResponseEntity<BaseResponse<List<PostResponse>>> getAllPosts() {
        List<PostResponse> response = postService.getAllPosts();
        return ResponseEntity.status(HttpStatus.OK)
                .body(BaseResponse.success(PostSuccessCode.POST_FIND_SUCCESS, response));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse<PostResponse>> getPost(
            @Parameter(description = "게시글 ID", example = "1")
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(BaseResponse.success(PostSuccessCode.POST_FIND_SUCCESS, postService.getPost(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BaseResponse<PostResponse>> updatePost(
            @PathVariable Long id,
            @RequestBody UpdatePostRequest request
    ) {
        PostResponse response = postService.updatePost(id, request);
        return ResponseEntity.status(HttpStatus.OK)
                .body(BaseResponse.success(PostSuccessCode.POST_UPDATE_SUCCESS, response));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(
            @PathVariable Long id
    ) {
        postService.deletePost(id);

        return ResponseEntity.noContent().build(); //제거 후 아무것도 반환하지 않음!
    }
}