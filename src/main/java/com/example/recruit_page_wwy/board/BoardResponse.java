package com.example.recruit_page_wwy.board;

import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class BoardResponse {

    @Data
    public static class ListDTO {
        private List<Board> boards;
        private Integer prev;
        private Integer next;

        private Integer size;
        private Integer totalCount;
        private Integer totalPage;
        private Integer current;
        private Boolean isFirst;
        private Boolean isLast;
        private List<Integer> numbers;

        public ListDTO(List<Board> boards, Integer current, Integer totalCount) {
            this.boards = boards;
            this.size = 5;
            this.prev = current - 1;
            this.next = current + 1;
            this.totalCount = totalCount;
            this.totalPage = makeTotalPage(totalCount, size);
            this.isFirst = current == 0;
            this.isLast = (totalPage - 1) == current;
            this.numbers = makeNumbers(current, totalPage);
        }

        private Integer makeTotalPage(int totalCount, int size) {
            int rest = totalCount % size > 0 ? 1 : 0; //나머지값은 0~2를 순회함
            return totalCount / size + rest;
        }

        private List<Integer> makeNumbers(int current, int totalPage) {
            List<Integer> numbers = new ArrayList<>();

            int start = (current / 5) * 5 + 1;
            int end = Math.min(start + 4, totalPage);

            for (int i = start; i <= end; i++) {
                numbers.add(i);
            }

            return numbers;
        }
    }

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
