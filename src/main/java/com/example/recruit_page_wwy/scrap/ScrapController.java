package com.example.recruit_page_wwy.scrap;

import com.example.recruit_page_wwy.user.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Controller
public class ScrapController {
    private final ScrapService scrapService;
    private final HttpSession session;

    @GetMapping("/mypage/scrap")
    public String scrapUserList(HttpServletRequest request,
                                @RequestParam(required = false, value = "page", defaultValue = "1") Integer page) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        ScrapRequest.UserScrapPageDTO model = scrapService.scrapUserfind(sessionUser, page);
        request.setAttribute("model", model);

        return "scrap/user-scrap";
    }

    @GetMapping("/mypage/scrap/com")
    public String scrapComList(HttpServletRequest request,
                               @RequestParam(required = false, value = "page", defaultValue = "1") Integer page) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        ScrapRequest.ComScrapPageDTO model = scrapService.scrapComfind(sessionUser, page);
        request.setAttribute("models", model);

        return "scrap/com-scrap";
    }

    @PostMapping("/api/scrap")
    @ResponseBody
    public Object saveScrap(@RequestBody ScrapRequest.SaveDTO reqDTO) {
        User sessionUser = (User) session.getAttribute("sessionUser");

        if (sessionUser == null) {
            throw new RuntimeException("로그인 해주세요");
        }

        ScrapResponse.SaveDTO respDTO = scrapService.save(reqDTO, sessionUser);
        System.out.println(respDTO.getScrapId());

        return respDTO;
    }

    @DeleteMapping("/api/scrap/{id}")
    @ResponseBody
    public Object deleteScrap(@PathVariable("id") Integer employmentId) {
        User sessionUser = (User) session.getAttribute("sessionUser");
//        if (sessionUser == null) throw new RuntimeException("인증이 필요합니다");

        ScrapResponse.DeleteDTO respDTO = scrapService.cancelScrap(employmentId);

        return respDTO;
    }
}
