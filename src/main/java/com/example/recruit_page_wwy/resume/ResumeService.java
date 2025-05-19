package com.example.recruit_page_wwy.resume;


import com.example.recruit_page_wwy._core.error.ex.ExceptionApi403;
import com.example.recruit_page_wwy._core.error.ex.ExceptionApi404;
import com.example.recruit_page_wwy._core.util.Base64Util;
import com.example.recruit_page_wwy.employment.Employment;
import com.example.recruit_page_wwy.employment.EmploymentRepository;
import com.example.recruit_page_wwy.job.Job;
import com.example.recruit_page_wwy.resumestack.ResumeStack;
import com.example.recruit_page_wwy.scrap.Scrap;
import com.example.recruit_page_wwy.scrap.ScrapRepository;
import com.example.recruit_page_wwy.stack.Stack;
import com.example.recruit_page_wwy.user.User;
import com.example.recruit_page_wwy.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class ResumeService {
    private final ResumeRepository resumeRepository;
    private final EmploymentRepository employmentRepository;
    private final ScrapRepository scrapRepository;
    private final UserRepository userRepository;

    // TODO : 저장 후 DTO에 담아서 반환
    @Transactional
    public ResumeResponse.DTO save(ResumeRequest.SaveDTO reqDTO, User sessionUser) {
        User userPS = userRepository.findUserById(sessionUser.getId());
        String imgFilename = null;

        // 새 이미지가 Base64 문자열로 넘어온 경우에만 저장
        String imgUrl = reqDTO.getImgUrl();
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
        Resume resumePS = resumeRepository.save(reqDTO.toEntity(sessionUser, imgFilename));
        resumeRepository.updateStack(resumePS.getId(), reqDTO.getSkills());
        return new ResumeResponse.DTO(resumePS);
    }

    @Transactional
    public ResumeResponse.MainDTO findAll(Integer userId, Integer page) {
        int realPage = page - 1;
        int size = 5;
        Long totalCount = resumeRepository.totalCount(userId);
        List<Resume> resumes = resumeRepository.findAll(userId, realPage);
        return new ResumeResponse.MainDTO(resumes, page, totalCount.intValue());
    }

    public ResumeResponse.DetailDTO detailView(Integer resumeId, User sessionUser) {
        Resume resume = resumeRepository.findByResumeId(resumeId);
        if (resume == null) throw new RuntimeException("404 Not Found");

        boolean isScrap = false;
        Integer scrapId = null;

        // 채용공고 리스트 (기업인 경우만)
        List<ResumeResponse.DetailDTO.EmployDTO> employmentList = new ArrayList<>();
        if (sessionUser != null && sessionUser.getRole() == 1) {
            List<Employment> employments = employmentRepository.findByUserId(sessionUser.getId());
            for (Employment employment : employments) {
                employmentList.add(new ResumeResponse.DetailDTO.EmployDTO(employment));
                System.out.println(resume.getTitle());
            }

            Scrap scrap = scrapRepository.findByUserIdAndResumeId(sessionUser.getId(), resumeId);
            isScrap = scrap != null;
            scrapId = scrap == null ? null : scrap.getId();
        }
        return new ResumeResponse.DetailDTO(sessionUser, resume, employmentList, isScrap, scrapId);
    }

    // TODO : 업데이트 후 DTO에 담아서 반환
    @Transactional
    public ResumeResponse.DTO update(Integer id, ResumeRequest.SaveDTO reqDTO, User sessionUser) {
        Resume resumePS = resumeRepository.findByResumeId(id);
        if (resumePS == null) throw new ExceptionApi404("해당하는 이력서가 없습니다.");
        User userPS = userRepository.findUserById(sessionUser.getId());
        String imgFilename = null;

        // 새 이미지가 Base64 문자열로 넘어온 경우에만 저장
        String imgUrl = reqDTO.getImgUrl();
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
        resumePS.update(reqDTO, imgFilename);
        resumeRepository.updateStack(resumePS.getId(), reqDTO.getSkills());
        return new ResumeResponse.DTO(resumePS);
    }

    public ResumeResponse.UpdateViewDTO findById(Integer resumeId) {
        Resume resume = resumeRepository.findByResumeId(resumeId);
        if (resume == null) throw new ExceptionApi404("404 Not Found");

        List<Job> jobList = resumeRepository.findAllJobs();

        List<Stack> stackList = resumeRepository.findAllStacks();

        // 4. 현재 employment에 등록된 스택(skill) 목록 뽑기
        List<String> selectedStacks = new ArrayList<>();
        for (ResumeStack resumeStack : resume.getResumeStackList()) {
            selectedStacks.add(resumeStack.getSkill());
        }

        // 5. jobList -> jobDTOList로 변환
        List<ResumeResponse.UpdateViewDTO.JobDTO> jobDTOList = new ArrayList<>();
        for (Job job : jobList) {
            ResumeResponse.UpdateViewDTO.JobDTO dto = new ResumeResponse.UpdateViewDTO.JobDTO();
            dto.setId(job.getId());
            dto.setName(job.getName());
            jobDTOList.add(dto);
        }

        // 6. stackList -> stackDTOList로 변환
        List<ResumeResponse.UpdateViewDTO.StackDTO> stackDTOList = new ArrayList<>();
        for (Stack stack : stackList) {
            ResumeResponse.UpdateViewDTO.StackDTO dto = new ResumeResponse.UpdateViewDTO.StackDTO();
            dto.setSkill(stack.getSkill());
            stackDTOList.add(dto);
        }

        // 7. selectedStacks 저장
        List<ResumeStack> selectedStackList = resumeRepository.findAllStacksByResumeId(resumeId);
        ResumeResponse.UpdateViewDTO updateViewDTO = new ResumeResponse.UpdateViewDTO(resume, jobDTOList, stackDTOList, selectedStackList);
        return updateViewDTO;
    }

    // TODO : CSR 구조 미준수로 인한 수정
    @Transactional
    public void delete(int resumeId, User sessionUser) {
        Resume resume = resumeRepository.findByResumeId(resumeId);
        if (resume == null) throw new ExceptionApi404("404 Not Found");
        User userPS = userRepository.findUserById(sessionUser.getId());
        if (userPS == null) throw new ExceptionApi404("404 Not Found");
        if (userPS.getId() != resume.getUser().getId()) throw new ExceptionApi403("403 Forbidden");

        resumeRepository.delete(resumeId);
    }

    public ResumeResponse.TableDTO viewJobAndStackList() {
        List<Job> jobList = resumeRepository.findAllJobs();
        List<Stack> stackList = resumeRepository.findAllStacks();
        return new ResumeResponse.TableDTO(jobList, stackList);
    }
}
