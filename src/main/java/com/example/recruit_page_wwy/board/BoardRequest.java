package com.example.recruit_page_wwy.board;

import com.example.recruit_page_wwy.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;

public class BoardRequest {

    @Data
    public static class SaveDTO {
        private Integer user_id;
        private String title;
        private String content;


        public Board toEntity(User user) {
            return Board.builder()
                    .title(title)
                    .content(content)

                    .user(user) // user객체 필요
                    .build();

        }
    }

    @AllArgsConstructor
    @Data
    public static class UpdateDTO {
        private String title;
        private String content;

    }

}
