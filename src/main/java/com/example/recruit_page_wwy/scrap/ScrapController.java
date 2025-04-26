package com.example.recruit_page_wwy.scrap;

import com.example.recruit_page_wwy.user.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class ScrapController {
    private final ScrapService scrapService;
    private final HttpSession session;

    @GetMapping("/mypage/scrap")
    public String scrapUserList(HttpServletRequest requset) {
        User sessinUser = (User) session.getAttribute("sessionUser");
        List<ScrapRequest.UserScrapDTO> scrapList = scrapService.ScrapUserfind(sessinUser);
        requset.setAttribute("models", scrapList);

        return "scrap/user-scrap";
    }

    @GetMapping("/mypage/scrap/com")
    public String scrapComList(HttpServletRequest requset) {
        User sessinUser = (User) session.getAttribute("sessionUser");
        List<ScrapRequest.ComScrapDTO> scrapList = scrapService.ScrapComfind(sessinUser);
        requset.setAttribute("models", scrapList);

        return "scrap/com-scrap";
    }
}
