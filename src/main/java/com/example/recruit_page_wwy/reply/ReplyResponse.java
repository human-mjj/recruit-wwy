package com.example.recruit_page_wwy.reply;

import lombok.Data;

public class ReplyResponse {
    @Data
    public static class DTO {
        private int id;
        private Integer boardId;
        private Integer userId;
        private String content;
        private String createdAt;

        public DTO(Reply reply) {
            this.id = reply.getId();
            this.boardId = reply.getBoard().getId();
            this.userId = reply.getUser().getId();
            this.content = reply.getContent();
            this.createdAt = reply.getCreatedAt().toString();
        }
    }
}
