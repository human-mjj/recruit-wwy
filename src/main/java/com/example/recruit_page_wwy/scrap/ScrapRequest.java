package com.example.recruit_page_wwy.scrap;

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
}
