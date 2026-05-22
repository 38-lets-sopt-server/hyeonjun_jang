package org.sopt.Domain.post.controller;


import io.swagger.v3.oas.annotations.Operation;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;

import org.sopt.Domain.like.dto.response.LikeResponse;
import org.sopt.Domain.post.exception.PostNotFoundException;
import org.sopt.Domain.post.dto.request.CreatePostRequest;
import org.sopt.Domain.post.dto.request.UpdatePostRequest;
import org.sopt.Domain.post.dto.response.CreatePostResponse;
import org.sopt.Domain.post.dto.response.PostResponse;
import org.sopt.global.response.BaseResponse;
import org.sopt.global.swagger.ApiExceptions;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@Tag(name = "Post", description = "게시글 관련 API")
public interface PostApi {


    @Operation(summary = "좋아요 토글",
            description = "좋아요를 추가하거나 삭제합니다.",
            security = @SecurityRequirement(name = "bearerAuth"))
    ResponseEntity<BaseResponse<LikeResponse>> LikePost(
            Authentication authentication,
            @PathVariable Long postId
    );

    @Operation(summary = "게시글 작성",
            description = "새로운 게시글을 작성합니다.",
            security = @SecurityRequirement(name = "bearerAuth"))
    ResponseEntity<BaseResponse<CreatePostResponse>> createPost(
            Authentication authentication,
            @Valid @RequestBody CreatePostRequest request
    );

    @Operation(summary = "게시글 목록 조회",
            description = "게시글 목록을 조회합니다.",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiExceptions({PostNotFoundException.class})
    ResponseEntity<BaseResponse<List<PostResponse>>> getAllPosts();


    @Operation(summary = "게시글 상세 조회",
            description = "상세 게시글을 조회합니다.")
    @ApiExceptions({PostNotFoundException.class})
    @GetMapping("/{id}")
    ResponseEntity<BaseResponse<PostResponse>> getPost(
            @Parameter(description = "게시글 ID", example = "1")
            @PathVariable Long id
    );

    @Operation(summary = "게시글 수정",
            description = "게시글을 수정합니다.",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "게시글 수정 성공"),
            @ApiResponse(responseCode = "404", description = "게시글을 찾을 수 없음 — 존재하지 않는 ID로 요청한 경우"),
            @ApiResponse(responseCode = "400", description = "잘못된 요청 — ID가 숫자가 아닌 경우")
    })
    ResponseEntity<BaseResponse<PostResponse>> updatePost(
            @PathVariable Long id,
            @RequestBody UpdatePostRequest request
    );


    @Operation(summary = "게시글 삭제",
            description = "게시글을 삭제합니다.",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "삭제 성공"),
            @ApiResponse(responseCode = "404", description = "게시글을 찾을 수 없음 — 존재하지 않는 ID로 요청한 경우"),
            @ApiResponse(responseCode = "400", description = "잘못된 요청 — ID가 숫자가 아닌 경우")
    })
    @DeleteMapping("/{id}")
    ResponseEntity<Void> deletePost(
            @PathVariable Long id
    );
}
