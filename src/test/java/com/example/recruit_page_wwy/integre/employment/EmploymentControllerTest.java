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
    public void employmentSave_test() throws Exception {

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
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.body.job.id").value(3));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.body.sal").value(4200));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.body.workingTime").value("09:30 ~ 18:30"));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.body.location").value("서울특별시 강남구"));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.body.endDate").value("2025-05-31"));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.body.duty").value("REST API 개발 및 유지보수$기존 서비스 코드 리팩토링$MySQL 기반 데이터 처리$협업을 위한 문서화"));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.body.qualification").value("Java/Spring 경력 1년 이상$MySQL 경험 우대$협업 및 커뮤니케이션 능력$Git 사용 가능자"));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.body.employStack").value("Spring$Java$MySQL"));

    }
}
