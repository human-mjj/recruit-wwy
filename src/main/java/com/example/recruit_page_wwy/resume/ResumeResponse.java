package com.example.recruit_page_wwy.resume;

import com.example.recruit_page_wwy.job.Job;
import com.example.recruit_page_wwy.resumestack.ResumeStack;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

public class ResumeResponse {

    @Data
    public static class MainDTO {
        private List<Resume> resumes;
        private Integer prev;
        private Integer next;
        private Integer size;
        private Integer totalCount;
        private Integer totalPage;
        private Integer current;
        private Boolean isFirst;
        private Boolean isLast;
        private List<Integer> numbers;

        public MainDTO(List<Resume> resumes, Integer current, Integer totalCount) {
            this.resumes = resumes;
            this.size = 5;
            this.totalCount = totalCount;
            this.totalPage = makeTotalPage(totalCount, size);
            this.current = current;
            this.prev = current - 1;
            this.next = current + 1;
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
            int start = ((current - 1) / 5) * 5 + 1;
            int end = Math.min(start + 4, totalPage);
            for (int i = start; i <= end; i++) {
                numbers.add(i);
            }
            return numbers;
        }
    }


    @Data
    public static class DetailDTO {
        private Integer id;
        private Integer userId;
        private Job job;
        private List<ResumeStack> resumeStack;
        private String username;
        private String email;
        private String phone;
        private Integer jobId;
        private String title;
        private String exp;
        private String edu;
        private String location;
        private String qualified;
        private String activity;
        private String imgUrl;
        private String LETTER;

        private Boolean isScrap;
        private Integer scrapId;

        public DetailDTO(Integer id, String username, String email, String phone, Integer userId, Job job, List<ResumeStack> resumeStack, Integer jobId, String title, String exp, String edu, String location, String qualified, String activity, String imgUrl, String LETTER, Boolean isScrap, Integer scrapId) {
            this.id = id;
            this.username = username;
            this.email = email;
            this.phone = phone;
            this.userId = userId;
            this.job = job;
            this.resumeStack = resumeStack;
            this.jobId = jobId;
            this.title = title;
            this.exp = exp;
            this.edu = edu;
            this.location = location;
            this.qualified = qualified;
            this.activity = activity;
            this.imgUrl = imgUrl;
            this.LETTER = LETTER;

            this.isScrap = isScrap;
            this.scrapId = scrapId;
        }
    }

}
