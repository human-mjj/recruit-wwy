package com.example.recruit_page_wwy.reply;

import com.example.recruit_page_wwy._core.util.Resp;
import com.example.recruit_page_wwy.user.User;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class ReplyController {
    private final ReplyService replyService;
    private final HttpSession session;

    // TODO : 예외 추가
    @PostMapping("/reply/save")
    public ResponseEntity<?> replySave(@RequestBody ReplyRequest.SaveDTO saveDTO) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        ReplyResponse.DTO respDTO = replyService.replySave(saveDTO, sessionUser);
        return Resp.ok(respDTO);
    }

    // TODO : 예외 추가
    @PostMapping("/reply/{id}/delete")
    public String resumeDelete(@PathVariable("id") Integer id) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        int boardId = replyService.delete(id, sessionUser.getId());
        return "redirect:/board/" + boardId;
    }

}
