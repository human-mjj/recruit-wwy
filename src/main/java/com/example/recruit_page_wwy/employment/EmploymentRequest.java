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
        private String shift;
        private Integer sal;
        private String workingTime;
        private String location;
        private Date endDate;
        private List<String> duty;
        private List<String> qualification;
        private String jobName;
        private List<String> stack;

        public Employment toEntity(User user, Job job) {
            return Employment.builder()
                    .title(title)
                    .exp(exp)
                    .edu(edu)
                    .shift(shift)
                    .sal(sal)
                    .workingTime(workingTime)
                    .location(location)
                    .endDate(endDate)
                    .duty(String.join("$", duty))
                    .qualification(String.join("$", qualification))
                    .job(job)
                    .user(user)
                    .build();
        }
    }
}
