package com.example.recruit_page_wwy.resume;

import com.example.recruit_page_wwy.user.User;
import jakarta.servlet.http.HttpServletRequest;
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
    public String resumeList(HttpServletRequest request) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        request.setAttribute("models", resumeService.findAll(sessionUser.getId()));
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
        User sessionUser = (User) session.getAttribute("sessionUser");
        saveDTO.setUser_id(sessionUser.getId());
        resumeService.save(saveDTO);
        return "redirect:/mypage/resume";
    }

    @GetMapping("/resume/1/update-form")
    public String resumeUpdateForm() {
        return "resume/update-form";
    }


}


