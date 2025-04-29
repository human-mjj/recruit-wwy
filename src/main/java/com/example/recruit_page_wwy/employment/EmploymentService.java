package com.example.recruit_page_wwy.employment;


import com.example.recruit_page_wwy.employstack.EmployStack;
import com.example.recruit_page_wwy.employstack.EmployStackRepository;
import com.example.recruit_page_wwy.job.Job;
import com.example.recruit_page_wwy.resume.Resume;
import com.example.recruit_page_wwy.resume.ResumeRepository;
import com.example.recruit_page_wwy.scrap.Scrap;
import com.example.recruit_page_wwy.scrap.ScrapRepository;
import com.example.recruit_page_wwy.stack.Stack;
import com.example.recruit_page_wwy.user.User;
import com.example.recruit_page_wwy.user.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class EmploymentService {
    private final EmploymentRepository employmentRepository;
    private final UserRepository userRepository;
    private final ResumeRepository resumeRepository;
    private final EmployStackRepository employStackRepository;
    private final ScrapRepository scrapRepository;

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

    public List<Employment> viewEmployList() {
        return employmentRepository.findTop4ByOrderByIdDesc();
    }

    public EmploymentResponse.DetailDTO findEmploymentDetail(Integer employmentId, User sessionUser) {

        // 채용공고 엔티티 조회
        Employment employment = employmentRepository.findByEmploymentId(employmentId);
        if (employment == null) throw new RuntimeException("채용공고를 찾을 수 없습니다.");

        // 스택 리스트
        List<String> stackList = new ArrayList<>();
        for (EmployStack employStack : employment.getEmployStackList()) {
            if (employStack.getSkill() != null) { // 혹시 null 방어
                stackList.add(employStack.getSkill());
            }
        }

        boolean isScrap = false;
        Integer scrapId = null;

        // 이력서 리스트 (구직자인 경우만)
        List<EmploymentResponse.DetailDTO.ResumeDTO> resumeList = new ArrayList<>();
        if (sessionUser != null && sessionUser.getRole() == 0) {
            List<Resume> resumes = resumeRepository.findByUserId(sessionUser.getId());
            for (Resume resume : resumes) {
                resumeList.add(new EmploymentResponse.DetailDTO.ResumeDTO(resume));
            }

            Scrap scrap = scrapRepository.findByUserIdAndEmployId(sessionUser.getId(), employmentId);
            isScrap = scrap == null ? false : true;
            scrapId = scrap == null ? null : scrap.getId();
        }

        // 최종 DTO 생성 (우리가 만든 깔끔한 생성자 사용)
        return new EmploymentResponse.DetailDTO(sessionUser, employment, resumeList, stackList, isScrap, scrapId);
    }

    public EmploymentResponse.TableDTO viewJobAndStackList() {
        List<Job> jobList = employmentRepository.findAllJobs();
        List<Stack> stackList = employmentRepository.findAllStacks();
        return new EmploymentResponse.TableDTO(jobList, stackList);
    }

    @Transactional
    public void save(EmploymentRequest.SaveDTO saveDTO, User sessionUser) {
        Employment savingEmployment = saveDTO.toEntity(sessionUser);
        employmentRepository.save(savingEmployment, saveDTO.getEmployStack());
    }

    public EmploymentResponse.UpdateViewDTO showUpdateView(int employmentId) {
        // 1. employment 1개 조회
        Employment employment = employmentRepository.findByEmploymentId(employmentId);
        if (employment == null) throw new RuntimeException("404 Not Found");

        // 2. 직군 리스트 전체 조회
        List<Job> jobList = employmentRepository.findAllJobs();

        // 3. 기술 스택 리스트 전체 조회
        List<Stack> stackList = employmentRepository.findAllStacks();

        // 4. 현재 employment에 등록된 스택(skill) 목록 뽑기
        List<String> selectedStacks = new ArrayList<>();
        for (EmployStack employStack : employment.getEmployStackList()) {
            selectedStacks.add(employStack.getSkill());
        }

        // 5. jobList -> jobDTOList로 변환
        List<EmploymentResponse.UpdateViewDTO.JobDTO> jobDTOList = new ArrayList<>();
        for (Job job : jobList) {
            EmploymentResponse.UpdateViewDTO.JobDTO dto = new EmploymentResponse.UpdateViewDTO.JobDTO();
            dto.setId(job.getId());
            dto.setName(job.getName());
            jobDTOList.add(dto);
        }

        // 6. stackList -> stackDTOList로 변환
        List<EmploymentResponse.UpdateViewDTO.StackDTO> stackDTOList = new ArrayList<>();
        for (Stack stack : stackList) {
            EmploymentResponse.UpdateViewDTO.StackDTO dto = new EmploymentResponse.UpdateViewDTO.StackDTO();
            dto.setSkill(stack.getSkill());
            stackDTOList.add(dto);
        }

        // 7. selectedStacks 저장
        List<EmployStack> selectedStackList = employmentRepository.findAllStacksByEmploymentId(employmentId);


        // 7. UpdateViewDTO 조립
        EmploymentResponse.UpdateViewDTO updateViewDTO = EmploymentResponse.UpdateViewDTO.builder()
                .employment(employment)
                .jobList(jobDTOList)
                .stackList(stackDTOList)
                .selectedStacks(selectedStackList)
                .build();

        return updateViewDTO;
    }

    @Transactional
    public void update(int employmentId, EmploymentRequest.SaveDTO dto) {
        // 1. 수정할 Employment 엔티티를 조회
        Employment employment = employmentRepository.findByEmploymentId(employmentId);
        if (employment == null) throw new RuntimeException("해당 채용공고를 찾을 수 없습니다.");

        // 2. Employment 엔티티의 update 메서드를 호출
        employment.update(dto);

        // 3. 스택(EmployStack) 수정은 별도로 처리 필요
        employmentRepository.updateStack(employmentId, dto.getEmployStack()); // 기존 스택 전부 삭제
    }

    public List<String> getCareerLevels() {
        List<String> careerLevels = new ArrayList<>();
        careerLevels.add("신입");
        careerLevels.add("1 ~ 3년 차");
        careerLevels.add("3 ~ 5년 차");
        careerLevels.add("5 ~ 7년 차");
        careerLevels.add("7 ~ 9년 차");
        careerLevels.add("10년 이상");
        return careerLevels;
    }

    public List<EmploymentResponse.ListDTO> emplymentAllListFiltered(String jobType, String careerLevel, List<String> skills, String sort) {
        List<Employment> employmentList = employmentRepository.search(jobType, careerLevel, skills, sort);

        List<EmploymentResponse.ListDTO> dtoList = employmentList.stream()
                .map(employment -> new EmploymentResponse.ListDTO(employment))
                .collect(Collectors.toList());

        return dtoList;

    }
}

