package com.example.recruit_page_wwy.scrap;

import lombok.Data;

public class ScrapResponse {
    @Data
    public static class SaveDTO {
        private Integer scrapId;

        public SaveDTO(Integer scrapId) {
            this.scrapId = scrapId;
        }
    }

    @Data
    public static class DeleteDTO {
        private Integer employmentId;

        public DeleteDTO(Integer employmentId) {
            this.employmentId = employmentId;
        }
    }
}
