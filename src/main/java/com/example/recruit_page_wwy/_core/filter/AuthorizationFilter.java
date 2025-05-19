package com.example.recruit_page_wwy._core.filter;

import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.example.recruit_page_wwy._core.util.JwtUtil;
import com.example.recruit_page_wwy._core.util.Resp;
import com.example.recruit_page_wwy.user.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

public class AuthorizationFilter implements Filter {
    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        String accessToken = request.getHeader("Authorization");

        try {
            if (accessToken == null || accessToken.isBlank()) throw new RuntimeException("토큰을 전달해주세요");
            if (!accessToken.startsWith("Bearer ")) throw new RuntimeException("Bearer 프로토콜 지켜야지 짜식아");

            accessToken = accessToken.replace("Bearer ", "");


            User user = JwtUtil.verify(accessToken);

            // 토큰 재검증 회피를 위한 임시 저장용 session
            HttpSession session = request.getSession();
            session.setAttribute("sessionUser", user);

            chain.doFilter(request, response);
        } catch (TokenExpiredException e1) {
            e1.printStackTrace();
            exResponse(response, "토큰이 만료되었습니다");
        } catch (JWTDecodeException | SignatureVerificationException e2) {
            e2.printStackTrace();
            exResponse(response, "토큰 검증에 실패했어요");
        } catch (RuntimeException e3) {
            e3.printStackTrace();
            exResponse(response, e3.getMessage());
        }


    }

    // Filter - DS를 거치지 않고 바로 Tomcat으로 가기 때문에 직접 ResponseEntity를 꺼내줘야 함
    private void exResponse(HttpServletResponse response, String msg) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        response.setStatus(401);
        PrintWriter out = response.getWriter();

        Resp<?> resp = Resp.fail(401, msg);
        String responseBody = new ObjectMapper().writeValueAsString(resp);
        out.println(responseBody);
    }
}