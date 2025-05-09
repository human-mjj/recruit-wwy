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

    @GetMapping("/mypage/resume")
    public String resumeList(HttpServletRequest request, @RequestParam(required = false, value = "page", defaultValue = "1") Integer page) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        request.setAttribute("model", resumeService.findAll(sessionUser.getId(), page));
        return "resume/list";
    }


    @GetMapping("/resume/{id}")
    public String resumeDetail(@PathVariable("id") Integer resumeId, HttpServletRequest request) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        ResumeResponse.DetailDTO detailDTO = resumeService.detailView(resumeId, sessionUser);
        request.setAttribute("model", detailDTO);
        System.out.println(detailDTO.getIsScrap());
        System.out.println(detailDTO.getLetter());
        return "resume/detail";
    }

    // TODO DTO처리 0 - 끝
    @GetMapping("/resume/save-form")
    public String resumeSaveForm(HttpServletRequest request) {
        ResumeResponse.TableDTO tableDTO = resumeService.viewJobAndStackList();
        request.setAttribute("model", tableDTO);
        return "resume/save-form";
    }

    @PostMapping("/resume/save")
    public String resumeSave(ResumeRequest.SaveDTO saveDTO) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        resumeService.save(saveDTO, sessionUser);
        return "redirect:/mypage/resume";
    }

    @GetMapping("/resume/{id}/update-form")
    public String resumeUpdateForm(@PathVariable("id") Integer id, HttpServletRequest request) {
        ResumeResponse.UpdateViewDTO updateViewDTO = resumeService.findById(id);
        request.setAttribute("model", updateViewDTO);
        return "resume/update-form";
    }

    @PostMapping("/resume/{id}/update")
    public String resumeUpdate(@PathVariable("id") Integer id, ResumeRequest.SaveDTO updateDTO) {
        resumeService.update(id, updateDTO);
        return "redirect:/mypage/resume";
    }

    @PostMapping("/resume/{id}/delete")
    public String resumeDelete(@PathVariable("id") Integer resumeId) {
        resumeService.delete(resumeId);
        return "redirect:/mypage/resume";
    }
}


