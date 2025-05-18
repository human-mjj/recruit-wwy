package com.example.recruit_page_wwy.scrap;

import com.example.recruit_page_wwy._core.error.ex.ExceptionApi401;
import com.example.recruit_page_wwy._core.util.Resp;
import com.example.recruit_page_wwy.user.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class ScrapController {
    private final ScrapService scrapService;
    private final HttpSession session;

    // TODO : 예외 추가
    @GetMapping("/s/api/mypage/scrap")
    public ResponseEntity<?> scrapUserList(HttpServletRequest request,
                                           @RequestBody ScrapRequest.PageDTO pageDTO) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        ScrapRequest.UserScrapPageDTO respDTO = scrapService.scrapUserfind(sessionUser, pageDTO.getPage());

        return Resp.ok(respDTO);
    }

    // TODO : 예외 추가
    @GetMapping("/s/api/mypage/scrap/com")
    public ResponseEntity<?> scrapComList(HttpServletRequest request,
                                          @RequestBody ScrapRequest.PageDTO pageDTO) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        ScrapRequest.ComScrapPageDTO respDTO = scrapService.scrapComfind(sessionUser, pageDTO.getPage());

        return Resp.ok(respDTO);
    }

    @PostMapping("/s/api/scrap")
    @ResponseBody
    public ResponseEntity<?> saveScrap(@RequestBody ScrapRequest.SaveDTO reqDTO) {
        User sessionUser = (User) session.getAttribute("sessionUser");

        if (sessionUser == null) {
            throw new ExceptionApi401("로그인 해주세요");
        }

        ScrapResponse.SaveDTO respDTO = scrapService.save(reqDTO, sessionUser);
        System.out.println(respDTO.getScrapId());

        return Resp.ok(respDTO);
    }

    @DeleteMapping("/s/api/scrap/{id}")
    @ResponseBody
    public ResponseEntity<?> deleteScrap(@PathVariable("id") Integer employmentId) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        if (sessionUser == null) throw new ExceptionApi401("인증이 필요합니다");

        ScrapResponse.DeleteDTO respDTO = scrapService.cancelScrap(employmentId);

        return Resp.ok(respDTO);
    }
}
