package com.example.recruit_page_wwy.board;

import lombok.Data;

public class BoardRequest {

    @Data
    public static class SaveDTO {
        private Integer user_id;
        private String title;
        private String content;


        public SaveDTO(Integer user_id, String title, String content) {
            this.user_id = user_id;
            this.title = title;
            this.content = content;
        }

    }

    @Data
    public static class UpdateDTO {
        private String title;
        private String content;

        public UpdateDTO(String title, String content) {
            this.title = title;
            this.content = content;
        }
    }
}
