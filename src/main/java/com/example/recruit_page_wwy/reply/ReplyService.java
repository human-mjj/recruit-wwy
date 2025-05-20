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
    public ReplyResponse.DTO replySave(ReplyRequest.SaveDTO reqDTO, User sessionUser) {
        if (reqDTO.getContent().isBlank()) throw new ExceptionApi400("댓글 내용을 입력하세요.");
        Board board = boardRepository.findById(reqDTO.getBoardId());
        if (board == null) {
            throw new ExceptionApi404("게시글을 찾을 수 없습니다. id = " + reqDTO.getBoardId());
        }

        Reply replyPS = replyRepository.replySave(reqDTO.toEntity(sessionUser));

        return new ReplyResponse.DTO(replyPS);
    }

    @Transactional
    public void delete(Integer id, User sessionUser) {
        Reply replyPS = replyRepository.findById(id);
        System.out.println("sessionUser.getId " + sessionUser.getId());
        System.out.println("replyPS.getUser().getId()" + replyPS.getUser().getId());
        if (replyPS == null) throw new ExceptionApi404("자원을 찾을 수 없습니다");
        if (replyPS.getUser().getId() != sessionUser.getId()) throw new ExceptionApi403("권한이 없습니다");

        int boardId = replyPS.getBoard().getId();

        replyRepository.deleteById(id);
    }
}
