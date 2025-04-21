package com.example.recruit_page_wwy;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HelloController {

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/board/1")
    public String boardDetail() {
        return "board/detail";
    }

    @GetMapping("/board")
    public String boardList() {
        return "board/list";
    }

    @GetMapping("/board/save-form")
    public String boardSaveForm() {
        return "board/save-form";
    }

    @GetMapping("/board/1/update-form")
    public String boardUpdateForm() {
        return "board/update-form";
    }

    @PostMapping("/board/1/delete")
    public String deleteBoard() {
        return "redirect:/board";
    }

    @GetMapping("/mypage/employment")
    public String employmentDashboard() {
        return "employment/dashboard";
    }

    @GetMapping("/employment/1")
    public String employmentDetail() {
        return "employment/detail";
    }

    @GetMapping("/employment")
    public String employmentList() {
        return "employment/list";
    }

    @GetMapping("/employment/save-form")
    public String employmentSaveForm() {
        return "employment/save-form";
    }

    @GetMapping("/employment/1/update-form")
    public String employmentUpdateForm() {
        return "employment/update-form";
    }

    @GetMapping("/mypage/scrap/com")
    public String scrapComList() {
        return "scrap/com-scrap";
    }

    @GetMapping("/mypage/scrap")
    public String scrapList() {
        return "scrap/user-scrap";
    }

    @GetMapping("/mypage")
    public String mypage() {
        return "mypage/index";
    }

    @GetMapping("/mypage/apply")
    public String applyList() {
        return "resume/apply-list";
    }

    @GetMapping("/mypage/apply/com")
    public String applyManageList() {
        return "resume/com-apply-list";
    }

    @GetMapping("/match")
    public String matchList() {
        return "match/list";
    }

    @GetMapping("/match/com")
    public String matchComList() {
        return "match/com-list";
    }

    @GetMapping("/mypage/resume")
    public String resumeList() {
        return "resume/list";
    }

    @GetMapping("/resume/1")
    public String resumeDetail() {
        return "resume/detail";
    }

    @GetMapping("/resume/save-form")
    public String resumeSaveForm() {
        return "resume/save-form";
    }

    @GetMapping("/resume/1/update-form")
    public String resumeUpdateForm() {
        return "resume/update-form";
    }

    @GetMapping("/login-form")
    public String loginForm() {
        return "user/login-form";
    }

    @GetMapping("/join-form")
    public String joinSelectForm() {
        return "user/joinway-form";
    }

    @GetMapping("/join-form/user")
    public String userJoinForm() {
        return "user/user-join-form";
    }

    @GetMapping("/join-form/com")
    public String comJoinForm() {
        return "user/com-join-form";
    }

    @GetMapping("/logout")
    public String logout() {
        return "redirect:/";
    }

    @PostMapping("/login")
    public String login() {
        return "redirect:/";
    }

    @PostMapping("/join")
    public String join() {
        return "redirect:/login-form";
    }

}
