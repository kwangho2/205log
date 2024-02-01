package com._205log.api.controller;

import com._205log.api.request.PostCreate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@RestController
public class PostController   {

    // Http Method
    // GET, POST, PUT, PATCH, DELETE, OPTIONS, HEAD, TRACE, CONNECT
    // 각각 기능과 특징 알아두기
    // 글 등록
    // Post Method

    @PostMapping("/posts")
//    public String post(@RequestParam String title, @RequestParam String content){
//    public String post(@RequestParam Map<String, String> params){
    public String post(@RequestBody PostCreate params) throws Exception {
        // 데이터를 검증하는 이유
        //1. client 개발자가 깜박할 수 있다. 실수로 값을 안보낼 수 있다.
        //2. client bug로 값이 누락될 수 있다.
        //3. 외부의 나쁜 사람이 값을 임으로 조작해서 보낼 수 있다.
        //4. DB에 값을 저장할 때 의도치 않은 오류가 발생할 수 있다.
        //5. 서버 개발자의 편안함을 위해서
//        log.info("title={}, content={}", title, content);
//        log.info("params={}", params);
        log.info("params={}", params.toString());
//        String title = params.get("title");
        String title = params.getTitle();
        if (title == null || title.equals("")){
            // 1. 빡세다. (노가다)
            // 2. 개발팁 -> 무언가 3번이상 반복작업을 할때 내가 뭔가 잘못하고 있는건 아닐지 의심한다.
            // 3. 누락가능성
            // 4. 생각보다 검증해야할 게 많다. (꼼꼼하지 않을 수 있다.)
            // 5. 뭔가 개발자 스럽지 않다. -> 간지 X
            throw new Exception("타이들 값이 없어요!");
        }

        String content = params.getContent();
        if (content == null || content.equals("")){
            //error
        }

        return "Hello World";
    }
}
