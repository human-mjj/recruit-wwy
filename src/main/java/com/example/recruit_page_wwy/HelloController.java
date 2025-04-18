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

    @GetMapping("/community/1")
    public String communityDetail() {
        return "community/detail";
    }

    @GetMapping("/community")
    public String communityList() {
        return "community/list";
    }

    @GetMapping("/community/save-form")
    public String communitySaveForm() {
        return "community/save-form";
    }

    @GetMapping("/community/1/update-form")
    public String communityUpdateForm() {
        return "community/update-form";
    }

    @PostMapping("/community/1/delete")
    public String deleteBoard() {
        return "redirect:/community";
    }

    @GetMapping("/mypage/job")
    public String jobDashboard() {
        return "job/dashboard";
    }

    @GetMapping("/job/1")
    public String jobDetail() {
        return "job/detail";
    }

    @GetMapping("/job")
    public String jobList() {
        return "job/list";
    }

    @GetMapping("/job/save-form")
    public String jobSaveForm() {
        return "job/save-form";
    }

    @GetMapping("/job/1/update-form")
    public String jobUpdateForm() {
        return "job/update-form";
    }

    @GetMapping("/mypage/scrap/com")
    public String scrapComList() {
        return "mypage/com-scrap";
    }

    @GetMapping("/mypage/scrap")
    public String scrapList() {
        return "mypage/user-scrap";
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
