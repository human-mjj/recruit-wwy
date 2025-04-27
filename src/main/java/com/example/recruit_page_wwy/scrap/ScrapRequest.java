package com.example.recruit_page_wwy.scrap;

import com.example.recruit_page_wwy.employment.Employment;
import com.example.recruit_page_wwy.user.User;
import lombok.Data;

public class ScrapRequest {

    public static class UserScrapDTO {
        String title;
        String comName;
        String exp;
        String location;
        String name;

        public UserScrapDTO(String title, String comName, String exp, String location, String name) {
            this.title = title;
            this.comName = comName;
            this.exp = exp;
            this.location = location;
            this.name = name;
        }
    }

    public static class ComScrapDTO {
        String title;
        String username;

        public ComScrapDTO(String title, String username) {
            this.title = title;
            this.username = username;
        }
    }

    @Data
    public static class SaveDTO {
        private Integer employmentId;

        public Scrap toEntity(Integer sessionUserId) {
            return Scrap.builder()
                    .employment(Employment.builder().id(employmentId).build())
                    .resume(null)
                    .user(User.builder().id(sessionUserId).build())
                    .build();
        }
    }
}
