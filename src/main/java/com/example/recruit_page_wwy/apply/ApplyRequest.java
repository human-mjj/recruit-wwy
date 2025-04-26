package com.example.recruit_page_wwy.apply;

import com.example.recruit_page_wwy.employment.Employment;
import com.example.recruit_page_wwy.resume.Resume;
import com.example.recruit_page_wwy.user.User;
import lombok.Data;


public class ApplyRequest {
    @Data
    public static class SaveDTO {
        private int resumeId;
        private int employmentId;

        public Apply toEntity(User sessionUser, Resume resume, Employment employment) {
            return Apply.builder()
                    .user(sessionUser)
                    .resume(resume)
                    .employment(employment)
                    .progress("대기")
                    .build();
        }
    }
}
