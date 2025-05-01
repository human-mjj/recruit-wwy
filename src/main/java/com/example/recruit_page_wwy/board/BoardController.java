package com.example.recruit_page_wwy.board;

import com.example.recruit_page_wwy._core.error.ex.Exception401;
import com.example.recruit_page_wwy.user.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    public String boardList(HttpServletRequest request,
                            @RequestParam(required = false, value = "page", defaultValue = "1") Integer page) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        request.setAttribute("model", boardService.boardList(page - 1, sessionUser));
        System.out.println(boardService.boardList(page - 1, sessionUser).getIsCompanyUser());
        return "board/list";
    }

    @GetMapping("/board/{id}")
    public String boardDetail(@PathVariable("id") Integer id, HttpServletRequest request) {
        User sessionUser = (User) session.getAttribute("sessionUser");

        BoardResponse.DetailDTO detailDTO = boardService.boardDetail(id, sessionUser);
        request.setAttribute("model", detailDTO);

        return "board/detail";
    }


    @GetMapping("/board/{id}/update-form")
    public String boardUpdateForm(@PathVariable("id") Integer id, HttpServletRequest request) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        if (sessionUser == null) throw new Exception401("로그인을 해 주세요.");
        BoardResponse.UpdateViewDTO respDTO = boardService.updateView(id, sessionUser);
        request.setAttribute("model", respDTO);
        return "board/update-form";
    }

    @PostMapping("/board/{id}/update")
    public String boardUpdate(@PathVariable("id") Integer id, BoardRequest.UpdateDTO updateDTO) {
        boardService.boardUpdate(id, updateDTO);
        return "redirect:/board";
    }

    @PostMapping("/board/{id}/delete")
    public String deleteBoard(@PathVariable("id") Integer id) {
        boardService.boardDelete(id);
        return "redirect:/board";
    }

}
