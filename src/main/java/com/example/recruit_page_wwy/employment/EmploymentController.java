package com.example.recruit_page_wwy.employment;

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
    public String manageEmployment(HttpServletRequest request,
                                   @RequestParam(required = false, value = "page", defaultValue = "1") Integer page) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        if (sessionUser == null) throw new RuntimeException("401 Unauthorized");
        EmploymentResponse.EmploymentDashboardDTO model = employmentService.employmentList(sessionUser, page);
        request.setAttribute("model", model);
        return "employment/dashboard";
    }

    @GetMapping("/employment")
    public String employmentList(HttpServletRequest request,
                                 @RequestParam(required = false, value = "page", defaultValue = "1") Integer page,
                                 @RequestParam(required = false) String jobType,
                                 @RequestParam(required = false) String careerLevel,
                                 @RequestParam(defaultValue = "latest") String sort,
                                 @RequestParam(required = false) List<String> skills) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        EmploymentResponse.EmploymentPageDTO model = employmentService.employmentAllList(sessionUser, jobType, careerLevel, skills, sort, page);
        request.setAttribute("model", model);

        // 유저일 경우에만 스크랩 버튼 보이게 함 (로그인을 안해도 스크랩 버튼 보임)
        if (sessionUser != null) {
            UserResponse.MyPageDTO myDTO = new UserResponse.MyPageDTO(sessionUser);
            request.setAttribute("ComCheck", myDTO);
        } else {
            request.setAttribute("ComCheck", null); // 로그인 안 한 경우
        }

        return "employment/list";
    }

    @GetMapping("/employment/{id}")
    public String employmentDetail(@PathVariable("id") Integer id, HttpServletRequest request) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        EmploymentResponse.DetailDTO detailDTO = employmentService.findEmploymentDetail(id, sessionUser);
        request.setAttribute("models", detailDTO);
        System.out.println(detailDTO.getId());


        return "employment/detail";
    }

    @PostMapping("/employment/save")
    public String employmentSave(EmploymentRequest.SaveDTO saveDTO) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        employmentService.save(saveDTO, sessionUser);
        return "redirect:/mypage/employment";
    }

    @GetMapping("/employment/save-form")
    public String employmentSaveForm(HttpServletRequest request) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        if (sessionUser == null || sessionUser.getRole() == 0) throw new RuntimeException("401 Unauthorized");
        EmploymentResponse.TableDTO tableDTO = employmentService.viewJobAndStackList();
        request.setAttribute("model", tableDTO);
        return "employment/save-form";
    }

    @GetMapping("/employment/{id}/update-form")
    public String employmentUpdateForm(@PathVariable("id") int employmentId, HttpServletRequest request) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        if (sessionUser == null || sessionUser.getRole() == 0) throw new RuntimeException("401 Unauthorized");
        EmploymentResponse.UpdateViewDTO updateViewDTO = employmentService.showUpdateView(employmentId);
        request.setAttribute("model", updateViewDTO);
        return "employment/update-form";
    }

    @PostMapping("/employment/{id}/update")
    public String updateEmployment(@PathVariable("id") int employmentId, EmploymentRequest.SaveDTO saveDTO) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        if (sessionUser == null || sessionUser.getRole() == 0) throw new RuntimeException("401 Unauthorized");
        employmentService.update(employmentId, saveDTO);
        return "redirect:/employment/" + employmentId;
    }

    @PostMapping("/employment/{id}/delete")
    public String deleteEmployment(@PathVariable("id") int employmentId) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        if (sessionUser == null || sessionUser.getRole() == 0) throw new RuntimeException("401 Unauthorized");
        employmentService.delete(employmentId);
        return "redirect:/mypage/employment";
    }

}