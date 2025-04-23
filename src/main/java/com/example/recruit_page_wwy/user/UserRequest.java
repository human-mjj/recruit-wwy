package com.example.recruit_page_wwy.user;

import com.example.recruit_page_wwy.industry.Industry;
import lombok.Data;

public class UserRequest {

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
                    .role(role)
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
                    .industry(Industry.builder().id(industryId).build())
                    .build();
        }
    }

    @Data
    public static class LoginDTO {
        private String email;
        private String password;
        private int role;
    }
}
