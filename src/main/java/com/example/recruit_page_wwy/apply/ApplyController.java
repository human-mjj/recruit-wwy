package com.example.recruit_page_wwy.apply;

import com.example.recruit_page_wwy.user.User;
import com.example.recruit_page_wwy.user.UserResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class ApplyController {
    private final ApplyService applyService;
    private final HttpSession session;

    @GetMapping("/mypage/apply")
    public String applyList(HttpServletRequest request) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        List<ApplyResponse.UserApplyDTO> userApplyList = applyService.findUserApply(sessionUser);
        request.setAttribute("models", userApplyList);

        // 구직자로 로그인 시 이력서 nav / 기업으로 로그인 시 추천 nav
        if (sessionUser != null) {
            UserResponse.MyPageDTO myDTO = new UserResponse.MyPageDTO(sessionUser);
            request.setAttribute("comCheck", myDTO);
            System.out.println(myDTO.getIsCompanyUser());
        } else {
            request.setAttribute("comCheck", null); // 로그인 안 한 경우
        }

        return "resume/apply-list";
    }

    @GetMapping("/mypage/apply/com")
    public String applyManageList(HttpServletRequest request) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        List<ApplyResponse.ComApplyDTO> comApplyList = applyService.findComApply(sessionUser);
        System.out.println(sessionUser.getId());

        request.setAttribute("models", comApplyList);

        // 구직자로 로그인 시 이력서 nav / 기업으로 로그인 시 추천 nav
        if (sessionUser != null) {
            UserResponse.MyPageDTO myDTO = new UserResponse.MyPageDTO(sessionUser);
            request.setAttribute("comCheck", myDTO);
            System.out.println(myDTO.getIsCompanyUser());
        } else {
            request.setAttribute("comCheck", null); // 로그인 안 한 경우
        }

        return "resume/com-apply-list";
    }

    // TODO : 인터셉터 만들어야함
    @PostMapping("/employment/{id}/apply")
    public String apply(@PathVariable("id") int employmentId, @RequestParam("resumeId") Integer resumeId) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        applyService.apply(sessionUser, resumeId, employmentId);
        return "redirect:/mypage/apply";
    }
}
