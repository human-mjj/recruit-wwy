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

    @PostMapping("/s/api/apply")
    public ResponseEntity<?> apply(@RequestBody ApplyRequest.SaveDTO reqDTO) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        ApplyResponse.DTO respDTO = applyService.apply(sessionUser, reqDTO);
        return Resp.ok(respDTO);
    }

    @PutMapping("/s/api/apply")
    public ResponseEntity<?> updateApplyProgress(@RequestBody Map<String, String> request) {
        Integer applyId = Integer.valueOf(request.get("applyId"));
        String progress = request.get("progress");

        ApplyResponse.DTO respDTO = applyService.updateProgress(applyId, progress); // 실제 처리 로직
        return Resp.ok(respDTO);
    }

    @GetMapping("/s/api/apply")
    public ResponseEntity<?> applyList() {
        User sessionUser = (User) session.getAttribute("sessionUser");
        ApplyResponse.UserApplyListDTO respDTO = applyService.findUserApply(sessionUser);
        return Resp.ok(respDTO);
    }

    @GetMapping("/s/api/apply/com")
    public ResponseEntity<?> applyManageList() {
        User sessionUser = (User) session.getAttribute("sessionUser");
        ApplyResponse.ComApplyListDTO respDTO = applyService.findComApply(sessionUser);
        return Resp.ok(respDTO);
    }
}
