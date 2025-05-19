package com.example.recruit_page_wwy.resume;

import com.example.recruit_page_wwy._core.util.Resp;
import com.example.recruit_page_wwy.user.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Controller
public class ResumeController {
    private final ResumeService resumeService;
    private final HttpSession session;

    // TODO : 예외 추가
    @GetMapping("/mypage/resume")
    public ResponseEntity<?> resumeList(@RequestBody ResumeRequest.PageRequestDTO reqDTO, HttpSession session) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        Integer sessionUserId = sessionUser != null ? sessionUser.getId() : null;
        int page = reqDTO.getPage() != null && reqDTO.getPage() > 0 ? reqDTO.getPage() - 1 : 0;
        ResumeResponse.MainDTO mainDTO = resumeService.findAll(sessionUserId, page);
        return Resp.ok(mainDTO);
    }

    // TODO : 예외 추가
    @GetMapping("/resume/{id}")
    public ResponseEntity<?> resumeDetail(@PathVariable("id") Integer resumeId) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        ResumeResponse.DetailDTO respDTO = resumeService.detailView(resumeId, sessionUser);
        return Resp.ok(respDTO);
    }


    // TODO : 예외 추가
    @PostMapping("/resume/save")
    public ResponseEntity<?> resumeSave(@RequestBody ResumeRequest.SaveDTO reqDTO) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        ResumeResponse.DTO respDTO = resumeService.save(reqDTO, sessionUser);
        return Resp.ok(respDTO);
    }

    // TODO : 예외 추가
    @PutMapping("/resume/{id}/update")
    public ResponseEntity<?> resumeUpdate(@PathVariable("id") Integer id, @RequestBody ResumeRequest.SaveDTO reqDTO) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        ResumeResponse.DTO respDTO = resumeService.update(id, reqDTO, sessionUser);
        return Resp.ok(respDTO);
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
    @DeleteMapping("/resume/{id}/delete")
    public ResponseEntity resumeDelete(@PathVariable("id") Integer resumeId) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        resumeService.delete(resumeId, sessionUser);
        return Resp.ok(null);
    }
}


