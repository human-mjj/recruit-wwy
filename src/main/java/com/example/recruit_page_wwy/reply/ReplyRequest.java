package com.example.recruit_page_wwy.reply;

import com.example.recruit_page_wwy.board.Board;
import com.example.recruit_page_wwy.user.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
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

        @NotBlank(message = "댓글 내용은 필수입니다.")
        @Size(max = 200, message = "댓글은 200자 이내로 작성해주세요.")
        @Pattern(regexp = "^[^<>=\"']*$", message = "댓글 내용에 <, >, =, \" 등 특수문자는 사용할 수 없습니다.")
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
