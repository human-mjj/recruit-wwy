package com.example.recruit_page_wwy.employment;

import com.example.recruit_page_wwy._core.util.Resp;
import com.example.recruit_page_wwy.user.User;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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


    @PostMapping("/s/api/employment")
    public ResponseEntity<?> employmentSave(@RequestBody EmploymentRequest.SaveDTO saveDTO) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        EmploymentResponse.DTO respDTO = employmentService.save(reqDTO, sessionUser);
        return Resp.ok(respDTO);
    }

    @GetMapping("/s/api/employment/{id}")
    public ResponseEntity<?> employmentUpdateForm(@PathVariable("id") int employmentId) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        EmploymentResponse.UpdateViewDTO respDTO = employmentService.showUpdateView(employmentId);
        return Resp.ok(respDTO);
    }

    @PutMapping("/s/api/employment/{id}")
    public ResponseEntity<?> updateEmployment(@PathVariable("id") int employmentId, @RequestBody EmploymentRequest.SaveDTO saveDTO) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        EmploymentResponse.DTO respDTO = employmentService.update(employmentId, saveDTO, sessionUser);
        return Resp.ok(respDTO);
    }

    @DeleteMapping("/s/api/employment/{id}")
    public ResponseEntity<?> deleteEmployment(@PathVariable("id") int employmentId) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        employmentService.delete(employmentId, sessionUser);
        return Resp.ok(null);
    }

}