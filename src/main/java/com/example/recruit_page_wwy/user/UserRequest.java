package com.example.recruit_page_wwy.user;

import lombok.Data;

public class UserRequest {

//    @Data
//    public static class MyPageDTO {
//        private User sessionUser;
//        private Boolean isCompanyUser;
//
//        public MyPageDTO(User sessionUser) {
//            this.sessionUser = sessionUser;
//            this.isCompanyUser = sessionUser.getRole() == 1 ? true : false;
//        }
//    }

    @Data
    public static class UpdateDTO {
        private String comName;
        private String phone;
        private String password;
        private Integer industryId;
        //private MultipartFile uploadingImg;
        private String imgUrl;

        public User toEntity(String imgUrl) {
            if (comName != null && !comName.isEmpty() && industryId != null)
                return User.builder()
                        .comName(comName)
                        .industryId(industryId)
                        .role(1)
                        .phone(phone)
                        .password(password)
                        .imgUrl(imgUrl)
                        .build();

            else return User.builder()
                    .phone(phone)
                    .password(password)
                    .imgUrl(imgUrl)
                    .role(0)
                    .build();
        }
    }

    @Data
    public static class UserDTO {
        private String username;
        private String email;
        private String phone;
        private String password;
        private int role;

        public User toEntity() {
            return User.builder()
                    .username(username)
                    .email(email)
                    .phone(phone)
                    .password(password)
                    .role(0)
                    .build();
        }
    }

    @Data
    public static class ComDTO {
        private String username;
        private String email;
        private String phone;
        private String password;
        private int role;
        private String comName;
        private Integer industryId;

        public User toEntity() {
            return User.builder()
                    .username(username)
                    .email(email)
                    .phone(phone)
                    .password(password)
                    .role(1)
                    .comName(comName)
                    .industryId(industryId)
                    .build();
        }
    }

    @Data
    public static class LoginDTO {
        private String email;
        private String password;
        private int role;
//        private String rememberMe;
    }
}
