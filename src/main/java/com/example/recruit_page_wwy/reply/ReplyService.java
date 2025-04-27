package com.example.recruit_page_wwy.reply;


import com.example.recruit_page_wwy.board.Board;
import com.example.recruit_page_wwy.board.BoardRepository;
import com.example.recruit_page_wwy.user.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class ReplyService {
    private final ReplyRepository replyRepository;
    private final BoardRepository boardRepository;

    @PersistenceContext
    private EntityManager em;


    @Transactional
    public Reply replySave(ReplyRequest.SaveDTO saveDTO, User sessionUser) {
        Board board = boardRepository.findById(saveDTO.getBoardId());
        if (board == null) {
            throw new RuntimeException("게시글을 찾을 수 없습니다. id = " + saveDTO.getBoardId());
        }

        Reply reply = saveDTO.toEntity(board, sessionUser);

        em.persist(reply);
        return reply;
    }

    @Transactional
    public Integer delete(Integer id, Integer sessionUserId) {
        Reply replyPS = replyRepository.findById(id);

        int boardId = replyPS.getBoard().getId();

        replyRepository.deleteById(id);

        return boardId;
    }
}
