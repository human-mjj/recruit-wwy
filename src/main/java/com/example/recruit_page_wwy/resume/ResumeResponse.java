package com.example.recruit_page_wwy.resume;

import com.example.recruit_page_wwy.employment.Employment;
import com.example.recruit_page_wwy.job.Job;
import com.example.recruit_page_wwy.resumestack.ResumeStack;
import com.example.recruit_page_wwy.stack.Stack;
import com.example.recruit_page_wwy.user.User;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ResumeResponse {

    @Data
    public static class DTO {
        private Integer id;
        private String title;
        private Integer userId;
        private String exp;
        private String edu;
        private List<ResumeSummaryDTO> resumeStackList;
        private Integer jobId;
        private String location;
        private String qualified;
        private String activity;
        private String letter;
        private String imgUrl;

        public DTO(Resume resume) {
            this.id = resume.getId();
            this.title = resume.getTitle();
            this.userId = resume.getUser() != null ? resume.getUser().getId() : null;
            this.exp = resume.getExp();
            this.edu = resume.getEdu();
            this.resumeStackList = Optional.ofNullable(resume.getResumeStackList())
                    .orElse(List.of())
                    .stream()
                    .map(ResumeSummaryDTO::new)
                    .collect(Collectors.toList());
            this.jobId = resume.getJob() != null ? resume.getJob().getId() : null;
            this.location = resume.getLocation();
            this.qualified = resume.getQualified();
            this.activity = resume.getActivity();
            this.letter = resume.getLetter();
            this.imgUrl = resume.getImgUrl();
        }
    }

    @Data
    public static class ResumeSummaryDTO {
        private Integer id;
        private String skill;

        public ResumeSummaryDTO(ResumeStack rs) {
            this.id = rs.getId();
            this.skill = rs.getSkill();
        }
    }

    @Data
    public static class MainDTO {
        private List<Resume> resumes;
        private Integer prev;
        private Integer next;
        private Integer size;
        private Integer totalCount;
        private Integer totalPage;
        private Integer current;
        private Boolean isFirst;
        private Boolean isLast;
        private List<Integer> numbers;

        public MainDTO(List<Resume> resumes, Integer current, Integer totalCount) {
            this.resumes = resumes;
            this.size = 5;
            this.totalCount = totalCount;
            this.totalPage = makeTotalPage(totalCount, size);
            this.current = current;
            this.prev = current - 1;
            this.next = current + 1;

            this.isFirst = current == 1;
            this.isLast = current.equals(totalPage) || totalPage == 0;
            this.numbers = makeNumbers(current, totalPage);
        }

        private Integer makeTotalPage(int totalCount, int size) {
            int rest = totalCount % size > 0 ? 1 : 0;
            return totalCount / size + rest;
        }

        private List<Integer> makeNumbers(int current, int totalPage) {
            List<Integer> numbers = new ArrayList<>();
            int start = ((current - 1) / 5) * 5 + 1;
            int end = Math.min(start + 4, totalPage);
            for (int i = start; i <= end; i++) {
                numbers.add(i);
            }
            return numbers;
        }
    }


    @Data
    public static class DetailDTO {
        private Integer sessionUserId;
        private Integer sessionUserRole;
        private Boolean isOwner;
        private Boolean isCompanyUser;
        private Boolean isApplicant;

        private Integer id;
        private String username;
        private String email;
        private String phone;
        private Integer userId;
        private Job job;
        private List<ResumeStack> resumeStack;
        private Integer jobId;
        private String title;
        private String exp;
        private String edu;
        private String location;
        private String qualified;
        private String activity;
        private String imgUrl;
        private String letter;
        private List<EmployDTO> employmentList;

        private Boolean isScrap;
        private Integer scrapId;

        @Data
        public static class EmployDTO {
            private Integer id;
            private String title;

            public EmployDTO(Employment employment) {
                this.id = employment.getId();
                this.title = employment.getTitle();
            }
        }

        public DetailDTO(User sessionUser, Resume resume, List<EmployDTO> employmentList, boolean isScrap, Integer scrapId) {
            this.sessionUserId = sessionUser != null ? sessionUser.getId() : null;
            this.sessionUserRole = sessionUser != null ? sessionUser.getRole() : null;
            this.isOwner = sessionUser != null && sessionUser.getId() == resume.getUser().getId();
            this.isCompanyUser = sessionUser != null && sessionUser.getRole() == 1;
            this.isApplicant = false; // 기본 false (지원 여부 체크는 별도로)

            this.id = resume.getId();
            this.userId = resume.getUser().getId();
            this.job = resume.getJob();
            this.resumeStack = resume.getResumeStackList();
            this.username = resume.getUser().getUsername();
            this.email = resume.getUser().getEmail();
            this.phone = resume.getUser().getPhone();
            this.title = resume.getTitle();
            this.exp = resume.getExp();
            this.edu = resume.getEdu();
            this.location = resume.getLocation();
            this.qualified = resume.getQualified();
            this.activity = resume.getActivity();
            this.imgUrl = resume.getImgUrl();
            this.letter = resume.getLetter();
            this.employmentList = employmentList;
            this.isScrap = isScrap;
            this.scrapId = scrapId == null ? 0 : scrapId;
        }
    }

    @Data
    public static class TableDTO {
        List<Job> jobList;
        List<Stack> stackList;

        public TableDTO(List<Job> jobList, List<Stack> stackList) {
            this.jobList = jobList;
            this.stackList = stackList;
        }
    }

    @Data
    public static class UpdateViewDTO {
        private int id;
        private String title;

        private List<JobDTO> jobList;
        private Integer selectedJobId;

        private List<StackDTO> stackList;
        private List<String> selectedStacks;

        private String exp;
        private String expYear;

        private String edu;
        private String schoolName;

        private String location;
        private String region;
        private String regionDetail;

        private String qualified;
        private String activity;
        private String letter;

        private boolean isNewbie;

        private boolean isJunior;
        private boolean isSenior;
        private boolean isPrincipal;
        private boolean isManager;
        private boolean isMaster;

        private boolean isUnivGraduated;
        private boolean isUnivEnrolled;
        private boolean isHighGraduated;

        private boolean isSeoul;
        private boolean isBusan;
        private boolean isDaegu;
        private boolean isIncheon;
        private boolean isGwangju;
        private boolean isDaejeon;
        private boolean isUlsan;
        private boolean isSejong;
        private boolean isGyeonggi;
        private boolean isGangwon;
        private boolean isChungbuk;
        private boolean isChungnam;
        private boolean isJeonbuk;
        private boolean isJeonnam;
        private boolean isGyeongbuk;
        private boolean isGyeongnam;
        private boolean isJeju;

        @Data
        public static class JobDTO {
            private Integer id;
            private String name;
            private boolean isJobSelected;
        }

        @Data
        public static class StackDTO {
            private String skill;
            private boolean isStackChecked;
        }

        @Builder
        public UpdateViewDTO(Resume resume, List<JobDTO> jobList, List<StackDTO> stackList, List<ResumeStack> selectedStacks) {
            this.id = resume.getId();
            this.title = resume.getTitle();
            this.qualified = resume.getQualified();
            this.activity = resume.getActivity();
            this.letter = resume.getLetter();

            // === 경력 파싱 ===
            String[] expParts = parseExp(resume.getExp());
            this.exp = expParts[0];
            this.expYear = expParts.length > 1 ? expParts[1] : "";
            this.isNewbie = "신입".equals(this.exp);

            if (!this.isNewbie) {
                this.isJunior = "1 ~ 3년 차".equals(this.expYear);
                this.isSenior = "3 ~ 5년 차".equals(this.expYear);
                this.isPrincipal = "5 ~ 7년 차".equals(this.expYear);
                this.isManager = "7 ~ 9년 차".equals(this.expYear);
                this.isMaster = "10년 이상".equals(this.expYear);
            }

            // === 학력 파싱 ===
            String[] eduParts = parseEdu(resume.getEdu());
            this.edu = eduParts[0];
            this.schoolName = eduParts.length > 1 ? eduParts[1].trim() : "";

            this.isUnivGraduated = this.edu.contains("대학교 / 졸업");
            this.isUnivEnrolled = this.edu.contains("대학교 / 재학");
            this.isHighGraduated = this.edu.contains("고등학교 / 졸업");

            // === 직무 ===
            this.jobList = jobList;
            this.selectedJobId = resume.getJob() != null ? resume.getJob().getId() : null;
            if (jobList != null) {
                for (JobDTO job : jobList) {
                    if (job.getId() != null && job.getId().equals(selectedJobId)) {
                        job.setJobSelected(true);
                    }
                }
            }

            // === 기술 스택 ===
            this.stackList = stackList;
            this.selectedStacks = toSelectedStackNames(selectedStacks);
            if (stackList != null && selectedStacks != null) {
                for (StackDTO stack : stackList) {
                    if (selectedStacks.contains(stack.getSkill())) {
                        stack.setStackChecked(true);
                    }
                }
            }

            // === 지역 파싱 ===
            String[] locationParts = resume.getLocation().split(" ", 2);
            this.region = locationParts[0];
            this.regionDetail = locationParts.length > 1 ? locationParts[1] : "";
            this.location = resume.getLocation();

            this.isSeoul = "서울특별시".equals(region);
            this.isBusan = "부산광역시".equals(region);
            this.isDaegu = "대구광역시".equals(region);
            this.isIncheon = "인천광역시".equals(region);
            this.isGwangju = "광주광역시".equals(region);
            this.isDaejeon = "대전광역시".equals(region);
            this.isUlsan = "울산광역시".equals(region);
            this.isSejong = "세종특별자치시".equals(region);
            this.isGyeonggi = "경기도".equals(region);
            this.isGangwon = "강원특별자치도".equals(region);
            this.isChungbuk = "충청북도".equals(region);
            this.isChungnam = "충청남도".equals(region);
            this.isJeonbuk = "전라북도".equals(region);
            this.isJeonnam = "전라남도".equals(region);
            this.isGyeongbuk = "경상북도".equals(region);
            this.isGyeongnam = "경상남도".equals(region);
            this.isJeju = "제주특별자치도".equals(region);
        }

        private static String[] parseEdu(String dbEdu) {
            if (dbEdu == null || !dbEdu.contains("$")) return new String[]{"", ""};
            return dbEdu.split("\\$", 2);
        }

        private static String[] parseExp(String dbExp) {
            if (dbExp == null || !dbExp.contains(" ")) return new String[]{dbExp};
            if (dbExp.startsWith("신입")) return new String[]{"신입"};
            String[] parts = dbExp.split(" ", 2);
            return new String[]{parts[0], parts[1]};
        }

        private static List<String> toSelectedStackNames(List<ResumeStack> stackList) {
            if (stackList == null) return List.of();
            return stackList.stream().map(ResumeStack::getSkill).toList();
        }
    }
}