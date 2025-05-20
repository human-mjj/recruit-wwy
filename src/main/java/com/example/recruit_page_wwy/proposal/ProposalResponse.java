package com.example.recruit_page_wwy.proposal;

import com.example.recruit_page_wwy.employment.Employment;
import com.example.recruit_page_wwy.resume.Resume;
import com.example.recruit_page_wwy.user.User;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

public class ProposalResponse {
    public static class DTO {
        private int id;
        private Integer userId;
        private Integer resumeId;
        private Integer employmentId;
        private String createdAt;

        public DTO(Proposal proposal) {
            this.id = proposal.getId();
            this.userId = proposal.getUser().getId();
            this.resumeId = proposal.getResume().getId();
            this.employmentId = proposal.getEmployment().getId();
            this.createdAt = proposal.getCreatedAt().toString();
        }
    }
}
