package com.example.recruit_page_wwy.match;


import lombok.Data;

public class MatchResponse {

    @Data
    public static class ResumeDTO {
        private int id;
        private String title;
        private String author;

        public ResumeDTO(int id, String title, String author) {
            this.id = id;
            this.title = title;
            this.author = author;
        }
    }

    @Data
    public static class EmploymentDTO {
        private int id;
        private String title;
        private String comName;
        private String exp;
        private String location;

        public EmploymentDTO(int id, String title, String comName, String exp, String location) {
            this.id = id;
            this.title = title;
            this.comName = comName;
            this.exp = exp;
            this.location = location;
        }
    }
}
