package com.example.recruit_page_wwy.user;

import lombok.Data;

public class UserResponse {

//    @Data
//    public static class MyPageDTO {
//        private User sessionUser;
//        private Boolean isCompanyUser;
//
//        public MyPageDTO(User sessionUser) {
//            this.sessionUser = sessionUser;
//            this.isCompanyUser = sessionUser.getRole() == 1;
//        }
//    }

    @Data
    public static class MyPageDTO {
        private String email;

    }
}
