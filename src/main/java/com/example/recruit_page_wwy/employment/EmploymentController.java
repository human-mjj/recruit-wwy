package com.example.recruit_page_wwy.employment;

import com.example.recruit_page_wwy._core.error.ex.ExceptionApi401;
import com.example.recruit_page_wwy._core.util.Resp;
import com.example.recruit_page_wwy.user.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class EmploymentController {
    private final EmploymentService employmentService;
    private final HttpSession session;

    @GetMapping("/")
    public ResponseEntity<?> index() {
        User sessionUser = (User) session.getAttribute("sessionUser");
        EmploymentResponse.MainDTO respDTO = employmentService.viewEmployList(sessionUser);
        return Resp.ok(respDTO);
    }

    @GetMapping("/s/api/mypage/employment")
    public ResponseEntity<?> manageEmployment(@RequestParam(required = false, value = "page", defaultValue = "1") Integer page) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        EmploymentResponse.EmploymentDashboardDTO respDTO = employmentService.employmentList(sessionUser, page);
        return Resp.ok(respDTO);
    }

    @GetMapping("/api/employment")
    public ResponseEntity<?> employmentList(@RequestParam(required = false, value = "page", defaultValue = "1") Integer page,
                                 @RequestParam(required = false) String jobType,
                                 @RequestParam(required = false) String careerLevel,
                                 @RequestParam(defaultValue = "latest") String sort,
                                 @RequestParam(required = false) List<String> skills) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        EmploymentResponse.EmploymentPageDTO respDTO = employmentService.employmentAllList(sessionUser, jobType, careerLevel, skills, sort, page - 1);
        return Resp.ok(respDTO);
    }

    @GetMapping("/api/employment/{id}")
    public ResponseEntity<?> employmentDetail(@PathVariable("id") Integer id) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        EmploymentResponse.DetailDTO respDTO = employmentService.findEmploymentDetail(id, sessionUser);
        return Resp.ok(respDTO);
    }

    // TODO : 예외 추가
    @PostMapping("/s/api/employment/save")
    public ResponseEntity<?> employmentSave(@RequestBody EmploymentRequest.SaveDTO saveDTO) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        EmploymentResponse.DTO respDTO = employmentService.save(saveDTO, sessionUser);
        return Resp.ok(respDTO);
    }

    @GetMapping("/employment/{id}/update-form")
    public String employmentUpdateForm(@PathVariable("id") int employmentId, HttpServletRequest request) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        if (sessionUser == null || sessionUser.getRole() == 0) throw new ExceptionApi401("401 Unauthorized");
        EmploymentResponse.UpdateViewDTO updateViewDTO = employmentService.showUpdateView(employmentId);
        request.setAttribute("model", updateViewDTO);
        return "employment/update-form";
    }

    @PutMapping("/s/api/employment/{id}")
    public ResponseEntity<?> updateEmployment(@PathVariable("id") int employmentId, @RequestBody EmploymentRequest.SaveDTO saveDTO) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        System.out.println("sessionUser = " + sessionUser);
        if (sessionUser == null || sessionUser.getRole() == 0) throw new ExceptionApi401("401 Unauthorized");
        EmploymentResponse.DTO respDTO = employmentService.update(employmentId, saveDTO, sessionUser);
        return Resp.ok(respDTO);
    }

    @PostMapping("/s/api/employment/{id}/delete")
    public ResponseEntity<?> deleteEmployment(@PathVariable("id") int employmentId) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        if (sessionUser == null || sessionUser.getRole() == 0) throw new ExceptionApi401("401 Unauthorized");
        employmentService.delete(employmentId);
        return Resp.ok(null);
    }

}