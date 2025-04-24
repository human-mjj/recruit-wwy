package com.example.recruit_page_wwy.proposal;

import com.example.recruit_page_wwy.employment.Employment;
import com.example.recruit_page_wwy.resume.Resume;
import com.example.recruit_page_wwy.user.User;
import lombok.Data;

public class ProposalRequest {

    @Data
    public static class SaveDTO {
        private int resumeId;
        private int employmentId;

        public Proposal toEntity(User sessionUser) {
            return Proposal.builder()
                    .user(sessionUser)
                    .resume(Resume.builder().id(resumeId).build())
                    .employment(Employment.builder().id(employmentId).build())
                    .build();
        }
    }
}
