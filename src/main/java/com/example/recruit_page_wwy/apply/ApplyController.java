package com.example.recruit_page_wwy.apply;

import com.example.recruit_page_wwy._core.util.Resp;
import com.example.recruit_page_wwy.user.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequiredArgsConstructor
@Controller
public class ApplyController {
    private final ApplyService applyService;
    private final HttpSession session;

    @GetMapping("/mypage/apply")
    public String applyList(HttpServletRequest request) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        ApplyResponse.UserApplyListDTO userApplyList = applyService.findUserApply(sessionUser);
        request.setAttribute("model", userApplyList);


        return "resume/apply-list";
    }

    @GetMapping("/mypage/apply/com")
    public String applyManageList(HttpServletRequest request) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        ApplyResponse.ComApplyListDTO comApplyList = applyService.findComApply(sessionUser);
        request.setAttribute("model", comApplyList);

        return "resume/com-apply-list";
    }

    // TODO : 인터셉터 만들어야함
    @PostMapping("/employment/{id}/apply")
    public String apply(@PathVariable("id") int employmentId, @RequestParam("resumeId") Integer resumeId) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        applyService.apply(sessionUser, resumeId, employmentId);
        return "redirect:/mypage/apply";
    }

    @PostMapping("/api/apply")
    @ResponseBody
    public Resp<?> updateApplyProgress(@RequestBody Map<String, String> request) {
        Integer applyId = Integer.valueOf(request.get("applyId"));
        String progress = request.get("progress");

        applyService.updateProgress(applyId, progress); // 실제 처리 로직
        return Resp.ok(Map.of("status", "success"));
    }
}
