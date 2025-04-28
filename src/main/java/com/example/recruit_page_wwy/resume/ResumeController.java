package com.example.recruit_page_wwy.resume;

import com.example.recruit_page_wwy.user.User;
import com.example.recruit_page_wwy.user.UserResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

        // 구직자로 로그인 시 이력서 nav / 기업으로 로그인 시 추천 nav
        if (sessionUser != null) {
            UserResponse.MyPageDTO myDTO = new UserResponse.MyPageDTO(sessionUser);
            request.setAttribute("comCheck", myDTO);
            System.out.println(myDTO.getIsCompanyUser());
        } else {
            request.setAttribute("comCheck", null); // 로그인 안 한 경우
        }

        return "resume/list";
    }


    @GetMapping("/resume/{id}")
    public String resumeDetail(@PathVariable("id") Integer id, HttpServletRequest request) {
        User sessionUser = (User) session.getAttribute("sessionUser");

        ResumeResponse.DetailDTO detailDTO = resumeService.Detail(id);
        request.setAttribute("models", detailDTO);
        System.out.println(detailDTO.getId());

        // 구직자로 로그인 시 이력서 nav / 기업으로 로그인 시 추천 nav
        if (sessionUser != null) {
            UserResponse.MyPageDTO myDTO = new UserResponse.MyPageDTO(sessionUser);
            request.setAttribute("comCheck", myDTO);
            System.out.println(myDTO.getIsCompanyUser());
        } else {
            request.setAttribute("comCheck", null); // 로그인 안 한 경우
        }

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

    @GetMapping("/resume/{id}/update-form")
    public String resumeUpdateForm(@PathVariable("id") Integer id, HttpServletRequest request) {
        Resume resume = resumeService.findById(id);
        request.setAttribute("model", resume);
        return "resume/update-form";
    }

    @PostMapping("/resume/{id}/update")
    public String resumeUpdate(@PathVariable("id") Integer id, ResumeRequest.UpdateDTO updateDTO) {
        resumeService.update(id, updateDTO);
        return "redirect:/mypage/resume";
    }

    @PostMapping("/resume/{id}/delete")
    public String resumeDelete(@PathVariable("id") Integer resumeId) {
        resumeService.delete(resumeId);
        return "redirect:/mypage/resume";
    }

}


