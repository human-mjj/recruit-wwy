package com.example.recruit_page_wwy.integre;

import com.example.recruit_page_wwy.RestDoc;
import com.example.recruit_page_wwy._core.util.JwtUtil;
import com.example.recruit_page_wwy.user.User;
import com.example.recruit_page_wwy.user.UserRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.Matchers.matchesPattern;
import static org.hamcrest.Matchers.nullValue;


@Transactional
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class UserControllerTest extends RestDoc {

    @Autowired
    private ObjectMapper om;

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
    public void mypage_test() throws Exception {

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
        actions.andDo(MockMvcResultHandlers.print()).andDo(document);
    }

    @Test
    public void update_test() throws Exception {
        // given
        UserRequest.UpdateDTO reqDTO = new UserRequest.UpdateDTO();
        reqDTO.setPhone("01033331111");
        reqDTO.setPassword("123456");

        String requestBody = om.writeValueAsString(reqDTO);
//        System.out.println(requestBody);

        // when
        ResultActions actions = mvc.perform(
                MockMvcRequestBuilders
                        .put("/s/api/mypage")
                        .content(requestBody)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Bearer " + accessToken)
        );

//         eye
        String responseBody = actions.andReturn().getResponse().getContentAsString();
//        System.out.println(responseBody);


        // then
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.status").value(200));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.msg").value("성공"));

        actions.andExpect(MockMvcResultMatchers.jsonPath("$.body.username").value("손영민"));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.body.password").value(nullValue()));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.body.email").value("ssar@nate.com"));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.body.phone").value("01033331111"));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.body.role").value(0));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.body.comName").value(""));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.body.industryId").value(nullValue()));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.body.companyUser").value(false));
        actions.andDo(MockMvcResultHandlers.print()).andDo(document);
    }

    @Test
    public void join_user_test() throws Exception {
        // given
        UserRequest.UserDTO reqDTO = new UserRequest.UserDTO();
        reqDTO.setUsername("하하");
        reqDTO.setEmail("haha@nate.com");
        reqDTO.setPhone("01012341234");
        reqDTO.setPassword("Haha1!");
        reqDTO.setRole(0);

        String requestBody = om.writeValueAsString(reqDTO);
//        System.out.println(requestBody);

        // when
        ResultActions actions = mvc.perform(
                MockMvcRequestBuilders
                        .post("/join-user")
                        .content(requestBody)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Bearer " + accessToken)
        );

//         eye
        String responseBody = actions.andReturn().getResponse().getContentAsString();
//        System.out.println(responseBody);


        // then
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.status").value(200));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.msg").value("성공"));

        actions.andExpect(MockMvcResultMatchers.jsonPath("$.body.username").value("하하"));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.body.password").value(nullValue()));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.body.email").value("haha@nate.com"));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.body.phone").value("01012341234"));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.body.role").value(0));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.body.comName").value(""));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.body.industryId").value(nullValue()));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.body.companyUser").value(false));
        actions.andDo(MockMvcResultHandlers.print()).andDo(document);
    }

    @Test
    public void join_com_test() throws Exception {
        // given
        UserRequest.ComDTO reqDTO = new UserRequest.ComDTO();
        reqDTO.setUsername("하하");
        reqDTO.setEmail("haha@nate.com");
        reqDTO.setPhone("01012341239");
        reqDTO.setPassword("Haha1!");
        reqDTO.setRole(1);
        reqDTO.setComName("하하컴퍼");
        reqDTO.setIndustryId(2);


        String requestBody = om.writeValueAsString(reqDTO);
//        System.out.println(requestBody);

        // when
        ResultActions actions = mvc.perform(
                MockMvcRequestBuilders
                        .post("/join-com")
                        .content(requestBody)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Bearer " + accessToken)
        );

//         eye
        String responseBody = actions.andReturn().getResponse().getContentAsString();
//        System.out.println(responseBody);

        // then
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.status").value(200));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.msg").value("성공"));

        actions.andExpect(MockMvcResultMatchers.jsonPath("$.body.username").value("하하"));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.body.password").value(nullValue()));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.body.email").value("haha@nate.com"));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.body.phone").value("01012341239"));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.body.role").value(1));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.body.comName").value("하하컴퍼"));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.body.industryId").value(2));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.body.companyUser").value(true));
        actions.andDo(MockMvcResultHandlers.print()).andDo(document);
    }

    @Test
    public void login_test() throws Exception {
        // given
        UserRequest.LoginDTO reqDTO = new UserRequest.LoginDTO();
        reqDTO.setEmail("ssar@nate.com");
        reqDTO.setPassword("1234");
        reqDTO.setRole(0);

        String requestBody = om.writeValueAsString(reqDTO);
//        System.out.println(requestBody);

        // when
        ResultActions actions = mvc.perform(
                MockMvcRequestBuilders
                        .post("/login")
                        .content(requestBody)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Bearer " + accessToken)
        );


//         eye
        String responseBody = actions.andReturn().getResponse().getContentAsString();
//        System.out.println(responseBody);

        // then
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.status").value(200));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.msg").value("성공"));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.body.accessToken",
                matchesPattern("^[A-Za-z0-9-_]+\\.[A-Za-z0-9-_]+\\.[A-Za-z0-9-_]+$")));
        actions.andDo(MockMvcResultHandlers.print()).andDo(document);
    }
}
