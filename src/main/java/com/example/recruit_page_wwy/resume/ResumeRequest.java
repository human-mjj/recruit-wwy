package com.example.recruit_page_wwy.resume;

import com.example.recruit_page_wwy.job.Job;
import com.example.recruit_page_wwy.user.User;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public class ResumeRequest {

    @Data
    public static class SaveDTO {
        private Integer user_id;
        private String title;

        private String personalHistory;    // "inexperienced" 또는 "experienced"
        private String experiencedDate;    // "1" ~ "5"

        private String educationLevel;     // "univ_graduated" 등
        private String schoolName;

        private Integer jobId;

        private String region;
        private String regionDetail;

        private String qualified;
        private String activity;

        private MultipartFile uploadingImg;
        private String imgUrl;

        private String letter;

        private List<String> skills;

        public Resume toEntity(User user, String savedImgUrl) {
            String exp = "신입".equals(personalHistory)
                    ? "신입"
                    : "경력 " + experiencedDate;

            String edu = educationLevel + " $" + schoolName;
            String location = region + " " + regionDetail;

            return Resume.builder()
                    .title(title)
                    .exp(exp)
                    .edu(edu)
                    .location(location)
                    .qualified(qualified)
                    .activity(activity)
                    .letter(letter)
                    .imgUrl(savedImgUrl)
                    .job(Job.builder().id(jobId).build())
                    .user(user)
                    .build();
        }
    }
}


