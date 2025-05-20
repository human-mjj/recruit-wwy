package com.example.recruit_page_wwy.proposal;

import lombok.Data;

public class ProposalResponse {
    @Data
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
