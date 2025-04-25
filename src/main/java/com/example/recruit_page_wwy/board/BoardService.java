package com.example.recruit_page_wwy.board;


import com.example.recruit_page_wwy.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BoardService {
    private final BoardRepository boardRepository;

    @Transactional
    public void boardSave(BoardRequest.SaveDTO saveDTO) {
        boardRepository.save(saveDTO.getUser_id(), saveDTO.getTitle(), saveDTO.getContent());
    }

    public List<Board> boardList() {
        List<Board> boards = boardRepository.findAll();
        return boards;
    }

    public BoardResponse.DetailDTO boardDetail(Integer boardId) {
        Board board = boardRepository.findByBoardId(boardId);

        User user = board.getUser();

        return new BoardResponse.DetailDTO(
                board.getUser().getId(),
                board.getTitle(),
                board.getContent()
        );
    }
}
