package com.example.recruit_page_wwy.resume;

import lombok.Data;

import java.util.List;

public class ResumeResponse {

    @Data
    public static class MainDTO {
        private List<Resume> resumes;


        public MainDTO(List<Resume> resumes) {
            this.resumes = resumes;

        }

    }


    @Data
    public static class DetailDTO {
        private Integer id;
        private String username;
        private String email;
        private String phone;
        private Integer userId;
        private String job;
        private Integer job_id;
        private String title;
        private String exp;
        private String edu;
        private String location;
        private String qualified;
        private String activity;
        private String imgUrl;
        private String LETTER;


        public DetailDTO(Integer userId, String username, String email, String phone, String title, String exp, String edu, Integer job_id, String location, String qualified, String activity, String imgUrl, String LETTER) {
            this.userId = userId;
            this.username = username;
            this.email = email;
            this.phone = phone;
            this.title = title;
            this.exp = exp;
            this.edu = edu;
            this.job_id = job_id;
            this.location = location;
            this.qualified = qualified;
            this.activity = activity;
            this.imgUrl = imgUrl;
            this.LETTER = LETTER;
        }


    }

}
