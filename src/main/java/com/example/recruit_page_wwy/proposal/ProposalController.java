package com.example.recruit_page_wwy.proposal;

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
public class ProposalController {
    private final ProposalService proposalService;
    private final HttpSession session;

    // TODO : 예외 추가
    @PostMapping("/s/api/recommend")
    public ResponseEntity<?> recommend(@PathVariable("id") int id, @RequestBody ProposalRequest.SaveDTO saveDTO) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        ProposalResponse.DTO respDTO = proposalService.recommend(id, saveDTO, sessionUser);
        return Resp.ok(respDTO);
    }
}
