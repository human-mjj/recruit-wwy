package com.example.recruit_page_wwy.scrap;

import com.example.recruit_page_wwy.user.User;
import com.example.recruit_page_wwy.user.UserResponse;
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
    public String scrapUserList(HttpServletRequest request) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        List<ScrapRequest.UserScrapDTO> scrapList = scrapService.scrapUserfind(sessionUser);
        request.setAttribute("models", scrapList);

        // 구직자로 로그인 시 이력서 nav / 기업으로 로그인 시 추천 nav
        if (sessionUser != null) {
            UserResponse.MyPageDTO myDTO = new UserResponse.MyPageDTO(sessionUser);
            request.setAttribute("comCheck", myDTO);
            System.out.println(myDTO.getIsCompanyUser());
        } else {
            request.setAttribute("comCheck", null); // 로그인 안 한 경우
        }


        return "scrap/user-scrap";
    }

    @GetMapping("/mypage/scrap/com")
    public String scrapComList(HttpServletRequest request) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        List<ScrapRequest.ComScrapDTO> scrapList = scrapService.scrapComfind(sessionUser);
        request.setAttribute("models", scrapList);

        // 구직자로 로그인 시 이력서 nav / 기업으로 로그인 시 추천 nav
        if (sessionUser != null) {
            UserResponse.MyPageDTO myDTO = new UserResponse.MyPageDTO(sessionUser);
            request.setAttribute("comCheck", myDTO);
            System.out.println(myDTO.getIsCompanyUser());
        } else {
            request.setAttribute("comCheck", null); // 로그인 안 한 경우
        }

        return "scrap/com-scrap";
    }

    // UserScrapSave
    @PostMapping("/api/user-scrap")
    @ResponseBody
    public Object userSaveScrap(@RequestBody ScrapRequest.userScrapSaveDTO reqDTO) {
        User sessionUser = (User) session.getAttribute("sessionUser");

        if (sessionUser == null) {
            throw new RuntimeException("sessionUser is null");
        }

        ScrapResponse.UserSaveDTO respDTO = scrapService.userScrapSave(reqDTO, sessionUser.getId());
        System.out.println(respDTO.getScrapId());

        return respDTO;
    }

    // UserScrapDelete
    @DeleteMapping("/api/user-scrap/{id}")
    @ResponseBody
    public Object userDeleteScrap(@PathVariable("id") Integer employmentId) {
        User sessionUser = (User) session.getAttribute("sessionUser");
//        if (sessionUser == null) throw new RuntimeException("인증이 필요합니다");

        ScrapResponse.UserDeleteDTO respDTO = scrapService.deleteUserScrap(employmentId);

        return respDTO;
    }

    // ComScrapSave
    @PostMapping("/api/com-scrap")
    @ResponseBody
    public Object comSaveScrap(@RequestBody ScrapRequest.comScrapSaveDTO reqDTO) {
        User sessionUser = (User) session.getAttribute("sessionUser");

        if (sessionUser == null) {
            throw new RuntimeException("sessionUser is null");
        }

        ScrapResponse.ComSaveDTO respDTO = scrapService.comScrapSave(reqDTO, sessionUser.getId());
        System.out.println(respDTO.getScrapId());

        return respDTO;
    }

    // ComScrapDelete
    @DeleteMapping("/api/com-scrap/{id}")
    @ResponseBody
    public Object comDeleteScrap(@PathVariable("id") Integer resumeId) {
        User sessionUser = (User) session.getAttribute("sessionUser");
//        if (sessionUser == null) throw new RuntimeException("인증이 필요합니다");

        ScrapResponse.ComDeleteDTO respDTO = scrapService.deleteComScrap(resumeId);

        return respDTO;
    }

}
