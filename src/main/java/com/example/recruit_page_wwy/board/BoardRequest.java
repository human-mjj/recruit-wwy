package com.example.recruit_page_wwy.board;

import com.example.recruit_page_wwy.user.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

public class BoardRequest {

    @Data
    public static class SaveDTO {
        private Integer user_id;

        @NotBlank(message = "제목은 비어 있을 수 없습니다.")
        @Size(max = 50, message = "제목은 50자 이내여야 합니다.")
        @Pattern(regexp = "^[^<>=]*$", message = "제목에 <, >, = 문자를 포함할 수 없습니다.")
        private String title;
        @NotBlank(message = "내용은 비어 있을 수 없습니다.")
        @Size(max = 1000, message = "내용은 1000자 이내여야 합니다.")
        @Pattern(regexp = "^[^<>=]*$", message = "내용에 <, >, = 문자를 포함할 수 없습니다.")
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
        @NotBlank(message = "제목은 비어 있을 수 없습니다.")
        @Size(max = 50, message = "제목은 50자 이내여야 합니다.")
        @Pattern(regexp = "^[^<>=]*$", message = "제목에 <, >, = 문자를 포함할 수 없습니다.")
        private String title;
        @NotBlank(message = "내용은 비어 있을 수 없습니다.")
        @Size(max = 1000, message = "내용은 1000자 이내여야 합니다.")
        @Pattern(regexp = "^[^<>=]*$", message = "내용에 <, >, = 문자를 포함할 수 없습니다.")
        private String content;

    }

}
