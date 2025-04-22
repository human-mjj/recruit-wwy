package com.example.recruit_page_wwy.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class UserController {
    private final UserService userService;


    // JoinWayPage
    @GetMapping("/join-form")
    public String joinSelectForm() {
        return "user/joinway-form";
    }

    // UserJoinPage
    @GetMapping("/join-form/user")
    public String userJoinForm(UserRequest.UserDTO reqDTO) {
        return "user/user-join-form";
    }

    // UserJoin
    @PostMapping("/join")
    public String userJoin(UserRequest.UserDTO reqDTO) {
        userService.joinUser(reqDTO);
        System.out.println(reqDTO);
        return "redirect:/login-form";
    }

    // ComJoinPage
    @GetMapping("/join-form/com")
    public String comJoinForm(UserRequest.ComDTO reqDTO) {
        userService.joinCom(reqDTO);
        return "user/com-join-form";
    }

    @PostMapping("/login")
    public String login() {
        return "redirect:/";
    }

}
