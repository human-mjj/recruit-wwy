package com.example.recruit_page_wwy.scrap;

import com.example.recruit_page_wwy.employment.Employment;
import com.example.recruit_page_wwy.resume.Resume;
import com.example.recruit_page_wwy.user.User;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

public class ScrapRequest {

    @Data
    public static class UserScrapPageDTO {
        private List<UserScrapDTO> scraps;
        private Integer prev;
        private Integer next;

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
            this.prev = (current <= 1) ? 1 : current - 1;
            this.next = (current >= totalPage) ? totalPage : current + 1;
            this.isFirst = current == 1;
            this.isLast = current.equals(totalPage);
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
        Integer emplorymentId;

        public UserScrapDTO(String title, String comName, String exp, String location, String name, Integer emplorymentId) {
            this.title = title;
            this.comName = comName;
            this.exp = exp;
            this.location = location;
            this.name = name;
            this.emplorymentId = emplorymentId;
        }
    }

    @Data
    public static class ComScrapPageDTO {
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

        public ComScrapPageDTO(List<ComScrapDTO> scraps, Integer current, Integer totalCount) {
            this.size = 5;
            this.scraps = scraps;
            this.totalCount = totalCount;
            this.totalPage = makeTotalPage(totalCount, size);
            this.current = current;
            this.prev = (current <= 1) ? 1 : current - 1;
            this.next = (current >= totalPage) ? totalPage : current + 1;
            this.isFirst = current == 1;
            this.isLast = current.equals(totalPage);
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
        String title;
        String username;

        public ComScrapDTO(Integer id, String title, String username) {
            this.id = id;
            this.title = title;
            this.username = username;
        }
    }

    @Data
    public static class userScrapSaveDTO {
        private Integer employmentId;

        public Scrap toEntity(Integer sessionUserId) {
            return Scrap.builder()
                    .employment(Employment.builder().id(employmentId).build())
                    .resume(null)
                    .user(User.builder().id(sessionUserId).build())
                    .build();
        }
    }

    @Data
    public static class comScrapSaveDTO {
        private Integer resumeId;

        public Scrap toEntity(Integer sessionUserId) {
            return Scrap.builder()
                    .employment(null)
                    .resume(Resume.builder().id(resumeId).build())
                    .user(User.builder().id(sessionUserId).build())
                    .build();
        }
    }

}
