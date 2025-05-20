package com.example.recruit_page_wwy.integre.board;

import com.example.recruit_page_wwy._core.util.JwtUtil;
import com.example.recruit_page_wwy.board.BoardRequest;
import com.example.recruit_page_wwy.user.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
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

import static org.hamcrest.Matchers.*;


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
        int page = 1;
        String keyword = "";

        // when
        ResultActions actions = mvc.perform(
                MockMvcRequestBuilders
                        .get("/api/board")
                        .param("page", Integer.toString(page))
                        .param("keyword", keyword)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Bearer " + accessToken)
        );

//        // eye
        String responseBody = actions.andReturn().getResponse().getContentAsString();
        System.out.println(responseBody);


//        // then
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.status").value(200));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.msg").value("성공"));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.body.boards[0].id").value(10));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.body.boards[0].title").value("중소기업 채용 어려운 이유, 어떻게 극복할까요?"));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.body.boards[0].content").value(Matchers.containsString("최근 구직자들의 지원율이 너무 낮아 고민입니다.")));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.body.boards[0].username").value("박정수"));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.body.boards[0].createdAt",
                matchesPattern("\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}\\.\\d+")));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.body.prev").value(0));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.body.next").value(2));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.body.current").value(1));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.body.size").value(5));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.body.prevPage").value(1));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.body.nextPage").value(3));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.body.totalCount").value(15));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.body.totalPage").value(3));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.body.isFirst").value(false));
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
                        .post("/s/api/board")
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
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.body.createdAt",
                matchesPattern("\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}\\.\\d+")));
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
                        .put("/s/api/board/{id}", id)
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
                        .delete("/s/api/board/{id}", id)
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
                        .get("/api/board/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Bearer " + accessToken)
        );

//        // eye
        String responseBody = actions.andReturn().getResponse().getContentAsString();
        System.out.println(responseBody);


        // then
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.status").value(200));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.msg").value("성공"));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.body.replyList[0].boardId").value(1));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.body.replyList[0].id").value(1));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.body.replyList[0].username").value("서회정"));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.body.replyList[0].content").value("저도 포폴 준비중인데 개빡세요ㅠㅠ 같이 힘내요!"));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.body.replyList[0].isOwner").value(false));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.body.username").value("손영민"));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.body.title").value("첫 이직 준비 넘 힘든데 포폴 꼭 해야 하나요?"));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.body.content").value("첫 이직 준비 중인데 포트폴리오 꼭 만들어야 하나요 ㅠㅠ 아무리 해도 부족한 느낌이라 너무 스트레스받아요.."));
    }

}
