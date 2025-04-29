package com.example.recruit_page_wwy.resume;

import com.example.recruit_page_wwy.job.Job;
import com.example.recruit_page_wwy.resumestack.ResumeStack;
import com.example.recruit_page_wwy.user.User;
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
        private Integer sessionUserId;
        private Integer sessionUserRole;
        private Boolean isOwner; // 수정/삭제 버튼 표시용
        private Boolean isApplicant; // 지원자 여부
        private Boolean isCompanyUser; // 스크랩 버튼 노출 여부 (false면 보여줌)

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

        public DetailDTO(User sessionUser, Resume resume, List<ResumeStack> resumeStack, Boolean isScrap, Integer scrapId) {
            this.sessionUserId = sessionUser != null ? sessionUser.getId() : null;
            this.sessionUserRole = sessionUser != null ? sessionUser.getRole() : null;
            this.isOwner = sessionUser != null && sessionUser.getId() == resume.getUser().getId();
            this.isCompanyUser = sessionUser != null && sessionUser.getRole() == 1;
            this.isApplicant = false; // 기본 false (지원 여부 체크는 별도로)

            this.id = resume.getId();
            this.userId = sessionUser.getId();
            this.job = resume.getJob();
            this.resumeStack = resumeStack;
            this.username = sessionUser.getUsername();
            this.email = sessionUser.getEmail();
            this.phone = sessionUser.getPhone();
            this.jobId = resume.getJob().getId();
            this.title = resume.getTitle();
            this.exp = resume.getExp();
            this.edu = resume.getEdu();
            this.location = resume.getLocation();
            this.qualified = resume.getQualified();
            this.activity = resume.getActivity();
            this.imgUrl = resume.getImgUrl();
            this.LETTER = resume.getLetter();

            this.isScrap = isScrap;
            this.scrapId = scrapId == null ? 0 : scrapId;
        }
    }
}