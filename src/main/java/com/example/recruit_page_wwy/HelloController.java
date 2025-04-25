package com.example.recruit_page_wwy;


import com.example.recruit_page_wwy.employment.EmploymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class HelloController {

    private final EmploymentRepository employmentRepository;

    @GetMapping("/board/1")
    public String boardDetail() {
        return "board/detail";
    }

    @GetMapping("/board")
    public String boardList() {
        return "board/list";
    }

    @GetMapping("/board/save-form")
    public String boardSaveForm() {
        return "board/save-form";
    }

    @GetMapping("/board/1/update-form")
    public String boardUpdateForm() {
        return "board/update-form";
    }

    @PostMapping("/board/1/delete")
    public String deleteBoard() {
        return "redirect:/board";
    }

    @GetMapping("/employment/save-form")
    public String employmentSaveForm() {
        return "employment/save-form";
    }

    @GetMapping("/employment/1/update-form")
    public String employmentUpdateForm() {
        return "employment/update-form";
    }

}
