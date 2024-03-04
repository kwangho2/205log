package com._205log.api.controller;

import com._205log.api.exception.InvalidRequest;
import com._205log.api.request.PostCreate;
import com._205log.api.request.PostEdit;
import com._205log.api.request.PostSearch;
import com._205log.api.response.PostResponse;
import com._205log.api.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class PostController   {

    // Http Method
    // GET, POST, PUT, PATCH, DELETE, OPTIONS, HEAD, TRACE, CONNECT
    // 각각 기능과 특징 알아두기
    // 글 등록
    // Post Method

    private final PostService postService;

    @PostMapping("/posts")
    public void post(@RequestBody @Valid PostCreate request, @RequestHeader String authorization) {
        // 1. GET Parameter -> ??
        // 2. POST(body) value ?? 글작성과 무관한 값이 들어가게 되는 경우이다.
        // 3. Header
        if (authorization.equals("205")) {
            request.validate();

            postService.write(request);
        }
    }

    /**
     * /posts -> 글 전체 조회(검색 + 페이징)
     * /posts/{postId} -> 글 한개만 조회
     */
    @GetMapping("/posts/{postId}")
    public PostResponse get(@PathVariable Long postId){
        return postService.get(postId);
    }

    @GetMapping("/posts")
    public List<PostResponse> getList(@ModelAttribute PostSearch postSearch) {
        return postService.getList(postSearch);
    }

    @PatchMapping("/posts/{postId}")
    public void edit(@PathVariable Long postId, @RequestBody @Valid PostEdit request, @RequestHeader String authorization) {
        if (authorization.equals("205")) {
            postService.edit(postId, request);
        }
    }

    @DeleteMapping("/posts/{postId}")
    public void delete(@PathVariable Long postId, @RequestHeader String authorization){
        if (authorization.equals("205")) {
            postService.delete(postId);
        }
    }
}
