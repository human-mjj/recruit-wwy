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

        public Proposal toEntity(User sessionUser, Resume resume, Employment employment) {
            return Proposal.builder()
                    .user(sessionUser)
                    .resume(resume)
                    .employment(employment)
                    .build();
        }
    }
}
