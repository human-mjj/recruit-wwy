package com.example.recruit_page_wwy.integre.user;

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

import static org.hamcrest.Matchers.nullValue;


@Transactional
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class BoardControllerTest {

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
    public void Mypage_test() throws Exception {

        // when
        ResultActions actions = mvc.perform(
                MockMvcRequestBuilders
                        .get("/s/api/mypage")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Bearer " + accessToken)
        );

//        // eye
        String responseBody = actions.andReturn().getResponse().getContentAsString();
        System.out.println(responseBody);


        // then
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.status").value(200));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.msg").value("성공"));

        actions.andExpect(MockMvcResultMatchers.jsonPath("$.body.sessionUser.id").value(1));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.body.sessionUser.username").value("ssar"));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.body.sessionUser.email").value(nullValue()));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.body.sessionUser.phone").value(nullValue()));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.body.sessionUser.password").value(nullValue()));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.body.sessionUser.imgUrl").value(nullValue()));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.body.sessionUser.role").value(0));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.body.sessionUser.comName").value(nullValue()));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.body.sessionUser.industry").value(nullValue()));

        actions.andExpect(MockMvcResultMatchers.jsonPath("$.body.username").value("ssar"));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.body.email").value(nullValue()));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.body.isCompanyUser").value(false));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.body.imgUrl").value("/img/Screenshot_14.png"));
    }


}
