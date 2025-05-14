package com.example.recruit_page_wwy.reply;


import com.example.recruit_page_wwy._core.error.ex.ExceptionApi400;
import com.example.recruit_page_wwy._core.error.ex.ExceptionApi404;
import com.example.recruit_page_wwy.board.Board;
import com.example.recruit_page_wwy.board.BoardRepository;
import com.example.recruit_page_wwy.user.User;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class ReplyService {
    private final ReplyRepository replyRepository;
    private final BoardRepository boardRepository;

    // TODO : Reply가 아니라 DTO에 담아서 반환
    @Transactional
    public ReplyRequest.DTO replySave(ReplyRequest.SaveDTO saveDTO, User sessionUser) {
        if (saveDTO.getContent().isBlank()) throw new ExceptionApi400("댓글 내용을 입력하세요.");
        Board board = boardRepository.findById(saveDTO.getBoardId());
        if (board == null) {
            throw new ExceptionApi404("게시글을 찾을 수 없습니다. id = " + saveDTO.getBoardId());
        }

        Reply reply = saveDTO.toEntity(sessionUser);

        Reply replyPS = replyRepository.replySave(reply);
        return new ReplyRequest.DTO(replyPS, board, sessionUser);
    }

    // TODO : 예외 처리
    @Transactional
    public Integer delete(Integer id, Integer sessionUserId) {
        Reply replyPS = replyRepository.findById(id);

        int boardId = replyPS.getBoard().getId();

        replyRepository.deleteById(id);

        return boardId;
    }
}
