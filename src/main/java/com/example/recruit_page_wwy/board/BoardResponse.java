package com.example.recruit_page_wwy.board;

import lombok.Data;

public class BoardResponse {

    @Data
    public static class DetailDTO {
        private int user_id;
        private String username;
        private String title;
        private String content;

        public DetailDTO(int user_id, String username, String content, String title) {
            this.user_id = user_id;
            this.username = username;
            this.title = title;
            this.content = content;

        }
    }
}
