package com.example.recruit_page_wwy.user;

import jakarta.servlet.http.HttpServletRequest;
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
    public String myPage(UserResponse.MyPageDTO respDTO, HttpServletRequest request) {
        User sessionUser = (User) session.getAttribute("sessionUser");


        User myPageDTO = userService.mypage(sessionUser.getId());
        request.setAttribute("model", myPageDTO);
        System.out.println();
//        User sessionUser = userService.login(reqDTO);
//        session.setAttribute("sessionUser", sessionUser);


        return "/mypage/index";
    }

    // MyPageUpdate
    @PostMapping("/mypage/update")
    public String userUpdate(UserRequest.UpdateDTO reqDTO) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        userService.userUpdate(reqDTO, sessionUser.getId());

        return "redirect:/mypage";
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

    // Login
    @PostMapping("/login")
    public String login(UserRequest.LoginDTO reqDTO) {
        User sessionUser = userService.login(reqDTO);
        session.setAttribute("sessionUser", sessionUser);
        return "redirect:/";
    }

    // Logout
    @GetMapping("/logout")
    public String logout() {
        session.invalidate();
        return "redirect:/";
    }

}
