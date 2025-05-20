package com.example.recruit_page_wwy.employment;

import com.example.recruit_page_wwy.job.Job;
import com.example.recruit_page_wwy.user.User;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.sql.Date;
import java.util.List;

public class EmploymentRequest {

    @Data
    public static class SaveDTO {

        @NotBlank(message = "제목 내용은 필수입니다.")
        @Size(max = 50, message = "제목은 50자 이내로 작성해주세요.")
        @Pattern(regexp = "^[^<>=\"']*", message = "제목 내용에 <, >, =, \" 등 특수문자는 사용할 수 없습니다.")
        private String title;

        private String exp;
        private String edu;
        private String schoolName;

        @NotBlank(message = "근무 형태는 필수입니다.")
        @Pattern(regexp = "^[가-힣]+$", message = "근무 형태는 한글만 입력 가능합니다.")
        private String shift;

        @NotNull(message = "급여는 필수입니다.")
        @Min(value = 0, message = "급여는 0 이상이어야 합니다.")
        private Integer sal;

        @NotBlank(message = "근무 시간은 필수입니다.")
        @Pattern(regexp = "^[가-힣0-9~\\-: ]+$", message = "근무 시간에는 한글, 숫자, ~, -, : 만 사용할 수 있습니다.")
        private String workingTime;
        private String location;

        @NotBlank(message = "상세 근무지는 필수입니다.")
        @Pattern(
                regexp = "^[가-힣0-9\\-\\s]{1,50}$",
                message = "상세 근무지는 50자 이내의 한글, 숫자, 공백, - 만 사용할 수 있습니다."
        )
        private String specificLocation;
        private Date endDate;

        @Valid
        @NotEmpty(message = "주요 업무는 1개 이상 입력해야 합니다.")
        @Size(max = 5, message = "주요 업무는 최대 5개까지만 입력할 수 있습니다.")
        private List<@NotBlank(message = "각 항목은 공백일 수 없습니다.")
        @Pattern(
                regexp = "^[^$<>=]{1,50}$",
                message = "각 항목은 50자 이내이며 $, <, >, = 문자를 포함할 수 없습니다."
        ) String> duty;


        @Valid
        @NotEmpty(message = "자격 요건은 1개 이상 입력해야 합니다.")
        @Size(max = 5, message = "자격 요건은 최대 5개까지만 입력할 수 있습니다.")
        private List<@NotBlank(message = "각 항목은 공백일 수 없습니다.")
        @Pattern(
                regexp = "^[^$<>=]{1,50}$",
                message = "각 항목은 50자 이내이며 $, <, >, = 문자를 포함할 수 없습니다."
        ) String> qualification;

        private Integer jobId;
        private List<String> employStack;

        //        private MultipartFile uploadingImg;
        private String imgUrl;

        public Employment toEntity(User sessionUser, String imgUrl) {
            return Employment.builder()
                    .title(title)
                    .exp(exp)
                    .edu(edu)
                    .shift(shift)
                    .sal(sal)
                    .workingTime(workingTime)
                    .location(location + " " + specificLocation)
                    .endDate(endDate)
                    .duty(String.join("$", duty))
                    .qualification(String.join("$", qualification))
                    .job(Job.builder().id(jobId).build())
                    .user(sessionUser)
                    .imgUrl(imgUrl)
                    .build();
        }
    }


}
