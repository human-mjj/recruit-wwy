package com.example.recruit_page_wwy.match;

import com.example.recruit_page_wwy.user.User;
import com.example.recruit_page_wwy.user.UserResponse;
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
        request.setAttribute("model", matchService.matchEmployment(sessionUser));
        return "match/list";
    }

    @GetMapping("/match/com")
    public String matchComList(HttpServletRequest request) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        request.setAttribute("models", matchService.matchResume(sessionUser));

        // 구직자로 로그인 시 이력서 nav / 기업으로 로그인 시 추천 nav
        if (sessionUser != null) {
            UserResponse.MyPageDTO myDTO = new UserResponse.MyPageDTO(sessionUser);
            request.setAttribute("comCheck", myDTO);
            System.out.println(myDTO.getIsCompanyUser());
        } else {
            request.setAttribute("comCheck", null); // 로그인 안 한 경우
        }

        return "match/com-list";
    }
}
