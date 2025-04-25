package com.example.recruit_page_wwy.apply;


import lombok.Data;

import java.sql.Timestamp;

public class ApplyResponse {

    @Data
    public static class UserApplyDTO {
        //        private User user;
        private String comName;
        private Integer jobId;
        private Timestamp createdAt;
        private String progress;

        public UserApplyDTO(String comName, Integer jobId, Timestamp createdAt, String progress) {
//            this.user = user;
            this.comName = comName;
            this.jobId = jobId;
            this.createdAt = createdAt;
            this.progress = progress;
        }
    }

    @Data
    public static class ComApplyDTO {
        private Integer id;
        private String title;
        private String username;
        private Integer jobId;
        private Timestamp createdAt;
        private String progress;

        public ComApplyDTO(Integer id, String title, String username, Integer jobId, Timestamp createdAt, String progress) {
            this.id = id;
            this.title = title;
            this.username = username;
            this.jobId = jobId;
            this.createdAt = createdAt;
            this.progress = progress;
        }
    }
}
