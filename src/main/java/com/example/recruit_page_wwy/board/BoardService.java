package com.example.recruit_page_wwy.board;


import com.example.recruit_page_wwy.reply.Reply;
import com.example.recruit_page_wwy.reply.ReplyRepository;
import com.example.recruit_page_wwy.user.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
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

    @PersistenceContext
    private EntityManager em;

    // TODO : save -> persist (later)
    @Transactional
    public void boardSave(BoardRequest.SaveDTO saveDTO) {
        boardRepository.save(saveDTO.getUser_id(), saveDTO.getTitle(), saveDTO.getContent());
    }

    public BoardResponse.ListDTO boardList(Integer page, User sessionUser) {
        Long totalCount = boardRepository.totalCount();
        List<Board> boards = boardRepository.findAll(page);
        return new BoardResponse.ListDTO(boards, page, totalCount.intValue(), sessionUser);
    }

    public BoardResponse.DetailDTO boardDetail(Integer id, User sessionUser) {
        Board board = boardRepository.findById(id);

        List<BoardResponse.DetailDTO.ReplyDTO> replyList = new ArrayList<>();

        List<Reply> replys = replyRepository.findByBoardId(id);
        for (Reply reply : replys) {
            BoardResponse.DetailDTO.ReplyDTO replyDTO = new BoardResponse.DetailDTO.ReplyDTO(reply);
            replyList.add(replyDTO);
        }

        return new BoardResponse.DetailDTO(board, replyList, sessionUser);
    }

    // TODO : update -> dirty checking (later)
    @Transactional
    public void boardUpdate(Integer id, BoardRequest.UpdateDTO updateDTO) {
        boardRepository.boardUpdate(id, updateDTO.getTitle(), updateDTO.getContent());
    }

    @Transactional
    public void boardDelete(Integer id) {
        boardRepository.delete(id);
    }
}
