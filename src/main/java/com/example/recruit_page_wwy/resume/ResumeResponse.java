package com.example.recruit_page_wwy.resume;

import com.example.recruit_page_wwy.job.Job;
import com.example.recruit_page_wwy.resumestack.ResumeStack;
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
        private Job job;
        private List<ResumeStack> resumeStack;
        private Integer jobId;
        private String title;
        private String exp;
        private String edu;
        private String location;
        private String qualified;
        private String activity;
        private String imgUrl;
        private String LETTER;


        public DetailDTO(Integer id, Integer userId, Job job, List<ResumeStack> resumeStack, String username, String email, String phone, String title, String exp, String edu, String location, String qualified, String activity, String imgUrl, String LETTER) {
            this.id = id;
            this.userId = userId;
            this.job = job;
            this.resumeStack = resumeStack;
            this.username = username;
            this.email = email;
            this.phone = phone;
            this.title = title;
            this.exp = exp;
            this.edu = edu;
            this.location = location;
            this.qualified = qualified;
            this.activity = activity;
            this.imgUrl = imgUrl;
            this.LETTER = LETTER;
        }


    }

}
