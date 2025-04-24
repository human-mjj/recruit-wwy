package com.example.recruit_page_wwy.employment;


import com.example.recruit_page_wwy.employstack.EmployStack;
import com.example.recruit_page_wwy.resume.Resume;
import com.example.recruit_page_wwy.resume.ResumeRepository;
import com.example.recruit_page_wwy.user.User;
import com.example.recruit_page_wwy.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class EmploymentService {
    private final EmploymentRepository employmentRepository;
    private final UserRepository userRepository;
    private final ResumeRepository resumeRepository;

    public List<EmploymentResponse.ListDTO> employmentList(Integer userId) {
        List<Employment> employmentList = employmentRepository.findAllByUserId(userId);

        List<EmploymentResponse.ListDTO> dtoList = new ArrayList<>();
        for (Employment e : employmentList) {
            EmploymentResponse.ListDTO dto = new EmploymentResponse.ListDTO(e);
            dtoList.add(dto);
        }

        return dtoList;
    }

    public List<EmploymentResponse.PublicListDTO> emplymentAllList(Integer userId) {
        List<Employment> employmentAllList = employmentRepository.findAll();

        List<EmploymentResponse.PublicListDTO> dtoList = new ArrayList<>();
        for (Employment e : employmentAllList) {
            EmploymentResponse.PublicListDTO dto = new EmploymentResponse.PublicListDTO(e);
            dtoList.add(dto);
        }

        return dtoList;
    }

    public EmploymentResponse.DetailDTO findEmploymentDetail(Integer userId, Integer sessionUserId) {

        // 1. 채용공고, 유저, 잡, 스택 조인해서 들고오기
        Employment e = employmentRepository.findByIdWithJoins(userId);
        if (e == null) {
            throw new RuntimeException("해당 공고가 존재하지 않습니다.");
        }

        // 2. duty 리스트 만들기
        List<String> dutyList = new ArrayList<>();
        if (e.getDuty() != null && !e.getDuty().isEmpty()) {
            String[] dutyArray = e.getDuty().split("\n");
            for (String d : dutyArray) {
                dutyList.add(d.trim());
            }
        }

        // 3. qualification 리스트 만들기
        List<String> qualificationList = new ArrayList<>();
        if (e.getQualification() != null && !e.getQualification().isEmpty()) {
            String[] qualificationArray = e.getQualification().split("\n");
            for (String q : qualificationArray) {
                qualificationList.add(q.trim());
            }
        }

        // 4. 기술 스택 리스트 만들기
        List<String> stackList = new ArrayList<>();
        List<EmployStack> employStackList = e.getEmployStackList();
        for (EmployStack es : employStackList) {
            stackList.add(es.getSkill());
        }

        // 5. 로그인 유저 정보 처리
        Integer sessionUserRole = null;
        Boolean isOwner = false;
        Boolean isApplicant = false;
        List<EmploymentResponse.DetailDTO.ResumeDTO> resumeList = new ArrayList<>();

        //세션유저 데이터가 있을 때
        if (sessionUserId != null) {
            User sessionUser = userRepository.findById(sessionUserId);
            if (sessionUser != null) {
                sessionUserRole = sessionUser.getRole(); // 0 or 1

                // 세션 유저 아이디가 채용공고 작성자와 같을 때 => 수정삭제버튼
                if (sessionUserId.equals(e.getUser().getId())) {
                    isOwner = true;
                }

                // 세션유저가 구직자일때 이력서 선택가능, 지원하기 버튼 클릭 가능
                if (sessionUserRole == 0) {
                    isApplicant = true;

                    List<Resume> resumes = resumeRepository.findByUserId(sessionUserId);
                    for (Resume r : resumes) {
                        EmploymentResponse.DetailDTO.ResumeDTO resumeDTO =
                                new EmploymentResponse.DetailDTO.ResumeDTO(r);
                        resumeList.add(resumeDTO);
                    }
                }
            }
        }

        // 6. DTO 생성 및 리턴
        EmploymentResponse.DetailDTO dto = new EmploymentResponse.DetailDTO(
                sessionUserId,
                sessionUserRole,
                isOwner,
                isApplicant,
                e.getId(),
                e.getUser().getImgUrl(),
                e.getTitle(),
                e.getUser().getComName(),
                e.getExp(),
                e.getEdu(),
                e.getShift(),
                e.getSal(),
                e.getWorkingTime(),
                e.getLocation(),
                e.getEndDate(),
                dutyList,
                qualificationList,
                e.getJob().getName(),
                stackList,
                (resumeList != null) ? resumeList : new ArrayList<>()
        );

        // 리턴
        return dto;
    }
}
