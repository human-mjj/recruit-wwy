package com.example.recruit_page_wwy.reply;

import com.example.recruit_page_wwy.board.Board;
import com.example.recruit_page_wwy.user.User;
import lombok.Data;
import lombok.NoArgsConstructor;

public class ReplyRequest {

    @Data
    public static class DTO {
        private int id;
        private Board board;
        private User user;
        private String content;
        private String createdAt;

        public DTO(Reply reply, Board board, User sessionUser) {
            this.id = reply.getId();
            this.board = board;
            this.user = sessionUser;
            this.content = reply.getContent();
            this.createdAt = reply.getCreatedAt().toString();
        }
    }

    @Data
    @NoArgsConstructor
    public static class SaveDTO {
        private Integer boardId;
        private String content;

        public Reply toEntity(User sessionUser) {
            return Reply.builder()
                    .content(content)
                    .board(Board.builder().id(boardId).build())
                    .user(sessionUser)
                    .build();
        }
    }
}
