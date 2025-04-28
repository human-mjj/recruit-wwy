package com.example.recruit_page_wwy.scrap;

import lombok.Data;

public class ScrapResponse {
    @Data
    public static class UserSaveDTO {
        private Integer scrapId;

        public UserSaveDTO(Integer scrapId) {
            this.scrapId = scrapId;
        }
    }

    @Data
    public static class UserDeleteDTO {
        private Integer employmentId;

        public UserDeleteDTO(Integer employmentId) {
            this.employmentId = employmentId;
        }
    }

    @Data
    public static class ComSaveDTO {
        private Integer scrapId;

        public ComSaveDTO(Integer scrapId) {
            this.scrapId = scrapId;
        }
    }

    @Data
    public static class ComDeleteDTO {
        private Integer resumeId;

        public ComDeleteDTO(Integer resumeId) {
            this.resumeId = resumeId;
        }
    }
}
