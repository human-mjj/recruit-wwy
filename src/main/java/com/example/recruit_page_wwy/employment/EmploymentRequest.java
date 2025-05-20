package com.example.recruit_page_wwy.employment;

import com.example.recruit_page_wwy.job.Job;
import com.example.recruit_page_wwy.user.User;
import lombok.Data;

import java.sql.Date;
import java.util.List;

public class EmploymentRequest {

    @Data
    public static class SaveDTO {
        private String title;
        private String exp;
        private String edu;
        private String schoolName;
        private String shift;
        private Integer sal;
        private String workingTime;
        private String location;
        private String specificLocation;
        private Date endDate;
        private List<String> duty;
        private List<String> qualification;
        private Integer jobId;
        private List<String> employStack;

        //        private MultipartFile uploadingImg;
        private String imgUrl;

        public Employment toEntity(User sessionUser, String imgUrl) {
            return Employment.builder()
                    .title(title)
                    .exp(exp)
                    .edu(edu)
                    .shift(shift)
                    .sal(sal)
                    .workingTime(workingTime)
                    .location(location + " " + specificLocation)
                    .endDate(endDate)
                    .duty(String.join("$", duty))
                    .qualification(String.join("$", qualification))
                    .job(Job.builder().id(jobId).build())
                    .user(sessionUser)
                    .imgUrl(imgUrl)
                    .build();
        }
    }


}
