package com.example.recruit_page_wwy.board;


import com.example.recruit_page_wwy._core.error.ex.ExceptionApi403;
import com.example.recruit_page_wwy._core.error.ex.ExceptionApi404;
import com.example.recruit_page_wwy.reply.Reply;
import com.example.recruit_page_wwy.reply.ReplyRepository;
import com.example.recruit_page_wwy.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service

public class BoardService {
    private final BoardRepository boardRepository;
    private final ReplyRepository replyRepository;

    // TODO : save -> persist
    // TODO : 저장 후 DTO에 담아서 반환
    @Transactional
    public BoardResponse.DTO boardSave(BoardRequest.SaveDTO reqDTO, User sessionUser) {
        Board board = reqDTO.toEntity(sessionUser);
        Board boardPS = boardRepository.save(board);
        return new BoardResponse.DTO(boardPS);
    }

    public BoardResponse.ListDTO boardList(Integer page, User sessionUser, String keyword) {
        Long totalCount = boardRepository.totalCount(keyword);
        List<Board> boards = boardRepository.findAll(page, keyword);
        return new BoardResponse.ListDTO(boards, page, totalCount.intValue(), sessionUser, keyword);
    }

    public BoardResponse.DetailDTO boardDetail(Integer id, User sessionUser) {
        Board board = boardRepository.findById(id);

        List<BoardResponse.DetailDTO.ReplyDTO> replyList = new ArrayList<>();

        List<Reply> replys = replyRepository.findByBoardId(id);
        for (Reply reply : replys) {
            BoardResponse.DetailDTO.ReplyDTO replyDTO = new BoardResponse.DetailDTO.ReplyDTO(reply, sessionUser);
            replyList.add(replyDTO);
        }

        return new BoardResponse.DetailDTO(board, replyList, sessionUser);
    }

    @Transactional
    public BoardResponse.DTO boardUpdate(Integer id, BoardRequest.UpdateDTO reqDTO, User sessionUser) {
        Board boardPS = boardRepository.findById(id);
        if (boardPS == null) {
            throw new ExceptionApi404("자원을 찾을 수 없습니다");
        }

        if (sessionUser == null || sessionUser.getId() != boardPS.getUser().getId()) {
            throw new ExceptionApi403("권한이 없습니다");
        }

        boardPS.update(reqDTO);

        return new BoardResponse.DTO(boardPS);
    }

    @Transactional
    public void boardDelete(Integer id, User sessionUser) {
        Board boardPS = boardRepository.findById(id);
        if (boardPS == null) {
            throw new ExceptionApi404("자원을 찾을 수 없습니다");
        }

        if (sessionUser == null || sessionUser.getId() != boardPS.getUser().getId()) {
            throw new ExceptionApi403("권한이 없습니다");
        }
        boardRepository.delete(id);
    }

    public BoardResponse.UpdateViewDTO updateView(Integer id, User sessionUser) {
        Board boardPS = boardRepository.findById(id);
        return new BoardResponse.UpdateViewDTO(boardPS, sessionUser);
    }
}
