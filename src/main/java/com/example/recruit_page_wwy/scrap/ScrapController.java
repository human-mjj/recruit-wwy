package com.example.recruit_page_wwy.scrap;

import com.example.recruit_page_wwy._core.util.Resp;
import com.example.recruit_page_wwy.user.User;
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
    public ResponseEntity<?> scrapUserList(@RequestParam(required = false, value = "page", defaultValue = "1") Integer page) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        ScrapRequest.UserScrapPageDTO respDTO = scrapService.scrapUserfind(sessionUser, page);

        return Resp.ok(respDTO);
    }

    // TODO : 예외 추가
    @GetMapping("/s/api/mypage/scrap/com")
    public ResponseEntity<?> scrapComList(@RequestParam(required = false, value = "page", defaultValue = "1") Integer page) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        ScrapRequest.ComScrapPageDTO respDTO = scrapService.scrapComfind(sessionUser, page);
        return Resp.ok(respDTO);
    }

    @PostMapping("/s/api/scrap")
    public ResponseEntity<?> saveScrap(@RequestBody ScrapRequest.SaveDTO reqDTO) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        ScrapResponse.SaveDTO respDTO = scrapService.save(reqDTO, sessionUser);
        return Resp.ok(respDTO);
    }

    @DeleteMapping("/s/api/scrap/{id}")
    public ResponseEntity<?> deleteScrap(@PathVariable("id") Integer id) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        scrapService.cancelScrap(id, sessionUser);
        return Resp.ok(null);
    }
}
