package com.example.recruit_page_wwy._core.intercetor;

import com.example.recruit_page_wwy.user.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.servlet.HandlerInterceptor;

public class LoginIntercetor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handler) throws Exception {
        String uri = req.getRequestURI();
        System.out.println("uri: " + uri);

        HttpSession session = req.getSession();
        User sessionUser = (User) session.getAttribute("sessionUser");
        return true;
    }

}
