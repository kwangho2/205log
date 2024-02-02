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
        // 1. 매번 메서드마다 값을 검증해야 한다.
        //      > 개발자가 까먹을 수 있다.
        //      > 검증 부분에서 버그가 발생할 여지가 높다.
        //      > 지겹다. (간지가 안난다.)
        // 2. 응닶값에 HashMap -> 응답 클래스를 만들어주는게 좋습니다.
        // 3. 여러개의 에러처리 힘듬
        // 4. 세 번이상의 반복적인 작업은 피해야한다. - 코드 && 개발에 관한 모든것 -> 자동화 고려
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
