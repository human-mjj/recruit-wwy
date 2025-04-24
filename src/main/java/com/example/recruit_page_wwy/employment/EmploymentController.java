package com.example.recruit_page_wwy.employment;

import com.example.recruit_page_wwy.user.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class EmploymentController {
    private final EmploymentService employmentService;
    private final HttpSession session;

    @GetMapping("/")
    public String index(HttpSession session, HttpServletRequest request) {
        User sessionUser = (User) session.getAttribute("sessionUser");
//        request.setAttribute("sessionUser", sessionUser);
//        System.out.println(sessionUser);

        List<Employment> jobs = employmentService.viewEmployList();
        request.setAttribute("models", jobs);

        return "index";
    }

    @GetMapping("/mypage/employment")
    public String manageEmployment(HttpServletRequest request) {


        // 마이페이지에서 아직 세션 정보 불러와지지 않음. 유저id 임시로 4 줌
        //User sessionUser = (User) session.getAttribute("sessionUser");
        //request.setAttribute("models", employmentService.employmentList(sessionUser.getId()));

        // TODO
        // 세션 받아오는 코드로 변경 필요
        int testUserId = 4;
        request.setAttribute("models", employmentService.employmentList(testUserId));
        return "employment/dashboard";
    }

    // TODO
    // 필터, 페이징 구현 필요
    @GetMapping("/employment")
    public String employmentList(HttpServletRequest request) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        Integer userId = (sessionUser != null) ? sessionUser.getId() : null; // 로그인 안해도 접근할 수 있게
        request.setAttribute("models", employmentService.emplymentAllList(userId));
        return "employment/list";
    }
}
