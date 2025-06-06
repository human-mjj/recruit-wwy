package com.example.recruit_page_wwy.reply;

import com.example.recruit_page_wwy._core.util.Resp;
import com.example.recruit_page_wwy.user.User;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class ReplyController {
    private final ReplyService replyService;
    private final HttpSession session;

    // TODO : 예외 추가
    @PostMapping("/s/api/reply")
    public ResponseEntity<?> replySave(@RequestBody @Valid ReplyRequest.SaveDTO reqDTO) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        ReplyResponse.DTO respDTO = replyService.replySave(reqDTO, sessionUser);
        return Resp.ok(respDTO);
    }

    // TODO : 예외 추가
    @DeleteMapping("/s/api/reply/{id}")
    public ResponseEntity<?> replyDelete(@PathVariable("id") Integer id) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        replyService.delete(id, sessionUser);
        return Resp.ok(null);
    }

}
