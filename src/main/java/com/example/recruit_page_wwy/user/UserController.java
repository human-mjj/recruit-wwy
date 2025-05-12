package com.example.recruit_page_wwy.user;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class UserController {
    private final UserService userService;
    private final HttpSession session;

    // MyPage
    @GetMapping("/mypage")
    public String myPage(HttpServletRequest request) {

        User sessionUser = (User) session.getAttribute("sessionUser");

        UserResponse.MyPageDTO myDTO = new UserResponse.MyPageDTO(sessionUser);
        request.setAttribute("model", myDTO);
        return "/mypage/index";
    }

    // MyPageUpdate
    @PostMapping("/mypage/update")
    public String userUpdate(UserRequest.UpdateDTO reqDTO) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        session.setAttribute("sessionUser", userService.userUpdate(reqDTO, sessionUser));
        return "redirect:/";
    }

    // JoinWayPage
    @GetMapping("/join-form")
    public String joinSelectForm() {
        return "user/joinway-form";
    }

    // UserJoinPage
    @GetMapping("/join-form/user")
    public String userJoinForm() {
        return "user/user-join-form";
    }

    // UserJoin
    @PostMapping("/join-user")
    public String userJoin(UserRequest.UserDTO reqDTO) {
        userService.joinUser(reqDTO);
        return "redirect:/login-form";
    }

    // ComJoinPage
    @GetMapping("/join-form/com")
    public String comJoinForm() {
        return "user/com-join-form";
    }

    // ComJoin
    @PostMapping("/join-com")
    public String comJoin(UserRequest.ComDTO reqDTO) {
        userService.joinCom(reqDTO);
        System.out.println(reqDTO);
        return "redirect:/login-form";
    }

    // LoginPage
    @GetMapping("/login-form")
    public String loginForm() {
        return "user/login-form";
    }

    // TODO : 쿠키 비교 삭제
    // Login
    @PostMapping("/login")
    public String login(UserRequest.LoginDTO reqDTO, HttpServletResponse response) {
        User sessionUser = userService.login(reqDTO);
        session.setAttribute("sessionUser", sessionUser);

        // 유저 아이디 기억하는 로직
        if (reqDTO.getRememberMe() == null) {
            Cookie cookie = new Cookie("email", null);
            cookie.setMaxAge(0); // 즉시 만료
            response.addCookie(cookie);
        } else {
            Cookie cookie = new Cookie("email", reqDTO.getEmail());
            cookie.setMaxAge(60 * 60 * 24 * 7);
            response.addCookie(cookie);
        }

        return "redirect:/";
    }

    // TODO : 삭제 (안씀)
    // Logout
    @GetMapping("/logout")
    public String logout() {
        session.invalidate();
        return "redirect:/";
    }
}
