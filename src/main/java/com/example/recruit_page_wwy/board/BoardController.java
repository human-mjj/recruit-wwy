package com.example.recruit_page_wwy.board;

import com.example.recruit_page_wwy.user.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class BoardController {
    private final BoardService boardService;
    private final HttpSession session;

    @GetMapping("/board/save-form")
    public String boardSaveForm() {
        return "board/save-form";
    }

    @PostMapping("/board/save")
    public String boardSave(BoardRequest.SaveDTO saveDTO) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        saveDTO.setUser_id(sessionUser.getId());
        boardService.boardSave(saveDTO);
        return "redirect:/board";
    }

    @GetMapping("/board")
    public String boardList(HttpServletRequest request) {
        boardService.boardList();
        request.setAttribute("models", boardService.boardList());
        return "board/list";
    }

    @GetMapping("/board/{id}")
    public String boardDetail(@PathVariable("id") Integer id, HttpServletRequest request) {
        BoardResponse.DetailDTO detailDTO = boardService.boardDetail(id);
        request.setAttribute("models", detailDTO);
        return "board/detail";
    }


    @GetMapping("/board/1/update-form")
    public String boardUpdateForm() {
        return "board/update-form";
    }

    @PostMapping("/board/1/delete")
    public String deleteBoard() {
        return "redirect:/board";
    }

}
