package com.example.recruit_page_wwy;

import com.example.recruit_page_wwy.employment.Employment;
import com.example.recruit_page_wwy.employment.EmploymentRepository;
import com.example.recruit_page_wwy.user.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class HelloController {

    private final EmploymentRepository employmentRepository;

    @GetMapping("/")
    public String index(HttpSession session, HttpServletRequest request) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        System.out.println(sessionUser);

        List<Employment> jobs = employmentRepository.findTop4ByOrderByIdDesc();
        request.setAttribute("models", jobs);

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

//    @GetMapping("/mypage/employment")
//    public String employmentDashboard() {
//        return "employment/dashboard";
//    }

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


}
