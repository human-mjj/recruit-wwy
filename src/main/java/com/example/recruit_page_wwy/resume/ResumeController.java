package com.example.recruit_page_wwy.resume;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class ResumeController {
    private final ResumeService resumeService;
    private final HttpSession session;

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

    @PostMapping("/resume/save")
    public String resumeSave(ResumeRequest.SaveDTO saveDTO) {
        // 임시 테스트 user_id 설정
        saveDTO.setUser_id(1);
        System.out.println("입력값: " + saveDTO);
        resumeService.save(saveDTO);

        return "resume/list";
    }

    @GetMapping("/resume/1/update-form")
    public String resumeUpdateForm() {
        return "resume/update-form";
    }


}


