package com.example.recruit_page_wwy.proposal;

import com.example.recruit_page_wwy.user.User;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class ProposalController {
    private final ProposalService proposalService;
    private final HttpSession session;

    @PostMapping("/resume/{id}/recommend")
    public String recommend(@PathVariable("id") int id, ProposalRequest.SaveDTO saveDTO) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        proposalService.recommend(id, saveDTO, sessionUser);
        return "redirect:/mypage/apply";
    }
}
