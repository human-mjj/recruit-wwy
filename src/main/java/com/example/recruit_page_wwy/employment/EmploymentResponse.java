package com.example.recruit_page_wwy.employment;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

public class EmploymentResponse {
    private List<EmploymentDTO> data;

    @Data
    @AllArgsConstructor
    public static class EmploymentDTO {
        private String title;
        private String comName;
        private String exp;
        private String location;
        private String jobName;
        private String imgUrl;
    }
}
