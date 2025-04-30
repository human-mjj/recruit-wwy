package com.example.recruit_page_wwy.resume;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

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

        private String job; // 폼에서 넘어오는 값 (예: "Spring 개발자")
        private Integer job_id;

        private String region;
        private String regionDetail;
        private String location;

        private String qualified;
        private String activity;
        private MultipartFile UploadingImg;
        private String imgUrl;

        private List<String> skills;


        public SaveDTO(Integer user_id, String title, String exp, String edu, String job, String location, String qualified, String activity, String img_url, List<String> skills) {
            this.user_id = user_id;
            this.title = title;
            this.exp = exp;
            this.edu = edu;
            this.job = job;
            this.job_id = convertJobToId(job);
            this.location = location;
            this.qualified = qualified;
            this.activity = activity;
            this.imgUrl = img_url;
            this.skills = skills;
        }


        public String getExp() {
            if ("inexperienced".equals(personalHistory)) {
                return "신입";
            } else {
                return "경력" + getExperienceRange(experiencedDate);
            }

        }


        public String getEdu() {
            return schoolName + convertEducationLabel(educationLevel);
        }

        public String getLocation() {
            return region + regionDetail;
        }

        public String getExperienceRange(String experiencedDate) {
            switch (experiencedDate) {
                case "1":
                    return " 1 ~ 3년 차";
                case "2":
                    return " 3 ~ 5년 차";
                case "3":
                    return " 5 ~ 7년 차";
                case "4":
                    return " 7 ~ 9년 차";
                case "5":
                    return " 10년 이상";
                default:
                    return null;
            }
        }


        public String convertEducationLabel(String educationLevel) {
            switch (educationLevel) {
                case "univ_graduated":
                    return " 대학교 / 졸업";
                case "univ_enrolled":
                    return " 대학교 / 재학";
                case "highschool_graduated":
                    return " 고등학교 / 졸업";
                default:
                    return null;
            }
        }


        public Integer convertJobToId(String job) {
            switch (job) {
                case "웹 풀스택 개발자":
                    return 1;
                case "HTML 개발자":
                    return 2;
                case "CSS 개발자":
                    return 3;
                case "JavaScript 개발자":
                    return 4;
                case "Python 개발자":
                    return 5;
                case "C++ 개발자":
                    return 6;
                case "Spring 개발자":
                    return 7;
                case "React 개발자":
                    return 8;
                case "Node.js 개발자":
                    return 9;
                case "MySQL 개발자":
                    return 10;
                default:
                    return null;
            }
        }


    }

    @Data
    public static class UpdateDTO {
        private Integer id;
        private String title;

        private String personalHistory;
        private String experiencedDate;
        private String exp;

        private String educationLevel;
        private String schoolName;
        private String edu;

        private String job; // 폼에서 넘어오는 값 (예: "Spring 개발자")
        private Integer job_id;

        private String region;
        private String regionDetail;
        private String location;

        private String qualified;
        private String activity;

        private List<String> skills;

        public UpdateDTO(Integer id, String title, String exp, String edu, String job, String location, String qualified, String activity, List<String> skills) {
            this.id = id;
            this.title = title;
            this.exp = exp;
            this.edu = edu;
            this.job = job;
            this.job_id = convertJobToId(job);
            this.location = location;
            this.qualified = qualified;
            this.activity = activity;
            this.skills = skills;
        }


        public String getExp() {
            if ("inexperienced".equals(personalHistory)) {
                return "신입";
            } else {
                return "경력" + getExperienceRange(experiencedDate);
            }

        }


        public String getEdu() {
            return schoolName + convertEducationLabel(educationLevel);
        }


        public String getLocation() {
            return region + regionDetail;
        }

        public String getExperienceRange(String experiencedDate) {
            switch (experiencedDate) {
                case "1":
                    return " 1 ~ 3년 차";
                case "2":
                    return " 3 ~ 5년 차";
                case "3":
                    return " 5 ~ 7년 차";
                case "4":
                    return " 7 ~ 9년 차";
                case "5":
                    return " 10년 이상";
                default:
                    return null;
            }
        }

        public String convertEducationLabel(String educationLevel) {
            switch (educationLevel) {
                case "univ_graduated":
                    return " 대학교 / 졸업";
                case "univ_enrolled":
                    return " 대학교 / 재학";
                case "highschool_graduated":
                    return " 고등학교 / 졸업";
                default:
                    return null;
            }
        }

        public Integer convertJobToId(String job) {
            switch (job) {
                case "웹 풀스택 개발자":
                    return 1;
                case "HTML 개발자":
                    return 2;
                case "CSS 개발자":
                    return 3;
                case "JavaScript 개발자":
                    return 4;
                case "Python 개발자":
                    return 5;
                case "C++ 개발자":
                    return 6;
                case "Spring 개발자":
                    return 7;
                case "React 개발자":
                    return 8;
                case "Node.js 개발자":
                    return 9;
                case "MySQL 개발자":
                    return 10;
                default:
                    return null;
            }
        }

    }
}


