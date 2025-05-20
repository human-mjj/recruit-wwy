package com.example.recruit_page_wwy.integre.scrap;


import com.example.recruit_page_wwy._core.util.JwtUtil;
import com.example.recruit_page_wwy.scrap.ScrapRequest;
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

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.nullValue;

@Import(FilterConfigTest.class)
@Transactional
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)

public class ScrapControllerTest {

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
    public void scrap_user_list_test() throws Exception {
        // given
        Integer page = 1;

        // when
        ResultActions actions = mvc.perform(
                MockMvcRequestBuilders
                        .get("/s/api/mypage/scrap")
                        .param("page", page.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Bearer " + accessToken)
        );

        // eye
        String responseBody = actions.andReturn().getResponse().getContentAsString();
        System.out.println(responseBody);

        // then
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.status").value(200));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.msg").value("성공"));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.body.scraps[0].title").value("Spring 백엔드 개발자 모집"));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.body.scraps[0].comName").value("WWY"));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.body.scraps[0].exp").value("10년 이상"));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.body.scraps[0].location").value("서울특별시 강남구"));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.body.scraps[0].name").value("백엔드 개발자"));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.body.scraps[0].employmentId").value(1));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.body.current").value(1));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.body.totalCount").value(10));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.body.prev").value(0));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.body.next").value(2));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.body.resumeId").isEmpty());
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.body.size").value(8));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.body.totalPage").value(2));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.body.isFirst").value(true));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.body.isLast").value(false));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.body.numbers", hasSize(2)));
    }

    @Test
    public void save_scrap_test() throws Exception {
        // given
        ScrapRequest.SaveDTO reqDTO = new ScrapRequest.SaveDTO();
        reqDTO.setEmploymentId(1);

        String requestBody = om.writeValueAsString(reqDTO);

        // when
        ResultActions actions = mvc.perform(
                MockMvcRequestBuilders
                        .post("/s/api/scrap")
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
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.body.scrapId").value(101));

    }

    @Test
    public void delete_scrap_test() throws Exception {
        // given
        int id = 1;


        // when
        ResultActions actions = mvc.perform(
                MockMvcRequestBuilders
                        .delete("/s/api/scrap/{id}", id)
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


}
