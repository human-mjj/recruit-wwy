package com.example.recruit_page_wwy.user;

import lombok.Data;

public class UserResponse {

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
