package com.example.recruit_page_wwy.user;

import com.example.recruit_page_wwy._core.util.Resp;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
public class UserController {
    private final UserService userService;
    private final HttpSession session;

    // TODO : 예외 처리
    // MyPage
    @GetMapping("/s/api/mypage")
    public @ResponseBody ResponseEntity<?> myPage(HttpSession session) {
        User sessionUser = (User) session.getAttribute("sessionUser");

        UserResponse.MyPageDTO respDTO = new UserResponse.MyPageDTO(sessionUser);
        return Resp.ok(respDTO);
    }

    // TODO : 예외 처리
    // MyPageUpdate
    @PutMapping("/s/api/mypage")
    public @ResponseBody ResponseEntity<?> userUpdate(@RequestBody UserRequest.UpdateDTO reqDTO) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        UserResponse.DTO respDTO = userService.userUpdate(reqDTO, sessionUser);
        return Resp.ok(respDTO);
    }

    // UserJoin
    @PostMapping("/join-user")
    public @ResponseBody ResponseEntity<?> userJoin(@RequestBody UserRequest.UserDTO reqDTO) {
        UserResponse.DTO respDTO = userService.joinUser(reqDTO);
        return Resp.ok(respDTO);
    }

    // ComJoin
    @PostMapping("/join-com")
    public @ResponseBody ResponseEntity<?> comJoin(@RequestBody UserRequest.ComDTO reqDTO) {
        UserResponse.DTO respDTO = userService.joinCom(reqDTO);
        return Resp.ok(respDTO);
    }

    // Login
    @PostMapping("/login")
    public @ResponseBody ResponseEntity<?> login(@RequestBody UserRequest.LoginDTO reqDTO, HttpServletResponse response, HttpSession session) {
        UserResponse.TokenDTO respDTO = userService.login(reqDTO);
        return Resp.ok(respDTO);
    }

}
