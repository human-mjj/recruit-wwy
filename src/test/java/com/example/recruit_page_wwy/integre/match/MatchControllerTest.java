package com.example.recruit_page_wwy.integre.match;

import com.example.recruit_page_wwy._core.util.JwtUtil;
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


@Transactional
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class MatchControllerTest {

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
    public void MatchList_test() throws Exception {
        // when
        ResultActions actions = mvc.perform(
                MockMvcRequestBuilders
                        .get("/s/api/match")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Bearer " + accessToken)
        );

//         eye
        String responseBody = actions.andReturn().getResponse().getContentAsString();
//        System.out.println(responseBody);


        // then
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.status").value(200));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.msg").value("성공"));

        actions.andExpect(MockMvcResultMatchers.jsonPath("$.body.proposalList[0].id").value(21));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.body.proposalList[0].title").value("HOG에서 UX 디자이너 찾습니다"));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.body.proposalList[0].comName").value("HOG"));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.body.proposalList[0].exp").value("10년 이상"));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.body.proposalList[0].location").value("서울특별시 성동구"));

        actions.andExpect(MockMvcResultMatchers.jsonPath("$.body.recommendationList[0].id").value(1));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.body.recommendationList[0].title").value("Spring 백엔드 개발자 모집"));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.body.recommendationList[0].comName").value("WWY"));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.body.recommendationList[0].exp").value("10년 이상"));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.body.recommendationList[0].location").value("서울특별시 강남구"));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.body.companyUser").value(false));
    }

    @Test
    public void MatchComList_test() throws Exception {
        // given
        User hog = User.builder()
                .id(7)
                .username("HOG")
                .role(1)
                .build();
        accessToken = JwtUtil.create(hog);

        // when
        ResultActions actions = mvc.perform(
                MockMvcRequestBuilders
                        .get("/s/api/match/com")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Bearer " + accessToken)
        );

//         eye
        String responseBody = actions.andReturn().getResponse().getContentAsString();
        System.out.println(responseBody);


        // then
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.status").value(200));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.msg").value("성공"));

        actions.andExpect(MockMvcResultMatchers.jsonPath("$.body.resumes[0].id").value(1));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.body.resumes[0].title").value("백엔드 신입 개발자 지원서"));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.body.resumes[0].author").value("손영민"));


        actions.andExpect(MockMvcResultMatchers.jsonPath("$.body.sessionUserId").value(7));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.body.isCompanyUser").value(true));
    }
}
