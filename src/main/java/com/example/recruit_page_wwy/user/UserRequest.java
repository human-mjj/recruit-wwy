package com.example.recruit_page_wwy.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;


public class UserRequest {

//    @Data
//    public static class MyPageDTO {
//        private User sessionUser;
//        private Boolean isCompanyUser;
//
//        public MyPageDTO(User sessionUser) {
//            this.sessionUser = sessionUser;
//            this.isCompanyUser = sessionUser.getRole() == 1 ? true : false;
//        }
//    }

    @Data
    public static class UpdateDTO {
        @Pattern(regexp = "^[가-힣A-Za-z0-9()]+$", message = "한글, 영어, 숫자, 괄호 ( ) 만 사용할 수 있습니다.")
        private String comName;
        @Pattern(regexp = "^0\\d{7,10}$", message = "- 없이 7~10자 사이로 입력해주세요.")
        private String phone;
        @Pattern(regexp = "^[A-Za-z0-9!@#$%^&*]{4,20}$", message = "4~20자 사이의 영문 대소문자, 숫자, 특수문자(!@#$%^&*)만 사용 가능합니다.")
        private String password;
        private Integer industryId;
        //private MultipartFile uploadingImg;
        private String imgUrl;

        public User toEntity(String imgUrl) {
            if (comName != null && !comName.isEmpty() && industryId != null)
                return User.builder()
                        .comName(comName)
                        .industryId(industryId)
                        .role(1)
                        .phone(phone)
                        .password(password)
                        .imgUrl(imgUrl)
                        .build();

            else return User.builder()
                    .phone(phone)
                    .password(password)
                    .imgUrl(imgUrl)
                    .role(0)
                    .build();
        }
    }

    @Data
    public static class UserDTO {
        @NotBlank(message = "이름을 입력해 주세요.") // null 또는 " " 막기
        @Pattern(regexp = "^[가-힣]{2,4}$", message = "이름은 한글 2~4자로 입력해야 합니다.")
        private String username;
        @NotBlank(message = "이메일을 입력해 주세요.")
        @Email(message = "올바른 이메일 형식을 입력하세요.")
        private String email;
        @NotBlank(message = "전화번호를 입력해 주세요.") // null 또는 " " 막기
        @Pattern(regexp = "^0\\d{7,10}$", message = "- 없이 7~10자 사이로 입력해주세요.")
        private String phone;
        @NotBlank(message = "비밀번호를 입력해 주세요.") // null 또는 " " 막기
        @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*])[A-Za-z0-9!@#$%^&*]{4,20}$", message = "4~20자 사이의 영문 대소문자 1개, 숫자 1개, 특수문자(!@#$%^&*) 1개를 포함하여주세요.")
        private String password;
        private int role;

        public User toEntity() {
            return User.builder()
                    .username(username)
                    .email(email)
                    .phone(phone)
                    .password(password)
                    .role(0)
                    .build();
        }
    }

    @Data
    public static class ComDTO {
        @NotBlank(message = "이름을 입력해 주세요.") // null 또는 " " 막기
        @Pattern(regexp = "^[가-힣]{2,4}$", message = "이름은 한글 2~4자로 입력해야 합니다.")
        private String username;
        @NotBlank(message = "이메일을 입력해 주세요.")
        @Email(message = "올바른 이메일 형식을 입력하세요.")
        private String email;
        @NotBlank(message = "전화번호를 입력해 주세요.") // null 또는 " " 막기
        @Pattern(regexp = "^0\\d{7,10}$", message = "- 없이 7~10자 사이로 입력해주세요.")
        private String phone;
        @NotBlank(message = "비밀번호를 입력해 주세요.") // null 또는 " " 막기
        @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*])[A-Za-z0-9!@#$%^&*]{4,20}$", message = "4~20자 사이의 영문 대소문자 1개, 숫자 1개, 특수문자(!@#$%^&*) 1개를 포함하여주세요.")
        private String password;
        private int role;

        @NotBlank(message = "회사명을 입력해 주세요.") // null 또는 " " 막기
        @Pattern(regexp = "^[가-힣A-Za-z0-9()]+$", message = "한글, 영어, 숫자, 괄호 ( ) 만 사용할 수 있습니다.")
        private String comName;
        private Integer industryId;

        public User toEntity() {
            return User.builder()
                    .username(username)
                    .email(email)
                    .phone(phone)
                    .password(password)
                    .role(1)
                    .comName(comName)
                    .industryId(industryId)
                    .build();
        }
    }

    @Data
    public static class LoginDTO {
        @NotBlank(message = "이메일을 입력해 주세요.")
        @Email(message = "올바른 이메일 형식을 입력하세요.")
        private String email;
        @NotBlank(message = "비밀번호를 입력해 주세요.") // null 또는 " " 막기
        private String password;
        private int role;
//        private String rememberMe;
    }
}
