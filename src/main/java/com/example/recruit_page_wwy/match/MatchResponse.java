package com.example.recruit_page_wwy.match;


import com.example.recruit_page_wwy.user.User;
import lombok.Data;

import java.util.List;

public class MatchResponse {

    @Data
    public static class ResumeListDTO {
        private Integer sessionUserId;
        private Boolean isCompanyUser; // 스크랩 버튼 노출 여부 (false면 보여줌)

        private List<ResumeDTO> resumes;

        public ResumeListDTO(User sessionUser, List<ResumeDTO> resumes) {
            this.sessionUserId = sessionUser != null ? sessionUser.getId() : null;
            this.isCompanyUser = sessionUser != null && sessionUser.getRole() == 1;
            this.resumes = resumes;
        }
    }

    @Data
    public static class ResumeDTO {
        private int id;
        private String title;
        private String author;

        public ResumeDTO(int id, String title, String author) {
            this.id = id;
            this.title = title;
            this.author = author;
        }
    }

    @Data
    public static class EmploymentDTO {
        private int id;
        private String title;
        private String comName;
        private String exp;
        private String location;

        public EmploymentDTO(int id, String title, String comName, String exp, String location) {
            this.id = id;
            this.title = title;
            this.comName = comName;
            this.exp = exp;
            this.location = location;
        }
    }

    @Data
    public static class MatchDTO {
        private boolean isCompanyUser;
        private List<EmploymentDTO> proposalList;
        private List<EmploymentDTO> recommendationList;

        public MatchDTO(List<EmploymentDTO> proposalList, List<EmploymentDTO> recommendationList, User sessionUser) {
            this.proposalList = proposalList;
            this.recommendationList = recommendationList;
            this.isCompanyUser = sessionUser != null && sessionUser.getRole() == 1;
        }
    }
}
