package com.example.recruit_page_wwy.reply;


import com.example.recruit_page_wwy._core.error.ex.ExceptionApi400;
import com.example.recruit_page_wwy._core.error.ex.ExceptionApi403;
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

    @Transactional
    public ReplyResponse.DTO replySave(ReplyRequest.SaveDTO saveDTO, User sessionUser) {
        if (saveDTO.getContent().isBlank()) throw new ExceptionApi400("댓글 내용을 입력하세요.");
        Board board = boardRepository.findById(saveDTO.getBoardId());
        if (board == null) {
            throw new ExceptionApi404("게시글을 찾을 수 없습니다. id = " + saveDTO.getBoardId());
        }

        Reply replyPS = replyRepository.replySave(saveDTO.toEntity(sessionUser));

        return new ReplyResponse.DTO(replyPS);
    }
    
    @Transactional
    public void delete(Integer id, User sessionUser) {
        Reply replyPS = replyRepository.findById(id);
        if (replyPS == null) throw new ExceptionApi404("404 Not Found");
        if (replyPS.getUser().getId() != sessionUser.getId()) throw new ExceptionApi403("403 Forbidden");

        int boardId = replyPS.getBoard().getId();

        replyRepository.deleteById(id);
    }
}
