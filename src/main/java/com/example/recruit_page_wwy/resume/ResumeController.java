package com.example.recruit_page_wwy.resume;

import com.example.recruit_page_wwy._core.util.Resp;
import com.example.recruit_page_wwy.user.User;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class ResumeController {
    private final ResumeService resumeService;
    private final HttpSession session;

    // TODO : 예외 추가
    @GetMapping("/s/api/mypage/resume")
    public ResponseEntity<?> resumeList(@RequestParam(required = false, value = "page", defaultValue = "1") Integer page) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        ResumeResponse.MainDTO respDTO = resumeService.findAll(sessionUser.getId(), page);
        return Resp.ok(respDTO);
    }

    // TODO : 예외 추가
    @GetMapping("/api/resume/{id}")
    public ResponseEntity<?> resumeDetail(@PathVariable("id") Integer resumeId) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        ResumeResponse.DetailDTO respDTO = resumeService.detailView(resumeId, sessionUser);
        return Resp.ok(respDTO);
    }

    // TODO : 예외 추가
    @GetMapping("/s/api/resume")
    public ResponseEntity<?> resumeSaveForm() {
        User sessionUser = (User) session.getAttribute("sessionUser");
        ResumeResponse.TableDTO respDTO = resumeService.viewJobAndStackList();
        return Resp.ok(respDTO);
    }

    // TODO : 예외 추가
    @PostMapping("/s/api/resume")
    public ResponseEntity<?> resumeSave(@RequestBody ResumeRequest.SaveDTO saveDTO) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        ResumeResponse.DTO respDTO = resumeService.save(saveDTO, sessionUser);
        return Resp.ok(respDTO);
    }

    // TODO : 예외 추가
    @GetMapping("/s/api/resume/{id}")
    public ResponseEntity<?> resumeUpdateForm(@PathVariable("id") Integer id) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        ResumeResponse.UpdateViewDTO respDTO = resumeService.findById(id);
        return Resp.ok(respDTO);
    }

    // TODO : 예외 추가
    @PutMapping("/s/api/resume/{id}")
    public ResponseEntity<?> resumeUpdate(@PathVariable("id") Integer id, @RequestBody ResumeRequest.SaveDTO updateDTO) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        ResumeResponse.DTO respDTO = resumeService.update(id, updateDTO, sessionUser);
        return Resp.ok(respDTO);
    }

    // TODO : 예외 추가
    @DeleteMapping("/s/api/resume/{id}")
    public ResponseEntity<?> resumeDelete(@PathVariable("id") Integer resumeId) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        resumeService.delete(resumeId, sessionUser);
        return Resp.ok(null);
    }
}


