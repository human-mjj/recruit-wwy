package com.example.recruit_page_wwy.apply;

import com.example.recruit_page_wwy._core.util.Resp;
import com.example.recruit_page_wwy.user.User;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequiredArgsConstructor
@RestController
public class ApplyController {
    private final ApplyService applyService;
    private final HttpSession session;

    // TODO : 예외 처리
    @GetMapping("/s/api/mypage/apply")
    public ResponseEntity<?> applyList() {
        User sessionUser = (User) session.getAttribute("sessionUser");
        ApplyResponse.UserApplyListDTO respDTO = applyService.findUserApply(sessionUser);
        return Resp.ok(respDTO);
    }

    // TODO : 예외 처리
    @GetMapping("/s/api/mypage/com")
    public ResponseEntity<?> applyManageList() {
        User sessionUser = (User) session.getAttribute("sessionUser");
        ApplyResponse.ComApplyListDTO respDTO = applyService.findComApply(sessionUser);
        return Resp.ok(respDTO);
    }

    // TODO : 예외 처리
    // TODO : 인터셉터 만들어야함
    @PostMapping("/employment/{id}/apply")
    public ResponseEntity<?> apply(@PathVariable("id") int employmentId, @RequestParam("resumeId") Integer resumeId) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        ApplyResponse.DTO respDTO = applyService.apply(sessionUser, resumeId, employmentId);
        return Resp.ok(respDTO);
    }

    // TODO : DTO 반환에서 Body에 넣기
    // TODO : Resp 수정
    @PutMapping("/api/apply")
    public ResponseEntity<?> updateApplyProgress(@RequestBody Map<String, String> request) {
        Integer applyId = Integer.valueOf(request.get("applyId"));
        String progress = request.get("progress");

        ApplyResponse.DTO respDTO = applyService.updateProgress(applyId, progress); // 실제 처리 로직
        return Resp.ok(respDTO);
    }

}
