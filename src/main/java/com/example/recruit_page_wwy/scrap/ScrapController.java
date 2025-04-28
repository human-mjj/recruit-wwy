package com.example.recruit_page_wwy.scrap;

import com.example.recruit_page_wwy.user.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class ScrapController {
    private final ScrapService scrapService;
    private final HttpSession session;

    @GetMapping("/mypage/scrap")
    public String scrapUserList(HttpServletRequest requset) {
        User sessinUser = (User) session.getAttribute("sessionUser");
        List<ScrapRequest.UserScrapDTO> scrapList = scrapService.scrapUserfind(sessinUser);
        requset.setAttribute("models", scrapList);

        return "scrap/user-scrap";
    }

    @GetMapping("/mypage/scrap/com")
    public String scrapComList(HttpServletRequest requset) {
        User sessinUser = (User) session.getAttribute("sessionUser");
        List<ScrapRequest.ComScrapDTO> scrapList = scrapService.scrapComfind(sessinUser);
        requset.setAttribute("models", scrapList);

        return "scrap/com-scrap";
    }

    @PostMapping("/api/scrap")
    @ResponseBody
    public Object saveScrap(@RequestBody ScrapRequest.SaveDTO reqDTO) {
        User sessionUser = (User) session.getAttribute("sessionUser");

        if (sessionUser == null) {
            throw new RuntimeException("sessionUser is null");
        }

        ScrapResponse.SaveDTO respDTO = scrapService.save(reqDTO, sessionUser.getId());
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
