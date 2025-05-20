package com.example.recruit_page_wwy.user;

import lombok.Builder;
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
        private String accessToken;

        public DTO(User user, String accessToken) {
            this.username = user.getUsername();
            this.email = user.getEmail();
            this.isCompanyUser = user.getRole() == 1;
            this.phone = user.getPhone();
            this.role = user.getRole();
            this.comName = user.getComName() != null ? user.getComName() : "";
            this.industryId = user.getIndustry() != null ? user.getIndustry().getId() : null;
            this.accessToken = accessToken == null ? null : accessToken;
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

    @Data
    public static class UpdateDTO {
        private int id;
        private String username;
        private String email;
        private String phone;
        private String imgUrl;

        // 기업, 구직자 구분 : not null
        private int role;

        // 기업용 : 구직자는 null 처리해야 함
        private String comName;
        private Integer industryId;

        public UpdateDTO(User user) {
            this.id = user.getId();
            this.username = user.getUsername();
            this.email = user.getEmail();
            this.phone = user.getPhone();
            this.imgUrl = user.getImgUrl();
            this.role = user.getRole();
            this.comName = user.getComName() != null ? user.getComName() : "";
            this.industryId = user.getIndustry() != null ? user.getIndustry().getId() : null;
        }
    }

    @Data
    public static class TokenDTO {
        private String accessToken;

        @Builder
        public TokenDTO(String accessToken) {
            this.accessToken = accessToken;
        }
    }

}
