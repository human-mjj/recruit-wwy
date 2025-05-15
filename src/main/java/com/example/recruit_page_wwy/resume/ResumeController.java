package com.example.recruit_page_wwy.resume;

import com.example.recruit_page_wwy.user.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequiredArgsConstructor
@Controller
public class ResumeController {
    private final ResumeService resumeService;
    private final HttpSession session;

    // TODO : 예외 추가
    @GetMapping("/mypage/resume")
    public String resumeList(HttpServletRequest request, @RequestParam(required = false, value = "page", defaultValue = "1") Integer page) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        request.setAttribute("model", resumeService.findAll(sessionUser.getId(), page));
        return "resume/list";
    }

    // TODO : 예외 추가
    @GetMapping("/resume/{id}")
    public String resumeDetail(@PathVariable("id") Integer resumeId, HttpServletRequest request) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        ResumeResponse.DetailDTO detailDTO = resumeService.detailView(resumeId, sessionUser);
        request.setAttribute("model", detailDTO);
        System.out.println(detailDTO.getIsScrap());
        System.out.println(detailDTO.getLetter());
        return "resume/detail";
    }

    // TODO : 예외 추가
    @GetMapping("/resume/save-form")
    public String resumeSaveForm(HttpServletRequest request) {
        ResumeResponse.TableDTO tableDTO = resumeService.viewJobAndStackList();
        request.setAttribute("model", tableDTO);
        return "resume/save-form";
    }

    // TODO : 예외 추가
    @PostMapping("/resume/save")
    public String resumeSave(ResumeRequest.SaveDTO saveDTO) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        resumeService.save(saveDTO, sessionUser);
        return "redirect:/mypage/resume";
    }

    // TODO : 예외 추가
    @GetMapping("/resume/{id}/update-form")
    public String resumeUpdateForm(@PathVariable("id") Integer id, HttpServletRequest request) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        ResumeResponse.UpdateViewDTO updateViewDTO = resumeService.findById(id);
        request.setAttribute("model", updateViewDTO);
        return "resume/update-form";
    }

    // TODO : 예외 추가
    @PostMapping("/resume/{id}/update")
    public String resumeUpdate(@PathVariable("id") Integer id, ResumeRequest.SaveDTO updateDTO) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        resumeService.update(id, updateDTO, sessionUser);
        return "redirect:/mypage/resume";
    }

    // TODO : 예외 추가
    @PostMapping("/resume/{id}/delete")
    public String resumeDelete(@PathVariable("id") Integer resumeId) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        resumeService.delete(resumeId, sessionUser);
        return "redirect:/mypage/resume";
    }
}


