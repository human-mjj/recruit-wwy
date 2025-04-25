package com.example.recruit_page_wwy.employment;

import com.example.recruit_page_wwy.resume.Resume;
import lombok.Data;

import java.sql.Date;
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
        private Integer sessionUserId;
        private Integer sessionUserRole;
        private Boolean isOwner;
        private Boolean isApplicant;

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
        private List<String> duty;
        private List<String> qualification;
        private String jobName;
        private List<String> stack;
        private String stackStr;
        private List<ResumeDTO> resumeList;

        @Data
        public static class ResumeDTO {
            private Integer id;
            private String title;

            public ResumeDTO(Resume resume) {
                this.id = resume.getId();
                this.title = resume.getTitle();
            }
        }

        public DetailDTO(Integer sessionUserId, Integer sessionUserRole, Boolean isOwner, Boolean isApplicant, Integer id, String userImgUrl, String title, String comName, String exp, String edu, String shift, Integer sal, String workingTime, String location, Date endDate, List<String> duty, List<String> qualification, String jobName, List<String> stack, String stackStr, List<ResumeDTO> resumeList) {
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
            this.stackStr = stackStr;
            this.resumeList = resumeList;
        }
    }
}
