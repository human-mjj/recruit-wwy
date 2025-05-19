package com.example.recruit_page_wwy.integre.scrap;


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
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
//    public void scrap_user_list_test() throws Exception {
//        // given
//        ResumeRequest.SaveDTO reqDTO = new ResumeRequest.SaveDTO();
//        reqDTO.setTitle("title");
//        reqDTO.setImgUrl(null);
//        reqDTO.setPersonalHistory("경력");
//        reqDTO.setExperiencedDate("1 ~ 3년 차");
//        reqDTO.setEducationLevel("대학교 / 졸업");
//        reqDTO.setSchoolName("부산대학교");
//        reqDTO.setJobId(2);
//        reqDTO.setSkills(List.of("Java", "mySql"));
//        reqDTO.setRegion("부산광역시");
//        reqDTO.setRegionDetail("해운대구");
//        reqDTO.setQualified("정보처리기사");
//        reqDTO.setActivity("멋쟁이사자처럼 10기 활동");
//        reqDTO.setLetter("웹 서비스를 만드는 것이 저의 목표입니다.");
//
//
//        String requestBody = om.writeValueAsString(reqDTO);
//
//        // when
//        ResultActions actions = mvc.perform(
//                MockMvcRequestBuilders
//                        .post("/resume/save")
//                        .content(requestBody)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .header("Authorization", "Bearer " + accessToken)
//        );
//
//        // eye
//        String responseBody = actions.andReturn().getResponse().getContentAsString();
//        System.out.println(responseBody);


}
