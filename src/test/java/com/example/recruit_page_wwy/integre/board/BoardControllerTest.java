package com.example.recruit_page_wwy.integre.board;

import com.example.recruit_page_wwy._core.util.JwtUtil;
import com.example.recruit_page_wwy.board.BoardRequest;
import com.example.recruit_page_wwy.user.User;
import com.example.recruit_page_wwy.util.config.FilterConfigTest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
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
    public void list_test() throws Exception {
        // given
        BoardRequest.SearchRequestDTO reqDTO = new BoardRequest.SearchRequestDTO("", 1);

        String requestBody = om.writeValueAsString(reqDTO);

        // when
        ResultActions actions = mvc.perform(
                MockMvcRequestBuilders
                        .post("/board")
                        .content(requestBody)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Bearer " + accessToken)
        );

//        // eye
        String responseBody = actions.andReturn().getResponse().getContentAsString();
        System.out.println(responseBody);


//        // then
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.status").value(200));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.msg").value("성공"));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.body.boards[0].id").value(15));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.body.boards[0].title").value("AI 면접 시스템 도입해보신 분 있나요?"));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.body.boards[0].content").value(Matchers.containsString("AI 면접을 도입하는 것을 검토하고 있습니다")));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.body.boards[0].username").value("홍길동"));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.body.prev").value(-1));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.body.next").value(1));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.body.current").value(0));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.body.size").value(5));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.body.totalCount").value(15));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.body.totalPage").value(3));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.body.isFirst").value(true));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.body.isLast").value(false));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.body.numbers", hasSize(3)));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.body.keyword").value(""));
    }

    @Test
    public void save_test() throws Exception {
        // given
        BoardRequest.SaveDTO reqDTO = new BoardRequest.SaveDTO();
        reqDTO.setTitle("title");
        reqDTO.setContent("content");


        String requestBody = om.writeValueAsString(reqDTO);

        // when
        ResultActions actions = mvc.perform(
                MockMvcRequestBuilders
                        .post("/board/save")
                        .content(requestBody)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Bearer " + accessToken)
        );

//        // eye
        String responseBody = actions.andReturn().getResponse().getContentAsString();
        System.out.println(responseBody);


//        // then
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.status").value(200));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.msg").value("성공"));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.body.id").value(16));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.body.title").value("title"));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.body.content").value("content"));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.body.userId").value(1));
    }


    @Test
    public void update_test() throws Exception {
        // given
        Integer id = 1;
        BoardRequest.UpdateDTO reqDTO = new BoardRequest.UpdateDTO("title", "content");

        String requestBody = om.writeValueAsString(reqDTO);

        // when
        ResultActions actions = mvc.perform(
                MockMvcRequestBuilders
                        .put("/board/{id}/update", id)
                        .content(requestBody)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Bearer " + accessToken)
        );

//        // eye
        String responseBody = actions.andReturn().getResponse().getContentAsString();
        System.out.println(responseBody);


//        // then
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.status").value(200));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.msg").value("성공"));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.body.id").value(1));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.body.title").value("title"));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.body.content").value("content"));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.body.userId").value(1));

    }

    @Test
    public void delete_test() throws Exception {
        // given
        Integer id = 1;

        // when
        ResultActions actions = mvc.perform(
                MockMvcRequestBuilders
                        .delete("/board/{id}/delete", id)
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
