package com.example.recruit_page_wwy.employment;


import com.example.recruit_page_wwy._core.error.ex.ExceptionApi403;
import com.example.recruit_page_wwy._core.error.ex.ExceptionApi404;
import com.example.recruit_page_wwy._core.util.Base64Util;
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

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class EmploymentService {
    private final EmploymentRepository employmentRepository;
    private final UserRepository userRepository;
    private final ResumeRepository resumeRepository;
    private final EmployStackRepository employStackRepository;
    private final ScrapRepository scrapRepository;

    public EmploymentResponse.EmploymentDashboardDTO employmentList(User sessionUser, Integer page) {
        if (page < 1) {
            page = 1;
        }
        Long totalCount = employmentRepository.totalCount(sessionUser.getId());
        List<Employment> employmentList = employmentRepository.findAllByUserId(sessionUser.getId(), page);

        List<EmploymentResponse.ListDTO> dtoList = new ArrayList<>();
        for (Employment e : employmentList) {
            EmploymentResponse.ListDTO dto = new EmploymentResponse.ListDTO(e, sessionUser);
            dtoList.add(dto);
        }

        return new EmploymentResponse.EmploymentDashboardDTO(
                sessionUser.getRole() == 1,
                dtoList,
                page,
                totalCount.intValue()
        );
    }

    // 채용공고 리스트, paging
    public EmploymentResponse.EmploymentPageDTO employmentAllList(User sessionUser, String jobType, String careerLevel, List<String> skills, String sort, Integer page) {
        Long totalCount = employmentRepository.totalCount();
        List<Employment> employmentList = new ArrayList<>();

        if (sort.equals("recommend")) {
            employmentList = employmentRepository.findAllWithRecommend(jobType, careerLevel, skills, sort, page);
        } else {
            employmentList = employmentRepository.findAll(jobType, careerLevel, skills, sort, page);
        }


        List<EmploymentResponse.PublicListDTO> dtoList = new ArrayList<>();
        for (Employment e : employmentList) {

            dtoList.add(new EmploymentResponse.PublicListDTO(e, sessionUser));
        }

        EmploymentResponse.TableDTO tableDTO = new EmploymentResponse.TableDTO(
                employmentRepository.findAllJobs(),
                employmentRepository.findAllStacks(),
                jobType, skills
        );
        List<String> careerLevels = new ArrayList<>();
        careerLevels.add("신입");
        careerLevels.add("1 ~ 3년 차");
        careerLevels.add("3 ~ 5년 차");
        careerLevels.add("5 ~ 7년 차");
        careerLevels.add("7 ~ 9년 차");
        careerLevels.add("10년 이상");
        return new EmploymentResponse.EmploymentPageDTO(dtoList, page, totalCount.intValue(), tableDTO, careerLevels, sessionUser, jobType, careerLevel, sort, skills);
    }

    public EmploymentResponse.MainDTO viewEmployList(User sessionUser) {
        return new EmploymentResponse.MainDTO(sessionUser, employmentRepository.findTop4ByOrderByIdDesc());
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
                System.out.println(resume.getTitle());
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
        return new EmploymentResponse.TableDTO(jobList, stackList, null, null);
    }

    @Transactional
    public EmploymentResponse.DTO save(EmploymentRequest.SaveDTO saveDTO, User sessionUser) {
        if (sessionUser == null || sessionUser.getRole() != 1) throw new ExceptionApi403("403 Forbidden");
        String imgFilename = null;

        // 새 이미지가 Base64 문자열로 넘어온 경우에만 저장
        String imgUrl = saveDTO.getImgUrl();
        if (imgUrl != null && imgUrl.startsWith("data:image/")) {
            imgFilename = UUID.randomUUID() + "_" + saveDTO.getTitle();
            Path imgPath = Paths.get("./upload/" + imgFilename);

            try {
                // 폴더가 없으면 생성, 있으면 넘어감
                Files.createDirectories(imgPath.getParent());
                byte[] decodedImageBytes = Base64Util.decodeAsBytes(imgUrl);
                Files.write(imgPath, decodedImageBytes);
            } catch (Exception e) {
                throw new RuntimeException("이미지 저장 실패", e);
            }
        }
        Employment savingEmployment = saveDTO.toEntity(sessionUser, imgFilename);
        Employment employmentPS = employmentRepository.save(savingEmployment, saveDTO.getEmployStack());

        List<EmployStack> stackList = employmentRepository.findAllStacksByEmploymentId(employmentPS.getId());

        return new EmploymentResponse.DTO(employmentPS, stackList);

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

    // TODO : 업데이트 후 DTO에 담아서 반환
    @Transactional
    public EmploymentResponse.DTO update(int employmentId, EmploymentRequest.SaveDTO dto, User sessionUser) {
        if (sessionUser == null || sessionUser.getRole() != 1) throw new ExceptionApi403("403 Forbidden");

        // 1. 수정할 Employment 엔티티를 조회
        Employment employment = employmentRepository.findByEmploymentId(employmentId);
        if (employment == null) throw new RuntimeException("해당 채용공고를 찾을 수 없습니다.");
        User userPS = userRepository.findUserById(sessionUser.getId());
        String imgFilename = null;

        // 새 이미지가 Base64 문자열로 넘어온 경우에만 저장
        String imgUrl = dto.getImgUrl();
        if (imgUrl != null && imgUrl.startsWith("data:image/")) {
            imgFilename = UUID.randomUUID() + "_" + userPS.getUsername();
            Path imgPath = Paths.get("./upload/" + imgFilename);

            try {
                // 폴더가 없으면 생성, 있으면 넘어감
                Files.createDirectories(imgPath.getParent());
                byte[] decodedImageBytes = Base64Util.decodeAsBytes(imgUrl);
                Files.write(imgPath, decodedImageBytes);
            } catch (Exception e) {
                throw new RuntimeException("이미지 저장 실패", e);
            }
        }

        // 2. Employment 엔티티의 update 메서드를 호출
        employment.update(dto, imgFilename);
        Employment employmentPS = employmentRepository.findByEmploymentId(employmentId);

        // 3. 스택(EmployStack) 수정은 별도로 처리 필요
        employmentRepository.updateStack(employmentId, dto.getEmployStack()); // 기존 스택 전부 삭제
        List<EmployStack> stackList = employmentRepository.findAllStacksByEmploymentId(employmentPS.getId());

        return new EmploymentResponse.DTO(employmentPS, stackList);
    }

    @Transactional
    public void delete(int employmentId, User sessionUser) {
        Employment employmentPS = employmentRepository.findByEmploymentId(employmentId);
        if (employmentPS == null) throw new ExceptionApi404("404 Not Found");
        if (sessionUser.getId() != employmentPS.getUser().getId()) throw new ExceptionApi403("403 Forbidden");
        employmentRepository.delete(employmentPS);
    }
}
