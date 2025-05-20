package com.example.recruit_page_wwy.integre.employment;

import com.example.recruit_page_wwy._core.util.JwtUtil;
import com.example.recruit_page_wwy.employment.EmploymentRequest;
import com.example.recruit_page_wwy.user.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.Arrays;

import static org.hamcrest.Matchers.containsString;

@Transactional
@AutoConfigureMockMvc
@SpringBootTest
public class EmploymentControllerTest {

    @Autowired
    private ObjectMapper om = new ObjectMapper();

    @Autowired
    private MockMvc mvc;

    private String accessToken;

    @BeforeEach
    public void setUp() {
        System.out.println("setUp");
        User user = User.builder()
                .id(6)
                .username("문정준")
                .email("a32176740@gmail.com")
                .role(1)
                .build();
        accessToken = JwtUtil.create(user);
        // System.out.println(user.getRole());
    }

    @AfterEach
    public void tearDown() {
        System.out.println();
        System.out.println("tearDown");
    }

    @Test
    public void employment_save_test() throws Exception {

        // given
        EmploymentRequest.SaveDTO reqDTO = new EmploymentRequest.SaveDTO();
        reqDTO.setTitle("Spring 백엔드 개발자 모집");
        reqDTO.setExp("10년 이상");
        reqDTO.setEdu("대학교 / 졸업");
        reqDTO.setShift("정규직");
        reqDTO.setJobId(3);
        reqDTO.setDuty(Arrays.asList(
                "REST API 개발 및 유지보수",
                "기존 서비스 코드 리팩토링",
                "MySQL 기반 데이터 처리",
                "협업을 위한 문서화"
        ));
        reqDTO.setQualification(Arrays.asList(
                "Java/Spring 경력 1년 이상",
                "MySQL 경험 우대",
                "협업 및 커뮤니케이션 능력",
                "Git 사용 가능자"
        ));
        reqDTO.setEmployStack(Arrays.asList("Spring", "Java", "MySQL"));
        reqDTO.setSal(4200);
        reqDTO.setWorkingTime("09:30 ~ 18:30");
        reqDTO.setLocation("서울특별시");
        reqDTO.setSpecificLocation("강남구");
        reqDTO.setEndDate(Date.valueOf("2025-05-31"));
        reqDTO.setImgUrl(null);

        String requestBody = om.writeValueAsString(reqDTO);

        // System.out.println(requestBody);

        // when
        ResultActions actions = mvc.perform(
                MockMvcRequestBuilders
                        .post("/s/api/employment")
                        .content(requestBody)
                        .header("Authorization", "Bearer " + accessToken)
                        .contentType(MediaType.APPLICATION_JSON)
        );
        System.out.println("accessToken = " + accessToken);

        // eye
        String responseBody = actions.andReturn().getResponse().getContentAsString();
        System.out.println(responseBody);

        // then
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.status").value(200));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.msg").value("성공"));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.body.title").value("Spring 백엔드 개발자 모집"));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.body.exp").value("10년 이상"));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.body.edu").value("대학교 / 졸업"));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.body.shift").value("정규직"));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.body.jobName").value("풀스택 개발자"));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.body.sal").value(4200));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.body.workingTime").value("09:30 ~ 18:30"));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.body.location").value("서울특별시 강남구"));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.body.endDate").value("2025-05-31"));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.body.duty").value("REST API 개발 및 유지보수$기존 서비스 코드 리팩토링$MySQL 기반 데이터 처리$협업을 위한 문서화"));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.body.qualification").value("Java/Spring 경력 1년 이상$MySQL 경험 우대$협업 및 커뮤니케이션 능력$Git 사용 가능자"));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.body.employStackList[0].skill").value("Spring"));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.body.employStackList[1].skill").value("Java"));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.body.employStackList[2].skill").value("MySQL"));
    }

    @Test
    public void index_test() throws Exception {
        // when
        ResultActions actions = mvc.perform(
                MockMvcRequestBuilders
                        .get("/")  // GET 방식으로 수정
                        .header("Authorization", "Bearer " + accessToken)
        );

        // eye
        String responseBody = actions.andReturn().getResponse().getContentAsString();
        System.out.println(responseBody);

        // then
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.status").value(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$.msg").value("성공"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.body.isCompanyUser").value(false))

                // 첫 번째 항목 검증
                .andExpect(MockMvcResultMatchers.jsonPath("$.body.employmentList[0].id").value(32))
                .andExpect(MockMvcResultMatchers.jsonPath("$.body.employmentList[0].title").value("보안 엔지니어 팀원 채용합니다"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.body.employmentList[0].companyName").value("에이펙스 테크놀로지"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.body.employmentList[0].exp").value("1 ~ 3년 차"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.body.employmentList[0].location").value("서울특별시 노원구"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.body.employmentList[0].imgUrl").doesNotExist()); // null이면 이 방식
    }

    @Test
    public void manage_employment_test() throws Exception {
        // when
        ResultActions actions = mvc.perform(
                MockMvcRequestBuilders
                        .get("/s/api/mypage/employment")
                        .header("Authorization", "Bearer " + accessToken)
                        .contentType(MediaType.APPLICATION_JSON)
        );

        // eye
        String responseBody = actions.andReturn().getResponse().getContentAsString();
        System.out.println(responseBody);

        // then
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.status").value(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$.msg").value("성공"))

                // 첫 번째 employments 항목 검증
                .andExpect(MockMvcResultMatchers.jsonPath("$.body.employments[0].id").value(14))
                .andExpect(MockMvcResultMatchers.jsonPath("$.body.employments[0].title").value("보안 엔지니어 팀원 채용합니다"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.body.employments[0].comName").value("WWY"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.body.employments[0].exp").value("10년 이상"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.body.employments[0].location").value("서울특별시 은평구"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.body.employments[0].jobName").value("보안 엔지니어"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.body.employments[0].imgUrl").value("/img/job_dummy.jpg"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.body.employments[0].thereImg").value(false))
                .andExpect(MockMvcResultMatchers.jsonPath("$.body.employments[0].companyUser").value(true));
    }

    @Test
    public void list_test() throws Exception {
        // when
        ResultActions actions = mvc.perform(
                MockMvcRequestBuilders
                        .get("/api/employment")
                        .header("Authorization", "Bearer " + accessToken)
                        .contentType(MediaType.APPLICATION_JSON)
        );
        System.out.println("accessToken = " + accessToken);

        // eye
        String responseBody = actions.andReturn().getResponse().getContentAsString();
        System.out.println("list_test 응답 = " + responseBody);

        // then
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.status").value(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$.msg").value("성공"))

                // 첫 번째 employments 항목만 검증
                .andExpect(MockMvcResultMatchers.jsonPath("$.body.employments[0].id").value(87))
                .andExpect(MockMvcResultMatchers.jsonPath("$.body.employments[0].scrapCount").value(0))
                .andExpect(MockMvcResultMatchers.jsonPath("$.body.employments[0].title").value("데이터 분석가 팀원 채용합니다"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.body.employments[0].comName").value("그림솔루션"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.body.employments[0].exp").value("3 ~ 5년 차"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.body.employments[0].location").value("서울특별시 강서구"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.body.employments[0].jobName").value("데이터 분석가"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.body.employments[0].imgUrl").value("/img/job_dummy.jpg"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.body.employments[0].thereImg").value(false));
    }

    @Test
    public void update_view_test() throws Exception {
        // when
        ResultActions actions = mvc.perform(
                MockMvcRequestBuilders
                        .get("/s/api/employment/2")
                        .header("Authorization", "Bearer " + accessToken)
                        .contentType(MediaType.APPLICATION_JSON)
        );

        System.out.println("accessToken = " + accessToken);

        // eye
        String responseBody = actions.andReturn().getResponse().getContentAsString();
        System.out.println(responseBody);

        // then
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.status").value(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$.msg").value("성공"))

                // 기본 필드
                .andExpect(MockMvcResultMatchers.jsonPath("$.body.id").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$.body.title").value("프론트엔드 React 개발자 채용"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.body.selectedJobId").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$.body.workingTime").value("10:00 ~ 19:00"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.body.sal").value(3800))
                .andExpect(MockMvcResultMatchers.jsonPath("$.body.shift").value("정규직"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.body.location").value("서울특별시"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.body.specificLocation").value("마포구"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.body.endDate").value("2025-06-15"))

                // selectedStacks
                .andExpect(MockMvcResultMatchers.jsonPath("$.body.selectedStacks[0]").value("mysql"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.body.selectedStacks[1]").value("html"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.body.selectedStacks[2]").value("node.js"))

                // stackList 중 체크된 항목
                .andExpect(MockMvcResultMatchers.jsonPath("$.body.stackList[2].stackChecked").value(true))
                .andExpect(MockMvcResultMatchers.jsonPath("$.body.stackList[5].stackChecked").value(true))
                .andExpect(MockMvcResultMatchers.jsonPath("$.body.stackList[7].stackChecked").value(true))

                // 직무 선택 확인
                .andExpect(MockMvcResultMatchers.jsonPath("$.body.jobList[1].name").value("프론트엔드 개발자"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.body.jobList[1].jobSelected").value(true))

                // dutyList
                .andExpect(MockMvcResultMatchers.jsonPath("$.body.dutyList[0]").value("웹 서비스 프론트 개발"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.body.dutyList[1]").value("반응형 UI 구현"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.body.dutyList[2]").value("API 연동 작업"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.body.dutyList[3]").value("컴포넌트 단위 개발 및 유지보수"))

                // qualificationList
                .andExpect(MockMvcResultMatchers.jsonPath("$.body.qualificationList[0]").value("React 사용 가능자"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.body.qualificationList[1]").value("포트폴리오 필수"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.body.qualificationList[2]").value("HTML/CSS/JS 기본 지식"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.body.qualificationList[3]").value("Git 사용 가능자"))

                // ✅ 지역 플래그: 현재 지역이 서울이므로 seoul = true 만 확인
                .andExpect(MockMvcResultMatchers.jsonPath("$.body.seoul").value(true));
    }


    @Test
    public void update_test() throws Exception {

        // given
        EmploymentRequest.SaveDTO reqDTO = new EmploymentRequest.SaveDTO();
        reqDTO.setTitle("Spring 백엔드 개발자 모집");
        reqDTO.setExp("10년 이상");
        reqDTO.setEdu("대학교 / 졸업");
        reqDTO.setShift("정규직");
        reqDTO.setJobId(3);
        reqDTO.setDuty(Arrays.asList(
                "REST API 개발 및 유지보수",
                "기존 서비스 코드 리팩토링",
                "MySQL 기반 데이터 처리",
                "협업을 위한 문서화"
        ));
        reqDTO.setQualification(Arrays.asList(
                "Java/Spring 경력 1년 이상",
                "MySQL 경험 우대",
                "협업 및 커뮤니케이션 능력",
                "Git 사용 가능자"
        ));
        reqDTO.setEmployStack(Arrays.asList("Spring", "Java", "MySQL"));
        reqDTO.setSal(4200);
        reqDTO.setWorkingTime("09:30 ~ 18:30");
        reqDTO.setLocation("서울특별시");
        reqDTO.setSpecificLocation("강남구");
        reqDTO.setEndDate(Date.valueOf("2025-05-31"));
        reqDTO.setImgUrl(null);

        String requestBody = om.writeValueAsString(reqDTO);

        // System.out.println(requestBody);

        // when
        ResultActions actions = mvc.perform(
                MockMvcRequestBuilders
                        .put("/s/api/employment/2")
                        .content(requestBody)
                        .header("Authorization", "Bearer " + accessToken)
                        .contentType(MediaType.APPLICATION_JSON)
        );
        System.out.println("accessToken = " + accessToken);

        // eye
        String responseBody = actions.andReturn().getResponse().getContentAsString();
        System.out.println(responseBody);

        // then
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.status").value(200));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.msg").value("성공"));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.body.title").value("Spring 백엔드 개발자 모집"));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.body.exp").value("10년 이상"));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.body.edu").value(containsString("대학교 / 졸업")));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.body.shift").value("정규직"));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.body.jobName").value("풀스택 개발자"));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.body.sal").value(4200));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.body.workingTime").value("09:30 ~ 18:30"));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.body.location").value("서울특별시 강남구"));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.body.endDate").value("2025-05-31"));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.body.duty").value("REST API 개발 및 유지보수$기존 서비스 코드 리팩토링$MySQL 기반 데이터 처리$협업을 위한 문서화"));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.body.qualification").value("Java/Spring 경력 1년 이상$MySQL 경험 우대$협업 및 커뮤니케이션 능력$Git 사용 가능자"));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.body.employStackList[0].skill").value("Spring"));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.body.employStackList[1].skill").value("Java"));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.body.employStackList[2].skill").value("MySQL"));
    }

    @Test
    public void delete_test() throws Exception {
        // when
        ResultActions actions = mvc.perform(
                MockMvcRequestBuilders
                        .delete("/s/api/employment/2")
                        .header("Authorization", "Bearer " + accessToken)
                        .contentType(MediaType.APPLICATION_JSON)
        );
        System.out.println("accessToken = " + accessToken);

        // eye
        String responseBody = actions.andReturn().getResponse().getContentAsString();
        System.out.println(responseBody);

        // then
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.status").value(200));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.msg").value("성공"));
    }

}
