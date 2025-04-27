package com.example.recruit_page_wwy;


import com.example.recruit_page_wwy.employment.EmploymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class HelloController {

    private final EmploymentRepository employmentRepository;
    
    @GetMapping("/employment/1/update-form")
    public String employmentUpdateForm() {
        return "employment/update-form";
    }

}
