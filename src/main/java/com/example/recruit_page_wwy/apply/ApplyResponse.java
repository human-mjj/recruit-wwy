package com.example.recruit_page_wwy.apply;


import lombok.Data;

public class ApplyResponse {

    @Data
    public static class UserApplyDTO {
        //        private User user;
        private String comName;
        private String name;
        private String createdAt;
        private String progress;

        public UserApplyDTO(String comName, String name, String createdAt, String progress) {
//            this.user = user;
            this.comName = comName;
            this.name = name;
            this.createdAt = createdAt;
            this.progress = progress;
        }
    }

    @Data
    public static class ComApplyDTO {
        private Integer id;
        private String title;
        private String username;
        private String name;
        private String createdAt;
        private String progress;

        public ComApplyDTO(Integer id, String title, String username, String name, String createdAt, String progress) {
            this.id = id;
            this.title = title;
            this.username = username;
            this.name = name;
            this.createdAt = createdAt;
            this.progress = progress;
        }
    }
}
