package com.example.recruit_page_wwy.scrap;

import com.example.recruit_page_wwy.employment.Employment;
import com.example.recruit_page_wwy.resume.Resume;
import com.example.recruit_page_wwy.user.User;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

public class ScrapRequest {

    @Data
    public static class PageDTO {
        private Integer page = 1;
    }

    @Data
    public static class UserScrapPageDTO {
        private List<UserScrapDTO> scraps;
        private Integer prev;
        private Integer next;

        private Integer resumeId;

        private Integer size;
        private Integer totalCount;
        private Integer totalPage;
        private Integer current;
        private Boolean isFirst;
        private Boolean isLast;
        private List<Integer> numbers;

        public UserScrapPageDTO(List<UserScrapDTO> scraps, Integer current, Integer totalCount) {
            this.size = 8;
            this.scraps = scraps;
            this.totalCount = totalCount;
            this.totalPage = makeTotalPage(totalCount, size);
            this.current = current;
            this.prev = current - 1;
            this.next = current + 1;

            this.isFirst = current == 1;
            this.isLast = current.equals(totalPage) || totalPage == 0;
            this.numbers = makeNumbers(current, totalPage);
        }

        private Integer makeTotalPage(int totalCount, int size) {
            int rest = totalCount % size > 0 ? 1 : 0;
            return totalCount / size + rest;
        }

        private List<Integer> makeNumbers(int current, int totalPage) {
            List<Integer> numbers = new ArrayList<>();
            int start = ((current - 1) / 5) * 5;
            int end = Math.min(start + 5, totalPage);
            for (int i = start; i < end; i++) {
                numbers.add(i + 1);
            }
            return numbers;
        }
    }

    public static class UserScrapDTO {
        String title;
        String comName;
        String exp;
        String location;
        String name;
        Integer employmentId;

        public UserScrapDTO(String title, String comName, String exp, String location, String name, Integer employmentId) {
            this.title = title;
            this.comName = comName;
            this.exp = exp;
            this.location = location;
            this.name = name;
            this.employmentId = employmentId;
        }
    }

    @Data
    public static class ComScrapPageDTO {
        private Integer sessionUserId;
        private Boolean isCompanyUser; // 스크랩 버튼 노출 여부 (false면 보여줌)

        private List<ComScrapDTO> scraps;
        private Integer prev;
        private Integer next;

        private Integer size;
        private Integer totalCount;
        private Integer totalPage;
        private Integer current;
        private Boolean isFirst;
        private Boolean isLast;
        private List<Integer> numbers;

        public ComScrapPageDTO(List<ComScrapDTO> scraps, Integer current, Integer totalCount, User sessionUser) {
            this.sessionUserId = sessionUser != null ? sessionUser.getId() : null;
            this.isCompanyUser = sessionUser != null && sessionUser.getRole() == 1;

            this.size = 5;
            this.scraps = scraps;
            this.totalCount = totalCount;
            this.totalPage = makeTotalPage(totalCount, size);
            this.current = current;
            this.prev = current - 1;
            this.next = current + 1;

            this.isFirst = current == 1;
            this.isLast = current.equals(totalPage) || totalPage == 0;
            this.numbers = makeNumbers(current, totalPage);
        }

        private Integer makeTotalPage(int totalCount, int size) {
            int rest = totalCount % size > 0 ? 1 : 0;
            return totalCount / size + rest;
        }

        private List<Integer> makeNumbers(int current, int totalPage) {
            List<Integer> numbers = new ArrayList<>();
            int start = ((current - 1) / 5) * 5;
            int end = Math.min(start + 5, totalPage);
            for (int i = start; i < end; i++) {
                numbers.add(i + 1);
            }
            return numbers;
        }
    }

    public static class ComScrapDTO {
        Integer id;
        Integer resumeId;
        String title;
        String username;

        public ComScrapDTO(Integer id, Integer resumeId, String title, String username) {
            this.id = id;
            this.resumeId = resumeId;
            this.title = title;
            this.username = username;
        }
    }

    @Data
    public static class SaveDTO {
        private User sessionUser;
        private Integer employmentId;
        private Integer resumeId;

        public Scrap toEntity(User sessionUser) {
            return Scrap.builder()
                    .employment(sessionUser.getRole() == 0 ? Employment.builder().id(employmentId).build() : null)
                    .resume(sessionUser.getRole() == 1 ? Resume.builder().id(resumeId).build() : null)
                    .user(User.builder().id(sessionUser.getId()).build())
                    .build();
        }
    }
}
