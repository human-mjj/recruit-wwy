package com.example.recruit_page_wwy.board;


import com.example.recruit_page_wwy.reply.Reply;
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

    @PersistenceContext
    private EntityManager em;


    @Transactional
    public void boardSave(BoardRequest.SaveDTO saveDTO) {
        boardRepository.save(saveDTO.getUser_id(), saveDTO.getTitle(), saveDTO.getContent());
    }

    public BoardResponse.ListDTO boardList(Integer page) {
        Long totalCount = boardRepository.totalCount();
        List<Board> boards = boardRepository.findAll(page);
        return new BoardResponse.ListDTO(boards, page, totalCount.intValue());
    }

    public BoardResponse.DetailDTO boardDetail(Integer id) {
        Board board = em.find(Board.class, id);

        List<Reply> replyList = em.createQuery("select r from Reply r where r.board.id = :id", Reply.class)
                .setParameter("id", id)
                .getResultList();

        User user = board.getUser();


        List<BoardResponse.DetailDTO.ReplyDTO> replyDTOList = new ArrayList<>();

        for (Reply reply : replyList) {
            BoardResponse.DetailDTO.ReplyDTO replyDTO = new BoardResponse.DetailDTO.ReplyDTO(
                    reply.getId(),
                    reply.getUser().getId(),
                    reply.getContent(),
                    reply.getCreatedAt()
            );
            replyDTOList.add(replyDTO);
        }

        return BoardResponse.DetailDTO.builder()
                .userId(board.getId())  // 게시글 ID
                .title(board.getTitle())
                .content(board.getContent())
                .username(board.getUser().getUsername())
                .replyList(replyDTOList)  // 댓글 리스트 추가
                .build();
    }

    @Transactional
    public void boardUpdate(Integer id, BoardRequest.UpdateDTO updateDTO) {
        boardRepository.boardUpdate(id, updateDTO.getTitle(), updateDTO.getContent());
    }

    @Transactional
    public void boardDelete(Integer id) {
        Board board = em.find(Board.class, id);
        if (board != null) {
            em.remove(board);
        }
    }
}
