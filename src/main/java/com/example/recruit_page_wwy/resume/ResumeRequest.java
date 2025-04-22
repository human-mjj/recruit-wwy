package com.example.recruit_page_wwy.resume;

import lombok.Data;

public class ResumeRequest {

    @Data
    public static class SaveDTO {
        private Integer user_id;
        private String title;
        private String exp;
        private String edu;
        private Integer job_id;
        private String location;
        private String qualified;
        private String activity;
        private String img_url;

        public SaveDTO(Integer user_id, String title, String exp, String edu, Integer job_id, String location, String qualified, String activity, String img_url) {
            this.user_id = user_id;
            this.title = title;
            this.exp = exp;
            this.edu = edu;
            this.job_id = job_id;
            this.location = location;
            this.qualified = qualified;
            this.activity = activity;
            this.img_url = img_url;
        }
    }

}
