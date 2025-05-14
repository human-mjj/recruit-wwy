package com.example.recruit_page_wwy.match;

import com.example.recruit_page_wwy._core.util.Resp;
import com.example.recruit_page_wwy.user.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class MatchController {
    private final MatchService matchService;
    private final HttpSession session;

    // TODO : 예외 추가
    @GetMapping("/match")
    public ResponseEntity<?> matchList(HttpServletRequest request) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        MatchResponse.MatchDTO respDTO = matchService.matchEmployment(sessionUser);
        return Resp.ok(respDTO);
    }

    // TODO : 예외 추가
    @GetMapping("/match/com")
    public ResponseEntity<?> matchComList(HttpServletRequest request) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        MatchResponse.ResumeListDTO respDTO = matchService.matchResume(sessionUser);
        return Resp.ok(respDTO);
    }
}
