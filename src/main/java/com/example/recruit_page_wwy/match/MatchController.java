package com.example.recruit_page_wwy.match;

import com.example.recruit_page_wwy.user.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class MatchController {
    private final MatchService matchService;
    private final HttpSession session;

    @GetMapping("/match")
    public String matchList(HttpServletRequest request) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        request.setAttribute("models", matchService.matchEmployment(sessionUser));
        return "match/list";
    }

    @GetMapping("/match/com")
    public String matchComList(HttpServletRequest request) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        request.setAttribute("models", matchService.matchResume(sessionUser));
        return "match/com-list";
    }
}
