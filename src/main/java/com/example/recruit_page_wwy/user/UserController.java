package com.example.recruit_page_wwy.user;

import com.example.recruit_page_wwy._core.util.Resp;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@RequiredArgsConstructor
@Controller
public class UserController {
    private final UserService userService;
    private final HttpSession session;

    // TODO : 예외 처리
    // MyPage
    @GetMapping("/mypage")
    public @ResponseBody ResponseEntity<?> myPage(HttpSession session) {
        User sessionUser = (User) session.getAttribute("sessionUser");

        UserResponse.MyPageDTO respDTO = new UserResponse.MyPageDTO(sessionUser);
        // request.setAttribute("model", respDTO);
        return Resp.ok(respDTO);
    }

    // TODO : 예외 처리
    // MyPageUpdate
    @PostMapping("/mypage/update")
    public @ResponseBody ResponseEntity<?> userUpdate(@RequestBody UserRequest.UpdateDTO reqDTO) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        UserResponse.DTO respDTO = userService.userUpdate(reqDTO, sessionUser);
        // session.setAttribute("sessionUser", userService.userUpdate(reqDTO, sessionUser));
        return Resp.ok(respDTO);
    }

    // JoinWayPage
//    @GetMapping("/join-form")
//    public String joinSelectForm() {
//        return "user/joinway-form";
//    }

    // UserJoinPage
//    @GetMapping("/join-form/user")
//    public String userJoinForm() {
//        return "user/user-join-form";
//    }

    // UserJoin
    @PostMapping("/join-user")
    public @ResponseBody ResponseEntity<?> userJoin(@RequestBody UserRequest.UserDTO reqDTO) {
        UserResponse.DTO respDTO = userService.joinUser(reqDTO);
        return Resp.ok(respDTO);
    }

    // ComJoinPage
//    @GetMapping("/join-form/com")
//    public String comJoinForm() {
//        return "user/com-join-form";
//    }

    // ComJoin
    @PostMapping("/join-com")
    public @ResponseBody ResponseEntity<?> comJoin(@RequestBody UserRequest.ComDTO reqDTO) {
        UserResponse.DTO respDTO = userService.joinCom(reqDTO);
        return Resp.ok(respDTO);
    }

    // LoginPage
//    @GetMapping("/login-form")
//    public String loginForm() {
//        return "user/login-form";
//    }

    // TODO : 쿠키 비교 삭제
    // Login
    @PostMapping("/login")
    public @ResponseBody ResponseEntity<?> login(@RequestBody UserRequest.LoginDTO reqDTO, HttpServletResponse response, HttpSession session) {
        UserResponse.DTO respDTO = userService.login(reqDTO);
        session.setAttribute("sessionUser", respDTO.getUser());

        // 유저 아이디 기억하는 로직
//        if (reqDTO.getRememberMe() == null) {
//            Cookie cookie = new Cookie("email", null);
//            cookie.setMaxAge(0); // 즉시 만료
//            response.addCookie(cookie);
//        } else {
//            Cookie cookie = new Cookie("email", reqDTO.getEmail());
//            cookie.setMaxAge(60 * 60 * 24 * 7);
//            response.addCookie(cookie);
//        }

        return Resp.ok(respDTO);
    }

    // TODO : 삭제 (안씀)
    // Logout
//    @GetMapping("/logout")
//    public String logout() {
//        session.invalidate();
//        return "redirect:/";
//    }
}
