package com.example.recruit_page_wwy.board;


import com.example.recruit_page_wwy.user.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public List<Board> boardList() {
        List<Board> boards = boardRepository.findAll();
        return boards;
    }

    public BoardResponse.DetailDTO boardDetail(Integer id) {
        Board board = boardRepository.findByBoardId(id);

        User user = board.getUser();

        return new BoardResponse.DetailDTO(
                board.getUser().getId(),
                board.getUser().getUsername(),
                board.getTitle(),
                board.getContent()
        );
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
