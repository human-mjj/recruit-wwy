package com.example.recruit_page_wwy.user;

import lombok.Data;

public class UserResponse {

    @Data
    public static class DTO {
        private String username;
        private String password;
        private String email;
        private boolean isCompanyUser;
        private String phone;
        private int role;
        private String comName;
        private Integer industryId;
        private User user;

        public DTO(User user) {
            this.email = user.getEmail();
            this.isCompanyUser = user.getRole() == 1;
            this.phone = user.getPhone();
            this.role = user.getRole();
            this.comName = user.getComName();
            this.industryId = user.getIndustry() != null ? user.getIndustry().getId() : null;
            this.user = user;
            this.username = user.getUsername();
        }
    }

    @Data
    public static class MyPageDTO {
        private String username;
        private String email;

        private User sessionUser;
        private Boolean isCompanyUser;
        private String imgUrl;

        public MyPageDTO(User sessionUser) {
            this.sessionUser = sessionUser;
            this.isCompanyUser = sessionUser.getRole() == 1;
            this.imgUrl = sessionUser.getImgUrl() != null ? "/upload/" + sessionUser.getImgUrl() : "/img/Screenshot_14.png";

            this.username = sessionUser.getUsername();
            this.email = sessionUser.getEmail();
        }
    }
}
