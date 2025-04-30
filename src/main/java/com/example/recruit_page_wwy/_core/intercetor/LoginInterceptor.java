package com.example.recruit_page_wwy._core.intercetor;

import com.example.recruit_page_wwy._core.error.ex.Exception401;
import com.example.recruit_page_wwy._core.error.ex.ExceptionApi401;
import com.example.recruit_page_wwy.user.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.servlet.HandlerInterceptor;

public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handler) throws Exception {
        String uri = req.getRequestURI();
        System.out.println("uri: " + uri);

        HttpSession session = req.getSession();
        User sessionUser = (User) session.getAttribute("sessionUser");

        if (sessionUser == null) {
            if (uri.contains("/api")) {
                throw new ExceptionApi401("로그인 후 사용해주세요");
            } else {
                throw new Exception401("로그인 후 사용해주세요");
            }
        }
        return true;
    }

}
