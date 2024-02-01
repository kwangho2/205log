package com._205log.api.controller;

import com._205log.api.request.PostCreate;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
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
    public Map<String, String> post(@RequestBody @Valid PostCreate params, BindingResult result) throws Exception {
        // 데이터를 검증하는 이유
        //1. client 개발자가 깜박할 수 있다. 실수로 값을 안보낼 수 있다.
        //2. client bug로 값이 누락될 수 있다.
        //3. 외부의 나쁜 사람이 값을 임으로 조작해서 보낼 수 있다.
        //4. DB에 값을 저장할 때 의도치 않은 오류가 발생할 수 있다.
        //5. 서버 개발자의 편안함을 위해서
        if (result.hasErrors()) {
            List<FieldError> fieldErrors = result.getFieldErrors();
            FieldError firstFieldError = fieldErrors.get(0);
            String fieldName = firstFieldError.getField(); // title
            String errorMessage = firstFieldError.getDefaultMessage(); // ..에러메시지

            Map<String, String> error = new HashMap<>();
            error.put(fieldName, errorMessage);

            return error;
        }

        return Map.of();
    }
}
