package com.example.recruit_page_wwy.integre.resume;

import com.example.recruit_page_wwy._core.util.JwtUtil;
import com.example.recruit_page_wwy.resume.ResumeRequest;
import com.example.recruit_page_wwy.user.User;
import com.example.recruit_page_wwy.util.config.FilterConfigTest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.nullValue;

@Import(FilterConfigTest.class)
@Transactional
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)


public class ResumeControllerTest {

    @Autowired
    private ObjectMapper om;

    @Autowired
    private MockMvc mvc;

    private String accessToken;

    @BeforeEach
    public void setUp() {
        // 테스트 시작 전에 실행할 코드
        System.out.println("setUp");
        User ssar = User.builder()
                .id(1)
                .username("ssar")
                .role(0)
                .build();
        accessToken = JwtUtil.create(ssar);
    }

    @AfterEach
    public void tearDown() { // 끝나고 나서 마무리 함수
        // 테스트 후 정리할 코드
        System.out.println("tearDown");
    }

    @Test
    public void list_test() throws Exception {
        // given


        // when
        ResultActions actions = mvc.perform(
                MockMvcRequestBuilders
                        .get("/s/api/mypage/resume")
                        .param("page", page.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Bearer " + accessToken)
        );

//        // eye
        String responseBody = actions.andReturn().getResponse().getContentAsString();
        User user = JwtUtil.verify(accessToken); // user 먼저 선언
        System.out.println("sessionUser = " + user.getId());
        System.out.println(responseBody);


        // then
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.status").value(200));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.msg").value("성공"));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.body.resumes[0].id").value(6));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.body.resumes[0].title").value("스프링 시큐리티 인증 인가 구축"));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.body.resumes[0].username").value("손영민"));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.body.resumes[0].exp").value("JWT 인증 구현 경험"));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.body.resumes[0].edu").value("부산대학교 컴퓨터공학과 졸업"));
//        actions.andExpect(MockMvcResultMatchers.jsonPath("$.body.resumes[0].jobId").value(1));
//        actions.andExpect(MockMvcResultMatchers.jsonPath("$.body.resumes[0].location").value("대구"));
//        actions.andExpect(MockMvcResultMatchers.jsonPath("$.body.resumes[0].qualified").value("정보보안기사"));
//        actions.andExpect(MockMvcResultMatchers.jsonPath("$.body.resumes[0].activity").value("보안 동아리 활동"));
//        actions.andExpect(MockMvcResultMatchers.jsonPath("$.body.resumes[0].img_url").value(nullValue()));
//        actions.andExpect(MockMvcResultMatchers.jsonPath("$.body.resumes[0].letter").value("기술 공유 세션을 자발적으로 열어 팀원들과 지식을 나누는 것을 즐깁니다."));

        actions.andExpect(MockMvcResultMatchers.jsonPath("$.body.current").value(1));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.body.totalCount").value(6));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.body.prev").value(0));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.body.next").value(2));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.body.size").value(5));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.body.totalPage").value(2));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.body.isFirst").value(true));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.body.isLast").value(false));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.body.numbers", hasSize(2)));
    }


    @Test
    public void save_test() throws Exception {
        // given
        ResumeRequest.SaveDTO reqDTO = new ResumeRequest.SaveDTO();
        reqDTO.setTitle("title");
        reqDTO.setImgUrl(null);
        reqDTO.setPersonalHistory("경력");
        reqDTO.setExperiencedDate("1 ~ 3년 차");
        reqDTO.setEducationLevel("대학교 / 졸업");
        reqDTO.setSchoolName("부산대학교");
        reqDTO.setJobId(2);
        reqDTO.setSkills(List.of("Java", "mySql"));
        reqDTO.setRegion("부산광역시");
        reqDTO.setRegionDetail("해운대구");
        reqDTO.setQualified("정보처리기사");
        reqDTO.setActivity("멋쟁이사자처럼 10기 활동");
        reqDTO.setLetter("웹 서비스를 만드는 것이 저의 목표입니다.");


        String requestBody = om.writeValueAsString(reqDTO);

        // when
        ResultActions actions = mvc.perform(
                MockMvcRequestBuilders
                        .post("/s/api/resume")
                        .content(requestBody)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Bearer " + accessToken)
        );

        // eye
        String responseBody = actions.andReturn().getResponse().getContentAsString();
        System.out.println(responseBody);


        // then
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.status").value(200));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.msg").value("성공"));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.body.id").value(31));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.body.title").value("title"));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.body.userId").value(1));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.body.exp").value("경력 1 ~ 3년 차"));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.body.edu").value("대학교 / 졸업 $부산대학교"));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.body.jobId").value(2));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.body.location").value("부산광역시 해운대구"));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.body.qualified").value("정보처리기사"));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.body.activity").value("멋쟁이사자처럼 10기 활동"));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.body.img_url").value(nullValue()));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.body.letter").value("웹 서비스를 만드는 것이 저의 목표입니다."));


    }

    @Test
    public void update_test() throws Exception {
        // given
        Integer id = 1;

        ResumeRequest.SaveDTO reqDTO = new ResumeRequest.SaveDTO();
        reqDTO.setTitle("title");
        reqDTO.setImgUrl(null);
        reqDTO.setPersonalHistory("경력");
        reqDTO.setExperiencedDate("1 ~ 3년 차");
        reqDTO.setEducationLevel("대학교 / 졸업");
        reqDTO.setSchoolName("부산대학교");
        reqDTO.setJobId(2);
        reqDTO.setSkills(List.of("Java", "mySql"));
        reqDTO.setRegion("부산광역시");
        reqDTO.setRegionDetail("해운대구");
        reqDTO.setQualified("정보처리기사");
        reqDTO.setActivity("멋쟁이사자처럼 10기 활동");
        reqDTO.setLetter("웹 서비스를 만드는 것이 저의 목표입니다.");


        String requestBody = om.writeValueAsString(reqDTO);

        // when
        ResultActions actions = mvc.perform(
                MockMvcRequestBuilders
                        .put("/s/api/resume/{id}", id)
                        .content(requestBody)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Bearer " + accessToken)
        );

        // eye
        String responseBody = actions.andReturn().getResponse().getContentAsString();
        System.out.println(responseBody);

        // then
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.status").value(200));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.msg").value("성공"));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.body.id").value(1));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.body.title").value("title"));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.body.userId").value(1));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.body.exp").value("경력 1 ~ 3년 차"));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.body.edu").value("대학교 / 졸업 $부산대학교"));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.body.jobId").value(2));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.body.location").value("부산광역시 해운대구"));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.body.qualified").value("정보처리기사"));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.body.activity").value("멋쟁이사자처럼 10기 활동"));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.body.img_url").value(nullValue()));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.body.letter").value("웹 서비스를 만드는 것이 저의 목표입니다."));
    }

    @Test
    public void delete_test() throws Exception {
        // given
        Integer resumeId = 1;


        // when
        ResultActions actions = mvc.perform(
                MockMvcRequestBuilders
                        .delete("/s/api/resume/{id}", resumeId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Bearer " + accessToken)
        );

        // eye
        String responseBody = actions.andReturn().getResponse().getContentAsString();
        System.out.println(responseBody);

        // then
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.status").value(200));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.msg").value("성공"));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.body").value(nullValue()));
    }

    @Test
    public void detail_test() throws Exception {
        // given
        Integer id = 1;

        // when
        ResultActions actions = mvc.perform(
                MockMvcRequestBuilders
                        .get("/api/resume/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Bearer " + accessToken)
        );

//        // eye
        String responseBody = actions.andReturn().getResponse().getContentAsString();
        System.out.println(responseBody);


        // then
//        actions.andExpect(MockMvcResultMatchers.jsonPath("$.status").value(200));
//        actions.andExpect(MockMvcResultMatchers.jsonPath("$.msg").value("성공"));
//        actions.andExpect(MockMvcResultMatchers.jsonPath("$.body.replyList[0].boardId").value(1));
//        actions.andExpect(MockMvcResultMatchers.jsonPath("$.body.replyList[0].id").value(1));
//        actions.andExpect(MockMvcResultMatchers.jsonPath("$.body.replyList[0].username").value("서회정"));
//        actions.andExpect(MockMvcResultMatchers.jsonPath("$.body.replyList[0].content").value("저도 포폴 준비중인데 개빡세요ㅠㅠ 같이 힘내요!"));
//        actions.andExpect(MockMvcResultMatchers.jsonPath("$.body.replyList[0].isOwner").value(false));
//        actions.andExpect(MockMvcResultMatchers.jsonPath("$.body.username").value("손영민"));
//        actions.andExpect(MockMvcResultMatchers.jsonPath("$.body.title").value("첫 이직 준비 넘 힘든데 포폴 꼭 해야 하나요?"));
//        actions.andExpect(MockMvcResultMatchers.jsonPath("$.body.content").value("첫 이직 준비 중인데 포트폴리오 꼭 만들어야 하나요 ㅠㅠ 아무리 해도 부족한 느낌이라 너무 스트레스받아요.."));
    }

}
