package com.example.recruit_page_wwy.integre;

import com.example.recruit_page_wwy.RestDoc;
import com.example.recruit_page_wwy._core.util.JwtUtil;
import com.example.recruit_page_wwy.apply.ApplyRequest;
import com.example.recruit_page_wwy.user.User;
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

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.matchesPattern;


@Transactional
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class ApplyControllerTest extends RestDoc {

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
    public void apply_test() throws Exception {
        // given
        ApplyRequest.SaveDTO reqDTO = new ApplyRequest.SaveDTO();
        reqDTO.setResumeId(1);
        reqDTO.setEmploymentId(1);

        String requestBody = om.writeValueAsString(reqDTO);
//        System.out.println(requestBody);

        // when
        ResultActions actions = mvc.perform(
                MockMvcRequestBuilders
                        .post("/s/api/apply")
                        .content(requestBody)
                        .param("resumeId", String.valueOf(reqDTO.getResumeId()))
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Bearer " + accessToken)
        );

//         eye
        String responseBody = actions.andReturn().getResponse().getContentAsString();
        System.out.println(responseBody);

        // then
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.status").value(200));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.msg").value("성공"));

        actions.andExpect(MockMvcResultMatchers.jsonPath("$.body.id").value(6));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.body.userId").value(1));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.body.resumeId").value(1));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.body.employmentId").value(1));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.body.createdAt")
                .value(matchesPattern("\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}\\.\\d+")));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.body.progress").value("대기"));
        actions.andDo(MockMvcResultHandlers.print()).andDo(document);
    }

    @Test
    public void update_apply_progress_test() throws Exception {
        // given
        Map<String, String> reqDTO = new HashMap<>();
        reqDTO.put("applyId", "1");
        reqDTO.put("progress", "진행 중");

        String requestBody = om.writeValueAsString(reqDTO);
        System.out.println(requestBody);

        // when
        ResultActions actions = mvc.perform(
                MockMvcRequestBuilders
                        .put("/s/api/apply")
                        .content(requestBody)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Bearer " + accessToken)
        );

//         eye
        String responseBody = actions.andReturn().getResponse().getContentAsString();
        System.out.println(responseBody);

        // then
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.status").value(200));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.msg").value("성공"));

        actions.andExpect(MockMvcResultMatchers.jsonPath("$.body.id").value(1));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.body.userId").value(1));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.body.resumeId").value(1));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.body.employmentId").value(1));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.body.createdAt")
                .value(matchesPattern("\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}\\.\\d+")));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.body.progress").value("진행 중"));
        actions.andDo(MockMvcResultHandlers.print()).andDo(document);
    }

    @Test
    public void list_test() throws Exception {
        // when
        ResultActions actions = mvc.perform(
                MockMvcRequestBuilders
                        .get("/s/api/apply")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Bearer " + accessToken)
        );

//         eye
        String responseBody = actions.andReturn().getResponse().getContentAsString();
        System.out.println(responseBody);

        // then
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.status").value(200));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.msg").value("성공"));

        actions.andExpect(MockMvcResultMatchers.jsonPath("$.body.userApplyList[0].comName").value("WWY"));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.body.userApplyList[0].name").value("ai 엔지니어"));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.body.userApplyList[0].createdAt")
                .value(matchesPattern("\\d{4}-\\d{2}-\\d{2}( \\d{2}:\\d{2}:\\d{2}\\.\\d+)?")));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.body.userApplyList[0].progress").value("대기"));

        actions.andExpect(MockMvcResultMatchers.jsonPath("$.body.sessionUserId").value(1));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.body.isCompanyUser").value(false));
        actions.andDo(MockMvcResultHandlers.print()).andDo(document);
    }

    @Test
    public void apply_manage_list_test() throws Exception {
        // given
        User hog = User.builder()
                .id(7)
                .username("HOG")
                .role(1)
                .build();
        accessToken = JwtUtil.create(hog);
        System.out.println(JwtUtil.create(hog)); // 발급된 토큰 출력해서 base64 디코딩해보기

        // when
        ResultActions actions = mvc.perform(
                MockMvcRequestBuilders
                        .get("/s/api/apply/com")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Bearer " + accessToken)
        );

//         eye
        String responseBody = actions.andReturn().getResponse().getContentAsString();
        System.out.println(responseBody);

        // then
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.status").value(200));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.msg").value("성공"));

        actions.andExpect(MockMvcResultMatchers.jsonPath("$.body.sessionUserId").value(7));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.body.isCompanyUser").value(true));
        actions.andDo(MockMvcResultHandlers.print()).andDo(document);
    }
}
