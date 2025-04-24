package com.example.recruit_page_wwy.employment;

import com.example.recruit_page_wwy.resume.Resume;
import lombok.Data;

import java.util.Date;
import java.util.List;

public class EmploymentResponse {
    @Data
    public static class ListDTO {
        private Integer id;
        private String title;
        private String comName;
        private String exp;
        private String location;
        private String jobName;
        private String imgUrl;

        public ListDTO(Employment e) {
            this.id = e.getId();
            this.title = e.getTitle();
            this.comName = e.getUser().getComName();
            this.exp = e.getExp();
            this.location = e.getLocation();
            this.jobName = e.getJob().getName();
            this.imgUrl = e.getImgUrl();
        }
    }

    @Data
    public static class PublicListDTO {
        private Integer id;
        private String title;
        private String comName;
        private String exp;
        private String location;
        private String jobName;
        private String imgUrl;

        public PublicListDTO(Employment e) {
            this.id = e.getId();
            this.title = e.getTitle();
            this.comName = e.getUser().getComName();
            this.exp = e.getExp();
            this.location = e.getLocation();
            this.jobName = e.getJob().getName();
            this.imgUrl = e.getImgUrl();
        }
    }

    @Data
    public static class DetailDTO {
        private Integer sessionUserId; // 로그인한 유저 아이디
        private Integer sessionUserRole; // 로그인한 유저 역할 기업/구직자
        private Boolean isOwner; // 로그인한 유저가 공고 작성자인지
        private Boolean isApplicant; // 로그인한 유저가 구직자인지

        private Integer id;
        private String userImgUrl; // user_tb 테이블
        private String title;
        private String comName; // user_tb 테이블
        private String exp;
        private String edu;
        private String shift;
        private Integer sal;
        private String workingTime;
        private String location;
        private Date endDate;
        private List<String> duty; // 화면에 여러개 순차적으로 뿌려짐
        private List<String> qualification; // 화면에 여러개 순차적으로 뿌려짐
        private String jobName; // job_tb 테이블
        private List<String> stack; // 화면에 여러개 순차적으로 뿌려짐
        private List<ResumeDTO> resumeList; // 이력서 선택 시 필요한 데이터

        @Data
        public static class ResumeDTO {
            private Integer id;
            private String title;

            public ResumeDTO(Resume resume) {
                this.id = resume.getId();
                this.title = resume.getTitle();
            }
        }

        public DetailDTO(Integer sessionUserId, Integer sessionUserRole, Boolean isOwner, Boolean isApplicant, Integer id, String userImgUrl, String title, String comName, String exp, String edu, String shift, Integer sal, String workingTime, String location, Date endDate, List<String> duty, List<String> qualification, String jobName, List<String> stack, List<ResumeDTO> resumeList) {
            this.sessionUserId = sessionUserId;
            this.sessionUserRole = sessionUserRole;
            this.isOwner = isOwner;
            this.isApplicant = isApplicant;
            this.id = id;
            this.userImgUrl = userImgUrl;
            this.title = title;
            this.comName = comName;
            this.exp = exp;
            this.edu = edu;
            this.shift = shift;
            this.sal = sal;
            this.workingTime = workingTime;
            this.location = location;
            this.endDate = endDate;
            this.duty = duty;
            this.qualification = qualification;
            this.jobName = jobName;
            this.stack = stack;
            this.resumeList = resumeList;
        }
    }
}
