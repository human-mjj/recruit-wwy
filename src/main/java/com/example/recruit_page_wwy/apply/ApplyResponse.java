package com.example.recruit_page_wwy.apply;


import com.example.recruit_page_wwy.user.User;
import lombok.Data;

import java.util.List;

public class ApplyResponse {

    @Data
    public static class UserApplyDTO {
        //        private User user;
        private String comName;
        private String name;
        private String createdAt;
        private String progress;

        public UserApplyDTO(String comName, String name, String createdAt, String progress) {
//            this.user = user;
            this.comName = comName;
            this.name = name;
            this.createdAt = createdAt;
            this.progress = progress;
        }
    }

    @Data
    public static class ComApplyListDTO {
        private Integer sessionUserId;
        private Boolean isCompanyUser; // 스크랩 버튼 노출 여부 (false면 보여줌)
        private List<ComApplyDTO> comApplys;

        public ComApplyListDTO(User sessionUser, List<ComApplyDTO> comApplys) {
            this.sessionUserId = sessionUser != null ? sessionUser.getId() : null;
            this.isCompanyUser = sessionUser != null && sessionUser.getRole() == 1;
            this.comApplys = comApplys;
        }
    }


    @Data
    public static class ComApplyDTO {


        private Integer id;
        private String title;
        private String username;
        private String name;
        private String createdAt;
        private String progress;

        private boolean isWaiting;
        private boolean isInProgress;
        private boolean isDone;
        private boolean isRejected;

        public ComApplyDTO(Integer id, String title, String username, String name, String createdAt, String progress) {


            this.id = id;
            this.title = title;
            this.username = username;
            this.name = name;
            this.createdAt = createdAt;
            this.progress = progress;

            this.isWaiting = "대기".equals(progress);
            this.isInProgress = "진행 중".equals(progress);
            this.isDone = "완료".equals(progress);
            this.isRejected = "반려".equals(progress);
        }
    }
}
