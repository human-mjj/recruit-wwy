package com.example.recruit_page_wwy.board;

import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

public class BoardResponse {

    @Data
    public static class DetailDTO {
        private Integer userId;
        private String username;
        private String title;
        private String content;
        private Timestamp createdAt;
        private List<ReplyDTO> replyList;


        @Builder
        public DetailDTO(Integer userId, String username, String content, String title, Timestamp createdAt, List<ReplyDTO> replyList) {
            this.userId = userId;
            this.username = username;
            this.title = title;
            this.content = content;
            this.createdAt = createdAt;
            this.replyList = replyList;
        }

        @Data
        public static class ReplyDTO {
            private int id;
            private int userId;
            private String content;
            private Timestamp createdAt;

            @Builder
            public ReplyDTO(int id, int userId, String content, Timestamp createdAt) {
                this.id = id;
                this.userId = userId;
                this.content = content;
                this.createdAt = createdAt;
            }

        }


    }
}
