package com.example.recruit_page_wwy.apply;

import com.example.recruit_page_wwy._core.util.Resp;
import com.example.recruit_page_wwy.user.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequiredArgsConstructor
@Controller
public class ApplyController {
    private final ApplyService applyService;
    private final HttpSession session;

    // TODO : 예외 처리
    @GetMapping("/mypage/apply")
    public String applyList(HttpServletRequest request) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        ApplyResponse.UserApplyListDTO userApplyList = applyService.findUserApply(sessionUser);
        request.setAttribute("model", userApplyList);


        return "resume/apply-list";
    }

    // TODO : 예외 처리
    @GetMapping("/mypage/apply/com")
    public String applyManageList(HttpServletRequest request) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        ApplyResponse.ComApplyListDTO comApplyList = applyService.findComApply(sessionUser);
        request.setAttribute("model", comApplyList);

        return "resume/com-apply-list";
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
    @PostMapping("/api/apply")
    public @ResponseBody ResponseEntity<?> updateApplyProgress(@RequestBody Map<String, String> request) {
        Integer applyId = Integer.valueOf(request.get("applyId"));
        String progress = request.get("progress");

        ApplyResponse.DTO respDTO = applyService.updateProgress(applyId, progress); // 실제 처리 로직
        return Resp.ok(respDTO);
    }
}
