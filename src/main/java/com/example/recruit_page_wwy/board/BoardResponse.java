package com.example.recruit_page_wwy.board;

import com.example.recruit_page_wwy.reply.Reply;
import com.example.recruit_page_wwy.user.User;
import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class BoardResponse {

    @Data
    public static class ListDTO {
        private Integer sessionUserId;
        private Boolean isCompanyUser; // 스크랩 버튼 노출 여부 (false면 보여줌)

        private List<Board> boards;
        private Integer prev;
        private Integer next;
        private Integer prevPage;
        private Integer nextPage;

        private Integer size;
        private Integer totalCount;
        private Integer totalPage;
        private Integer current;
        private Boolean isFirst;
        private Boolean isLast;
        private List<Integer> numbers;

        private String keyword;


        public ListDTO(List<Board> boards, Integer current, Integer totalCount, User sessionUser, String keyword) {
            this.sessionUserId = sessionUser != null ? sessionUser.getId() : null;
            this.isCompanyUser = sessionUser != null && sessionUser.getRole() == 1;

            this.boards = boards;
            this.size = 5;
            this.prev = current - 1;
            this.next = current + 1;
            this.prevPage = prev + 1;
            this.nextPage = next + 1;
            this.totalCount = totalCount;
            this.totalPage = makeTotalPage(totalCount, size);
            this.isFirst = current == 0;
            this.isLast = (totalPage - 1) == current;
            this.numbers = makeNumbers(current, totalPage);
            this.keyword = keyword;
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
        private Integer sessionUserId;
        private Boolean isOwner; // 수정/삭제 버튼 표시용
        private Boolean isCompanyUser; // 스크랩 버튼 노출 여부 (false면 보여줌)

        private Integer id;
        private Integer userId;
        private String username;
        private String title;
        private String content;
        private Timestamp createdAt;
        private List<ReplyDTO> replyList;

        @Builder
        public DetailDTO(Board board, List<ReplyDTO> replyList, User sessionUser) {
            this.sessionUserId = sessionUser != null ? sessionUser.getId() : null;
            this.isOwner = sessionUser != null && sessionUser.getId() == board.getUser().getId();
            this.isCompanyUser = sessionUser != null && sessionUser.getRole() == 1;

            this.id = board.getId();
            this.userId = board.getUser().getId();
            this.username = board.getUser().getUsername();
            this.title = board.getTitle();
            this.content = board.getContent();
            this.createdAt = board.getCreatedAt();

            this.replyList = replyList;
        }

        @Data
        public static class ReplyDTO {
            private int boardId;
            private int id;
            private String username;
            private String content;
            private Timestamp createdAt;
            private Boolean isOwner;

            @Builder
            public ReplyDTO(Reply reply, User sessionUser) {
                this.boardId = reply.getBoard().getId();
                this.id = reply.getId();
                this.username = reply.getUser().getUsername();
                this.content = reply.getContent();
                this.createdAt = reply.getCreatedAt();
                this.isOwner = sessionUser != null && sessionUser.getId() == reply.getUser().getId();
            }
        }


    }

    @Data
    public static class UpdateViewDTO {
        private Integer id;
        private String title;
        private String content;

        private Boolean isCompanyUser;

        public UpdateViewDTO(Board board, User sessionUser) {
            this.id = board.getId();
            this.title = board.getTitle();
            this.content = board.getContent();
            this.isCompanyUser = sessionUser != null && sessionUser.getRole() == 1;
        }
    }
}
