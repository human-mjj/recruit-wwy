package com.example.recruit_page_wwy.board;

import com.example.recruit_page_wwy._core.error.ex.ExceptionApi401;
import com.example.recruit_page_wwy._core.util.Resp;
import com.example.recruit_page_wwy.user.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Controller
public class BoardController {
    private final BoardService boardService;
    private final HttpSession session;

    @PostMapping("/board/save")
    public ResponseEntity<?> boardSave(@RequestBody BoardRequest.SaveDTO reqDTO) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        BoardResponse.DTO respDTO = boardService.boardSave(reqDTO, sessionUser);
        System.out.println(respDTO);
        return Resp.ok(respDTO);
    }

    @PostMapping("/board")
    public ResponseEntity<?> boardList(@RequestBody BoardRequest.SearchRequestDTO reqDTO) {
        User sessionUser = (User) session.getAttribute("sessionUser");

        int page = reqDTO.getPage() != null && reqDTO.getPage() > 0 ? reqDTO.getPage() - 1 : 0;
        String keyword = reqDTO.getKeyword() != null ? reqDTO.getKeyword() : "";

        BoardResponse.ListDTO respDTO = boardService.boardList(page, sessionUser, keyword);
        return Resp.ok(respDTO);
    }

    @GetMapping("/board/{id}")
    public ResponseEntity<?> boardDetail(@PathVariable("id") Integer id) {
        User sessionUser = (User) session.getAttribute("sessionUser");

        BoardResponse.DetailDTO respDTO = boardService.boardDetail(id, sessionUser);

        return Resp.ok(respDTO);
    }

    @PutMapping("/board/{id}/update")
    public ResponseEntity<?> boardUpdate(@PathVariable("id") Integer id, @RequestBody BoardRequest.UpdateDTO reqDTO, HttpSession session) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        Integer sessionUserId = sessionUser != null ? sessionUser.getId() : null;

        BoardResponse.DTO respDTO = boardService.boardUpdate(id, reqDTO, sessionUserId);
        System.out.println(reqDTO);
        return Resp.ok(respDTO);
    }

    @DeleteMapping("/board/{id}/delete")
    public ResponseEntity<?> deleteBoard(@PathVariable("id") Integer id, HttpSession session) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        Integer sessionUserId = sessionUser != null ? sessionUser.getId() : null;
        boardService.boardDelete(id, sessionUserId);
        return Resp.ok(null);
    }

    @GetMapping("/board/{id}/update-form")
    public String boardUpdateForm(@PathVariable("id") Integer id, HttpServletRequest request) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        if (sessionUser == null) throw new ExceptionApi401("로그인을 해 주세요.");
        BoardResponse.UpdateViewDTO respDTO = boardService.updateView(id, sessionUser);
        request.setAttribute("model", respDTO);
        return "board/update-form";
    }

}
