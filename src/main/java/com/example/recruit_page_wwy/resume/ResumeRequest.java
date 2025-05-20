package com.example.recruit_page_wwy.resume;

import com.example.recruit_page_wwy.job.Job;
import com.example.recruit_page_wwy.user.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public class ResumeRequest {

    @Data
    public static class SaveDTO {
        private Integer user_id;

        @NotBlank(message = "제목은 비어 있을 수 없습니다.")
        @Size(max = 50, message = "제목은 50자 이내여야 합니다.")
        @Pattern(regexp = "^[^<>=]*$", message = "제목에 <, >, = 문자를 포함할 수 없습니다.")
        private String title;

        // exp
        @NotBlank(message = "개인 이력은 비어 있을 수 없습니다.")
        @Pattern(regexp = "^(신입|경력)$", message = "개인 이력은 '신입' 또는 '경력'만 입력할 수 있습니다.")
        private String personalHistory;    // "inexperienced" 또는 "experienced"
        @NotBlank(message = "상세 이력은 비어 있을 수 없습니다.")
        @Size(max = 50, message = "상세 이력은 30자 이내여야 합니다.")
        private String experiencedDate;    // "1" ~ "5"

        // edu
        @NotBlank(message = "학력 구분은 비어 있을 수 없습니다.")
        @Size(max = 20, message = "학력 구분은 20자 이내여야 합니다.")
        @Pattern(regexp = "^[가-힣a-zA-Z0-9/ ]*$", message = "학력 구분은 한글, 영문, 숫자만 허용됩니다.")
        private String educationLevel;     // "univ_graduated" 등
        @NotBlank(message = "학교명은 비어 있을 수 없습니다.")
        @Size(max = 50, message = "학교명은 50자 이내여야 합니다.")
        @Pattern(regexp = "^[가-힣a-zA-Z0-9&]*$", message = "학교명은 한글, 영문, 숫자, &만 허용되며 공백은 허용되지 않습니다.")
        private String schoolName;

        private Integer jobId;

        // location
        @NotBlank(message = "지역명은 비어 있을 수 없습니다.")
        @Size(max = 30, message = "지역명은 30자 이내여야 합니다.")
        @Pattern(regexp = "^[가-힣]+$", message = "지역명은 한글만 입력할 수 있습니다.")
        private String region;
        @NotBlank(message = "상세 지역은 비어 있을 수 없습니다.")
        @Size(max = 100, message = "상세 지역은 100자 이내여야 합니다.")
        @Pattern(regexp = "^[가-힣0-9]+$", message = "상세 지역은 한글과 숫자만 입력할 수 있습니다.")
        private String regionDetail;

        @Size(max = 50, message = "자격사항은 50자 이내여야 합니다.")
        @Pattern(regexp = "^[^\\s].*\\S.*$", message = "자격사항은 앞뒤 공백 없이 최소 한 글자 이상 입력되어야 합니다.")
        private String qualified;

        @Size(max = 50, message = "활동 내용은 50자 이내여야 합니다.")
        @Pattern(regexp = "^[가-힣a-zA-Z0-9\\s]*$", message = "활동 내용은 한글, 영문, 숫자만 허용되며 <, >, = 는 사용 불가합니다.")
        private String activity;

        private MultipartFile uploadingImg;
        private String imgUrl;

        @NotBlank(message = "자기소개서는 비어 있을 수 없습니다.")
        @Size(max = 1000, message = "자기소개서는 1000자 이내여야 합니다.")
        @Pattern(regexp = "^[^<>=]{1,1000}$", message = "자기소개서에 <, >, = 문자를 포함할 수 없습니다.")
        private String letter;

        private List<String> skills;

        public Resume toEntity(User user, String savedImgUrl) {
            String exp = personalHistory + " " + experiencedDate;
            String edu = educationLevel + " $" + schoolName;
            String location = region + " " + regionDetail;

            return Resume.builder()
                    .title(title)
                    .exp(exp)
                    .edu(edu)
                    .location(location)
                    .qualified(qualified == null ? "없음" : qualified)
                    .activity(activity == null ? "없음" : activity)
                    .letter(letter)
                    .imgUrl(savedImgUrl)
                    .job(Job.builder().id(jobId).build())
                    .user(user)
                    .build();
        }
    }

    @Data
    public static class PageRequestDTO {
        private Integer page;


        public PageRequestDTO(int page) {
            this.page = page;
        }
    }

}


