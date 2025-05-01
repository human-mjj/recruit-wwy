package com.example.recruit_page_wwy.reply;

import com.example.recruit_page_wwy.user.User;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class ReplyController {
    private final ReplyService replyService;
    private final HttpSession session;

    @PostMapping("/reply/save")
    public String replySave(ReplyRequest.SaveDTO saveDTO) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        Reply replyPS = replyService.replySave(saveDTO, sessionUser);
        return "redirect:/board/" + saveDTO.getBoardId();
    }

    @PostMapping("/reply/{id}/delete")
    public String resumeDelete(@PathVariable("id") Integer id) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        int boardId = replyService.delete(id, sessionUser.getId());
        return "redirect:/board/" + boardId;
    }

}
