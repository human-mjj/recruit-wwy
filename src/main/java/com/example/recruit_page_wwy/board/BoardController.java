package com.example.recruit_page_wwy.board;

import com.example.recruit_page_wwy._core.util.Resp;
import com.example.recruit_page_wwy.user.User;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class BoardController {
    private final BoardService boardService;
    private final HttpSession session;

    @PostMapping("/s/api/board")
    public ResponseEntity<?> boardSave(@RequestBody @Valid BoardRequest.SaveDTO reqDTO, Errors errors) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        BoardResponse.DTO respDTO = boardService.boardSave(reqDTO, sessionUser);
        System.out.println(respDTO);
        return Resp.ok(respDTO);
    }

    @GetMapping("/api/board")
    public ResponseEntity<?> boardList(@RequestParam(required = false, value = "page", defaultValue = "0") Integer page,
                                       @RequestParam(required = false, value = "keyword", defaultValue = "") String keyword) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        BoardResponse.ListDTO respDTO = boardService.boardList(page, sessionUser, keyword);
        return Resp.ok(respDTO);
    }

    @GetMapping("/api/board/{id}")
    public ResponseEntity<?> boardDetail(@PathVariable("id") Integer id) {
        User sessionUser = (User) session.getAttribute("sessionUser");

        BoardResponse.DetailDTO respDTO = boardService.boardDetail(id, sessionUser);

        return Resp.ok(respDTO);
    }

    @PutMapping("/s/api/board/{id}")
    public ResponseEntity<?> boardUpdate(@PathVariable("id") Integer id, @RequestBody @Valid BoardRequest.UpdateDTO reqDTO, Errors errors) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        BoardResponse.DTO respDTO = boardService.boardUpdate(id, reqDTO, sessionUser);
        return Resp.ok(respDTO);
    }

    @DeleteMapping("/s/api/board/{id}")
    public ResponseEntity<?> deleteBoard(@PathVariable("id") Integer id) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        boardService.boardDelete(id, sessionUser);
        return Resp.ok(null);
    }

    @GetMapping("/s/api/board/{id}")
    public ResponseEntity<?> boardUpdateForm(@PathVariable("id") Integer id) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        BoardResponse.UpdateViewDTO respDTO = boardService.updateView(id, sessionUser);
        return Resp.ok(respDTO);
    }

}
