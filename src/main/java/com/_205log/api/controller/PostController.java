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
    public void post(@RequestBody @Valid PostCreate request) {
        // Case1. 저장한 데이터 Entity -> response로 응답하기
        // Case2. 저장한 데이터의 Primary_id -> response로 응답하기
        //          Client에서는 수신한id를 글 조회 API를 통해서 데이터를 수신받음
        // Case3. 응답 필요 없음 -> 클라이언트에서 모든 POST(글) 데이터 context를 잘 관리함
        // Bad Case: 서버에서 -> 반드시 이렇게 할껍니다. fix
        //          -> 서버에서 차라리 유연하게 대응하는게 좋습니다. -> 코드를 잘 짜야겠죠?! ㅎ
        //          -> 한 번에 일괄적으로 잘 처리되는 케이스가 없습니다. -> 잘 관리하는 형태가 중요합니다.
        request.validate();

        postService.write(request);
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
    public void edit(@PathVariable Long postId, @RequestBody @Valid PostEdit request) {
        postService.edit(postId, request);
    }

    @DeleteMapping("/posts/{postId}")
    public void delete(@PathVariable Long postId){
        postService.delete(postId);
    }
}
