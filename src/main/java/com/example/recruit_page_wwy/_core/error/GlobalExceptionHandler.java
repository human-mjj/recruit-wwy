package com.example.recruit_page_wwy._core.error;

import com.example.recruit_page_wwy._core.error.ex.Exception400;
import com.example.recruit_page_wwy._core.error.ex.Exception401;
import com.example.recruit_page_wwy._core.error.ex.Exception403;
import com.example.recruit_page_wwy._core.error.ex.Exception404;
import com.example.recruit_page_wwy._core.util.Script;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    //Bad Request - 잘못된 요청 (400)
    @ExceptionHandler(Exception400.class)
    public String ex400(Exception e) {
        return Script.back(e.getMessage());
    }

//    @ExceptionHandler(ExceptionApi400.class)
//    public String exApi400(Exception e) {
//        return Script.back(e.getMessage());
//    }

    //Unauthorized - 인증 안됨 (401)
    @ExceptionHandler(Exception401.class)
    public String ex401(Exception e) {
        return Script.href(e.getMessage(), "/login-form");
    }

//    @ExceptionHandler(ExceptionApi401.class)
//    public String exApi401(Exception e) {
//        return Script.href(e.getMessage(), "login-form");
//    }

    //Forbidden - 권한 없음 (403)
    @ExceptionHandler(Exception403.class)
    public String ex403(Exception e) {
        return Script.back(e.getMessage());
    }

//    @ExceptionHandler(ExceptionApi403.class)
//    public String exApi403(Exception e) {
//        return Script.alert(e.getMessage());
//    }

    //Not Found - 자원 찾을 수 없음 (404)
    @ExceptionHandler(Exception404.class)
    public String ex404(Exception e) {
        return Script.href(e.getMessage(), "/");
    }

//    @ExceptionHandler(ExceptionApi404.class)

    /// /    public String exApi404(Exception e) {
    /// /        return Script.back(e.getMessage());
    /// /    }

    //Internal Server Error - 나머지 에러 (500)
    @ExceptionHandler(Exception.class)
    public String exUnKnow(Exception e) {
        return Script.back(e.getMessage());
    }
}
