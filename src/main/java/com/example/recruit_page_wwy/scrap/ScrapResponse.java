package com.example.recruit_page_wwy.scrap;

import lombok.Data;

public class ScrapResponse {
    @Data
    public static class SaveDTO {
        private Integer ScrapId;

        public SaveDTO(Integer scrapId) {
            ScrapId = scrapId;
        }
    }
}
