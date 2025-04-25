package com.example.recruit_page_wwy.apply;

import com.example.recruit_page_wwy.user.User;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class ApplyController {
    private final ApplyService applyService;
    private final HttpSession session;

    @PostMapping("/employment/{id}/apply")
    public String apply(@PathVariable("id") int employmentId, ApplyRequest.SaveDTO saveDTO) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        applyService.apply(sessionUser, saveDTO, employmentId);
        return "redirect:/mypage/apply";
    }
}
