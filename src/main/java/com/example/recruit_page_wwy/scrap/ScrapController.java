package com.example.recruit_page_wwy.scrap;

import com.example.recruit_page_wwy._core.error.ex.ExceptionApi401;
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

    // TODO : 예외 추가
    @GetMapping("/s/api/mypage/scrap")
    public String scrapUserList(HttpServletRequest request,
                                @RequestParam(required = false, value = "page", defaultValue = "1") Integer page) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        ScrapRequest.UserScrapPageDTO model = scrapService.scrapUserfind(sessionUser, page);
        request.setAttribute("model", model);

        return "scrap/user-scrap";
    }

    // TODO : 예외 추가
    @GetMapping("/s/api/mypage/scrap/com")
    public String scrapComList(HttpServletRequest request,
                               @RequestParam(required = false, value = "page", defaultValue = "1") Integer page) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        ScrapRequest.ComScrapPageDTO model = scrapService.scrapComfind(sessionUser, page);
        request.setAttribute("model", model);

        return "scrap/com-scrap";
    }

    @PostMapping("/s/api/scrap")
    @ResponseBody
    public Object saveScrap(@RequestBody ScrapRequest.SaveDTO reqDTO) {
        User sessionUser = (User) session.getAttribute("sessionUser");

        if (sessionUser == null) {
            throw new ExceptionApi401("로그인 해주세요");
        }

        ScrapResponse.SaveDTO respDTO = scrapService.save(reqDTO, sessionUser);
        System.out.println(respDTO.getScrapId());

        return respDTO;
    }

    @DeleteMapping("/s/api/scrap/{id}")
    @ResponseBody
    public Object deleteScrap(@PathVariable("id") Integer employmentId) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        if (sessionUser == null) throw new ExceptionApi401("인증이 필요합니다");

        ScrapResponse.DeleteDTO respDTO = scrapService.cancelScrap(employmentId);

        return respDTO;
    }
}
