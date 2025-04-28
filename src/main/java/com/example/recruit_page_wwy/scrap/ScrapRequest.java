package com.example.recruit_page_wwy.scrap;

import com.example.recruit_page_wwy.employment.Employment;
import com.example.recruit_page_wwy.resume.Resume;
import com.example.recruit_page_wwy.user.User;
import lombok.Data;

public class ScrapRequest {

    public static class UserScrapDTO {
        String title;
        String comName;
        String exp;
        String location;
        String name;
        Integer emplorymentId;

        public UserScrapDTO(String title, String comName, String exp, String location, String name, Integer emplorymentId) {
            this.title = title;
            this.comName = comName;
            this.exp = exp;
            this.location = location;
            this.name = name;
            this.emplorymentId = emplorymentId;
        }
    }

    public static class ComScrapDTO {
        Integer id;
        String title;
        String username;

        public ComScrapDTO(Integer id, String title, String username) {
            this.id = id;
            this.title = title;
            this.username = username;
        }
    }

    @Data
    public static class userScrapSaveDTO {
        private Integer employmentId;

        public Scrap toEntity(Integer sessionUserId) {
            return Scrap.builder()
                    .employment(Employment.builder().id(employmentId).build())
                    .resume(null)
                    .user(User.builder().id(sessionUserId).build())
                    .build();
        }
    }

    @Data
    public static class comScrapSaveDTO {
        private Integer resumeId;

        public Scrap toEntity(Integer sessionUserId) {
            return Scrap.builder()
                    .employment(null)
                    .resume(Resume.builder().id(resumeId).build())
                    .user(User.builder().id(sessionUserId).build())
                    .build();
        }
    }

}
