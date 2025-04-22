package com.example.recruit_page_wwy.resume;

import lombok.Data;

public class ResumeRequest {

    @Data
    public static class SaveDTO {
        private Integer user_id;
        private String title;
        private String personalHistory;
        private String experiencedDate;
        private String exp;


        private String educationLevel;
        private String schoolName;
        private String edu;


        private Integer job_id;
        private String region;
        private String regionDetail;
        private String location;
        private String qualified;
        private String activity;
        private String img_url;


        public SaveDTO(Integer user_id, String title, String exp, Integer job_id, String qualified, String activity, String img_url) {
            this.user_id = user_id;
            this.title = title;
            this.exp = exp;
            this.edu = edu(educationLevel, schoolName);
            this.job_id = job_id;
            this.location = location(region, regionDetail);
            this.qualified = qualified;
            this.activity = activity;
            this.img_url = img_url;
        }

        public String exp(String personalHistory) {
            if (personalHistory == "inexperienced") {
                return exp = personalHistory;
            } else {
                return exp = personalHistory + experiencedDate
                        ;
            }

        }


        public String edu(String educationLevel, String schoolName) {
            if (schoolName != null && !schoolName.isBlank()) {
                return educationLevel != null && !educationLevel.isBlank()
                        ? educationLevel + " " + schoolName
                        : schoolName;
            } else {
                return educationLevel != null ? educationLevel : "";
            }

        }

        public String location(String region, String regionDetail) {
            if (regionDetail != null && !regionDetail.isBlank()) {
                return region != null && !region.isBlank()
                        ? region + " " + regionDetail
                        : regionDetail;
            } else {
                return region != null ? region : "";
            }

        }


    }


}
