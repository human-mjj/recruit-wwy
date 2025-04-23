package com.example.recruit_page_wwy.resume;

import java.util.List;

public class ResumeResponse {

    public static class MainDTO {
        private List<Resume> resumes;


        public MainDTO(List<Resume> resumes) {
            this.resumes = resumes;
            
        }

    }
}
