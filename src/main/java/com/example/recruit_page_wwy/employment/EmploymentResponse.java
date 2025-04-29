package com.example.recruit_page_wwy.employment;

import com.example.recruit_page_wwy.employstack.EmployStack;
import com.example.recruit_page_wwy.job.Job;
import com.example.recruit_page_wwy.resume.Resume;
import com.example.recruit_page_wwy.stack.Stack;
import com.example.recruit_page_wwy.user.User;
import lombok.Builder;
import lombok.Data;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class EmploymentResponse {

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
    public static class EmploymentPageDTO {
        private List<PublicListDTO> employments;
        private Integer prev;
        private Integer next;

        private Integer size;
        private Integer totalCount;
        private Integer totalPage;
        private Integer current;
        private Boolean isFirst;
        private Boolean isLast;
        private List<Integer> numbers;

        public EmploymentPageDTO(List<EmploymentResponse.PublicListDTO> employments, Integer current, Integer totalCount) {
            this.size = 16;
            this.employments = employments;
            this.totalCount = totalCount;
            this.totalPage = makeTotalPage(totalCount, size);
            this.current = current;
            this.prev = current <= 0 ? 1 : current;
            this.next = current + 2;

            this.isFirst = current == 0;
            this.isLast = current == totalPage - 1;
            this.numbers = makeNumbers(current, totalPage);
        }

        private Integer makeTotalPage(int totalCount, int size) {
            int rest = totalCount % size > 0 ? 1 : 0;
            return totalCount / size + rest;
        }

        private List<Integer> makeNumbers(int current, int totalPage) {
            List<Integer> numbers = new ArrayList<>();

            int start = (current / 5) * 5;
            int end = Math.min(start + 5, totalPage);

            for (int i = start; i < end; i++) {
                numbers.add(i + 1); // 버튼 1부터 보이게 처리된 곳
            }

            return numbers;
        }
    }

    @Data
    public static class ListDTO {
        private Integer id;
        private String title;
        private String comName;
        private String exp;
        private String location;
        private String jobName;
        private String imgUrl;
        private boolean isThereImg;

        private Boolean isCompanyUser; // 스크랩 버튼 노출 여부 (false면 보여줌)


        public ListDTO(Employment e, User sessionUser) {
            this.id = e.getId();
            this.title = e.getTitle();
            this.comName = e.getUser().getComName();
            this.exp = e.getExp();
            this.location = e.getLocation();
            this.jobName = e.getJob().getName();
            this.isThereImg = e.getImgUrl() != null;
            this.imgUrl = isThereImg ? e.getImgUrl() : "/img/job_dummy.jpg";
            this.isCompanyUser = sessionUser != null && sessionUser.getRole() == 1;
        }
    }

    @Data
    public static class PublicListDTO {
        private Integer id;
        private String title;
        private String comName;
        private String exp;
        private String location;
        private String jobName;
        private String imgUrl;
        private boolean isThereImg;

        private Boolean isCompanyUser; // 스크랩 버튼 노출 여부 (false면 보여줌)


        public PublicListDTO(Employment e, User sessionUser) {
            this.id = e.getId();
            this.title = e.getTitle();
            this.comName = e.getUser().getComName();
            this.exp = e.getExp();
            this.location = e.getLocation();
            this.jobName = e.getJob().getName();
            this.isThereImg = e.getImgUrl() != null;
            this.imgUrl = isThereImg ? e.getImgUrl() : "/img/job_dummy.jpg";

            this.isCompanyUser = sessionUser != null && sessionUser.getRole() == 1;
        }
    }

    @Data
    public static class DetailDTO {
        private Integer sessionUserId;
        private Integer sessionUserRole;
        private Boolean isOwner; // 수정/삭제 버튼 표시용
        private Boolean isApplicant; // 지원자 여부
        private Boolean isCompanyUser; // 스크랩 버튼 노출 여부 (false면 보여줌)

        private Integer id;
        private String userImgUrl;
        private String title;
        private String comName;
        private String exp;
        private String edu;
        private String shift;
        private Integer sal;
        private String workingTime;
        private String location;
        private Date endDate;
        private List<String> duty;
        private List<String> qualification;
        private String jobName;
        private List<String> stack;
        private String stackStr;
        private List<ResumeDTO> resumeList;

        private Boolean isScrap;
        private Integer scrapId;

        @Data
        public static class ResumeDTO {
            private Integer id;
            private String title;

            public ResumeDTO(Resume resume) {
                this.id = resume.getId();
                this.title = resume.getTitle();
            }
        }

        // ✅ 메인 생성자 (sessionUser와 employment를 받아서 처리)
        public DetailDTO(User sessionUser, Employment employment, List<ResumeDTO> resumeList, List<String> stackList, boolean isScrap, Integer scrapId) {
            this.sessionUserId = sessionUser != null ? sessionUser.getId() : null;
            this.sessionUserRole = sessionUser != null ? sessionUser.getRole() : null;
            this.isOwner = sessionUser != null && sessionUser.getId() == employment.getUser().getId();
            this.isCompanyUser = sessionUser != null && sessionUser.getRole() == 1;
            this.isApplicant = false; // 기본 false (지원 여부 체크는 별도로)

            this.id = employment.getId();
            this.userImgUrl = employment.getUser().getImgUrl();
            this.title = employment.getTitle();
            this.comName = employment.getUser().getComName();
            this.exp = parseExp(employment.getExp());
            this.edu = parseEdu(employment.getEdu());
            this.shift = employment.getShift();
            this.sal = employment.getSal();
            this.workingTime = employment.getWorkingTime();
            this.location = employment.getLocation();
            this.endDate = employment.getEndDate();
            this.duty = parseList(employment.getDuty());
            this.qualification = parseList(employment.getQualification());
            this.jobName = employment.getJob().getName();
            this.stack = stackList;
            this.stackStr = String.join(", ", stackList);
            this.resumeList = resumeList;
            this.isScrap = isScrap;
            this.scrapId = scrapId == null ? 0 : scrapId;
        }

        // ✅ 파싱 함수 추가
        private List<String> parseList(String rawString) {
            if (rawString == null || rawString.isBlank()) return List.of();
            return List.of(rawString.split("\\$"));
        }

        private String parseExp(String rawExp) {
            if (rawExp == null) return "";
            if (rawExp.contains("$")) {
                return rawExp.split("\\$")[0]; // 경력 항목은 '신입$1년차' 처럼 되어있음
            }
            return rawExp;
        }

        private String parseEdu(String rawEdu) {
            if (rawEdu == null) return "";
            if (rawEdu.contains("$")) {
                return rawEdu.split("\\$")[0]; // 학력 항목도 '대학교졸업$고려대학교' 처럼 되어있음
            }
            return rawEdu;
        }
    }

    @Data
    public static class UpdateViewDTO {
        private int id;

        // ===== 기본 필드 =====
        private String title;

        private List<JobDTO> jobList;
        private Integer selectedJobId;

        private List<StackDTO> stackList;
        private List<String> selectedStacks;

        private List<String> dutyList;
        private List<String> qualificationList;

        private String workingTime;
        private Integer sal;
        private String shift;

        private String exp;
        private String expYear;

        private String edu;
        private String schoolName;

        private String endDate;

        private String location;
        private String specificLocation;

        // ===== 선택 플래그 =====
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

        // ===== 내부 클래스 =====
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

        // ===== Builder =====
        @Builder
        public UpdateViewDTO(Employment employment, List<JobDTO> jobList, List<StackDTO> stackList, List<EmployStack> selectedStacks) {
            this.id = employment.getId();
            this.title = employment.getTitle();
            this.workingTime = employment.getWorkingTime();
            this.sal = employment.getSal();
            this.shift = employment.getShift();
            this.endDate = employment.getEndDate() != null ? employment.getEndDate().toString() : null; // Date를 String 변환

            String[] locationParts = parseLocation(employment.getLocation());
            this.location = locationParts[0];
            this.specificLocation = locationParts[1];

            // === exp 파싱
            String[] expParts = parseExp(employment.getExp());
            this.exp = expParts[0];
            this.expYear = expParts[1];

            // === edu 파싱
            String[] eduParts = parseEdu(employment.getEdu());
            this.edu = eduParts[0];
            this.schoolName = eduParts[1];

            // === 주요업무, 자격요건
            this.dutyList = parseDutyOrQualification(employment.getDuty());
            this.qualificationList = parseDutyOrQualification(employment.getQualification());

            // === 직무 선택
            this.jobList = jobList;
            this.selectedJobId = employment.getJob() != null ? employment.getJob().getId() : null;

            if (jobList != null) {
                for (JobDTO job : jobList) {
                    if (job.getId() != null && job.getId().equals(selectedJobId)) {
                        job.setJobSelected(true);
                    }
                }
            }

            // === 스택 선택
            this.stackList = stackList;
            this.selectedStacks = toSelectedStackNames(selectedStacks);
            if (stackList != null && selectedStacks != null) {
                for (StackDTO stack : stackList) {
                    if (selectedStacks.contains(stack.getSkill())) {
                        stack.setStackChecked(true);
                    }
                }
            }

            // === 신입 여부
            this.isNewbie = "신입".equals(this.exp);

            // === 경력 연차 플래그
            if (!this.isNewbie) {
                this.isJunior = "1 ~ 3년 차".equals(this.expYear);
                this.isSenior = "3 ~ 5년 차".equals(this.expYear);
                this.isPrincipal = "5 ~ 7년 차".equals(this.expYear);
                this.isManager = "7 ~ 9년 차".equals(this.expYear);
                this.isMaster = "10년 이상".equals(this.expYear);
            }

            // === 학력 플래그
            this.isUnivGraduated = this.edu != null && this.edu.contains("대학교 / 졸업");
            this.isUnivEnrolled = this.edu != null && this.edu.contains("대학교 / 재학");
            this.isHighGraduated = this.edu != null && this.edu.contains("고등학교 / 졸업");

            // === 지역 플래그
            this.isSeoul = "서울특별시".equals(location);
            this.isBusan = "부산광역시".equals(location);
            this.isDaegu = "대구광역시".equals(location);
            this.isIncheon = "인천광역시".equals(location);
            this.isGwangju = "광주광역시".equals(location);
            this.isDaejeon = "대전광역시".equals(location);
            this.isUlsan = "울산광역시".equals(location);
            this.isSejong = "세종특별자치시".equals(location);
            this.isGyeonggi = "경기도".equals(location);
            this.isGangwon = "강원특별자치도".equals(location);
            this.isChungbuk = "충청북도".equals(location);
            this.isChungnam = "충청남도".equals(location);
            this.isJeonbuk = "전라북도".equals(location);
            this.isJeonnam = "전라남도".equals(location);
            this.isGyeongbuk = "경상북도".equals(location);
            this.isGyeongnam = "경상남도".equals(location);
            this.isJeju = "제주특별자치도".equals(location);
        }

        // ===== 파싱 메서드 =====
        private static String[] parseLocation(String dbLocation) {
            if (dbLocation == null || dbLocation.isBlank()) {
                return new String[]{"", ""};
            }
            String[] parts = dbLocation.split("\\s+", 2); // 공백 1개 이상 기준으로 최대 2개로 나눈다
            if (parts.length == 1) {
                return new String[]{parts[0], ""}; // 시/도만 있을 경우
            }
            return parts; // 시/도 + 시/군/구
        }

        private static String[] parseEdu(String dbEdu) {
            if (dbEdu == null || !dbEdu.contains("$")) return new String[]{"", ""};
            return dbEdu.split("\\$", 2);
        }

        private static String[] parseExp(String dbExp) {
            if (dbExp == null || !dbExp.contains("$")) return new String[]{"", ""};
            return dbExp.split("\\$", 2);
        }

        private static List<String> parseDutyOrQualification(String str) {
            if (str == null || str.isEmpty()) {
                return List.of("", "", "");
            }
            List<String> parts = List.of(str.split("\\$"));
            while (parts.size() < 3) {
                parts.add("");
            }
            return parts;
        }

        private List<String> toSelectedStackNames(List<EmployStack> selectedStackList) {
            List<String> selectedStacks = new ArrayList<>();
            if (selectedStackList != null) {
                for (EmployStack es : selectedStackList) {
                    if (es.getSkill() != null) {
                        selectedStacks.add(es.getSkill());
                    }
                }
            }
            return selectedStacks;
        }
    }
}
