package com.example.recruit_page_wwy.employment;

import lombok.Data;

public class EmploymentResponse {
    @Data
    public static class ListDTO {
        private Integer id;
        private String title;
        private String comName;
        private String exp;
        private String location;
        private String jobName;
        private String imgUrl;

        public ListDTO(Employment e) {
            this.id = e.getId();
            this.title = e.getTitle();
            this.comName = e.getUser().getComName();
            this.exp = e.getExp();
            this.location = e.getLocation();
            this.jobName = e.getJob().getName();
            this.imgUrl = e.getImgUrl();
        }
    }

    @Data
    public static class PublicListDTO {
        private Integer id;
        private String title;
        private String comName;
        private String exp;
        private String location;
        private String jobName;
        private String imgUrl;

        public PublicListDTO(Employment e) {
            this.id = e.getId();
            this.title = e.getTitle();
            this.comName = e.getUser().getComName();
            this.exp = e.getExp();
            this.location = e.getLocation();
            this.jobName = e.getJob().getName();
            this.imgUrl = e.getImgUrl();
        }
    }
}
