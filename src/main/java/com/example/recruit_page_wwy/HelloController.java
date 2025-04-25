package com.example.recruit_page_wwy;


import com.example.recruit_page_wwy.employment.EmploymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class HelloController {

    private final EmploymentRepository employmentRepository;

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

    @GetMapping("/mypage/apply")
    public String applyList() {
        return "resume/apply-list";
    }

    @GetMapping("/mypage/apply/com")
    public String applyManageList() {
        return "resume/com-apply-list";
    }

}
