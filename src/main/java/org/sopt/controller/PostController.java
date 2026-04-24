package org.sopt.controller;

import org.sopt.dto.request.CreatePostRequest;
import org.sopt.dto.request.UpdatePostRequest;
import org.sopt.exception.ApiResponse;
import org.sopt.dto.response.CreatePostResponse;
import org.sopt.dto.response.PostResponse;
import org.sopt.dto.response.UpdatePostResponse;
import org.sopt.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


//그냥 “요청 받아서 넘기고, 결과 받아서 응답하는” 얇은 계층
//비즈니스 로직 직접 처리 xx
//비즈니스 로직은 Service에게, 데이터 저장은 Repository에게 위임해요. Controller 자체는 최대한 얇게 유지

@RestController //이걸 쓰면 자동으로 JSON으로 만들어서 스프링이 반환한다.
@RequestMapping("/posts") //이 컨트롤러의 모든 메서드에 /posts라는 공통 경로를 붙여준다

public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping //엔드포인트 정의
    public ResponseEntity<ApiResponse<CreatePostResponse>> createPost(
            @RequestBody CreatePostRequest request
            //HTTP 요청의 Body에 담긴 JSON을 Java 객체로 변환해준다.
    ) {
        CreatePostResponse response = postService.createPost(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success(response));
        //상태코드 + 응답!
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<PostResponse>>> getAllPosts() {
        List<PostResponse> response = postService.getAllPosts();
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.success(response));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<PostResponse>> getPost(
            @PathVariable Long id
    ) {
        PostResponse response = postService.getPost(id);
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.success(response));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<UpdatePostResponse>> updatePost(
            @PathVariable Long id, //posts/1 에서 id에 1L이 들어오게 하는 url의 변수 -> 파라미터!
            @RequestBody UpdatePostRequest request
    ) {
        UpdatePostResponse response = postService.updatePost(id, request);
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.success(response));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(
            @PathVariable Long id
    ) {
        postService.deletePost(id);

        return ResponseEntity.noContent().build(); //제거 후 아무것도 반환하지 않음!
    }
}