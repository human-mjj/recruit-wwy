package com.example.recruit_page_wwy.reply;

import com.example.recruit_page_wwy.board.Board;
import com.example.recruit_page_wwy.user.User;
import lombok.Data;
import lombok.NoArgsConstructor;

public class ReplyRequest {


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
